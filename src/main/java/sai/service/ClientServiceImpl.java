package sai.service;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import proto.cal.CalculatorGrpc;
import proto.cal.CalculatorOuterClass.Request;
import proto.cal.CalculatorOuterClass.Response;
import proto.greet.GreetServiceGrpc;
import proto.greet.Greeting;
import proto.prime.Prime;
import proto.prime.PrimeNumberDecompositionGrpc;
import proto.prime.PrimeNumberDecompositionGrpc.PrimeNumberDecompositionBlockingStub;
import proto.sum.AdditionGrpc;
import proto.sum.Sum;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClientServiceImpl {

    public void serverStreamingOperations(ManagedChannel channel) {

        doGreetServerStreamingOperations(channel);
        doPrimeNumberNumberDecompOperations(channel);
    }

    private void doGreetServerStreamingOperations(ManagedChannel channel) {
        GreetServiceGrpc.GreetServiceBlockingStub grpcClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting.GreetRequest request = Greeting.GreetRequest.newBuilder()
                                                             .setGreetings(Greeting.GreetingName.newBuilder().setFirstName("STG"))
                                                             .build();

        Iterator<Greeting.GreetResponse> greetResponseIterator = grpcClient.greetManyTimes(request);

        greetResponseIterator.forEachRemaining(System.out::println);
    }

    private void doPrimeNumberNumberDecompOperations(ManagedChannel channel) {
        PrimeNumberDecompositionBlockingStub primeClient = PrimeNumberDecompositionGrpc.newBlockingStub(channel);

        Prime.Request request = Prime.Request.newBuilder().setN(567890304).build();

        Iterator<Prime.Response> primeNumberDecomposition = primeClient.getAll(request);

        primeNumberDecomposition.forEachRemaining(System.out::println);
    }

    public void unaryOperations(ManagedChannel channel) {
        doGreetingServiceOperations(channel);
        doAdditionOperations(channel);

    }

    private void doAdditionOperations(ManagedChannel channel) {
        AdditionGrpc.AdditionBlockingStub addingClient = AdditionGrpc.newBlockingStub(channel);

        Sum.Request request = Sum.Request.newBuilder().setA(3).setB(10).build();

        Sum.Response response = addingClient.addThem(request);

        System.out.println("sum -> " + response.getC());
    }

    private void doGreetingServiceOperations(ManagedChannel channel) {
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting.GreetingName greetingName = Greeting.GreetingName.newBuilder()
                                                                  .setFirstName("sai")
                                                                  .setLastName("prakash")
                                                                  .build();

        Greeting.GreetRequest request = Greeting.GreetRequest.newBuilder()
                                                             .setGreetings(greetingName)
                                                             .build();

        Greeting.GreetResponse response = greetClient.greet(request);
    }

    public void clientStreamingOperaions(ManagedChannel channel) {
//        doGreetClientStreaming(channel);

        doAverageOfNumbersClientStreaming(channel);
    }

    private void doAverageOfNumbersClientStreaming(ManagedChannel channel) {
        CalculatorGrpc.CalculatorStub calClient = CalculatorGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<Request> requestStreamObserver = calClient.getAverage(new StreamObserver<Response>() {
            @Override
            public void onNext(Response response) {
                System.out.println("the average sum from the server is :" + response.getAvg());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        requestStreamObserver.onNext(Request.newBuilder().setNumbers(1).build());
        requestStreamObserver.onNext(Request.newBuilder().setNumbers(2).build());
        requestStreamObserver.onNext(Request.newBuilder().setNumbers(3).build());
        requestStreamObserver.onNext(Request.newBuilder().setNumbers(4).build());

        System.out.println("sent all numbers bro ");

        requestStreamObserver.onCompleted();

        try {
            latch.await(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doGreetClientStreaming(ManagedChannel channel) {
//        //sync method in this we cannot call client straming
//        GreetServiceGrpc.GreetServiceBlockingStub greetServiceBlockingStub = GreetServiceGrpc.newBlockingStub(channel);

        //async client
        GreetServiceGrpc.GreetServiceStub asyncGrpcClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<Greeting.GreetRequest> requestStreamObserver = asyncGrpcClient.longGreet(new StreamObserver<Greeting.GreetResponse>() {
            @Override
            public void onNext(Greeting.GreetResponse greetResponse) {
                // we get a response from the server
                // onNext will be called only once
                System.out.println("received response from the server");
                System.out.println(greetResponse.getResult());
            }

            @Override
            public void onError(Throwable throwable) {
                // we get an error from the server
            }

            @Override
            public void onCompleted() {
                // the server is done sending us data
                // onCompleted will be called right after onNext()
                System.out.println("Server has completed sending us data");
                latch.countDown();
            }
        });

        // streaming message #1
        requestStreamObserver.onNext(Greeting.GreetRequest
                                             .newBuilder()
                                             .setGreetings(Greeting.GreetingName.newBuilder()
                                                                                .setFirstName("sai"))
                                             .build());

        // streaming message #2
        requestStreamObserver.onNext(Greeting.GreetRequest
                                             .newBuilder()
                                             .setGreetings(Greeting.GreetingName.newBuilder()
                                                                                .setFirstName("sai"))
                                             .build());

        // streaming message #3
        requestStreamObserver.onNext(Greeting.GreetRequest
                                             .newBuilder()
                                             .setGreetings(Greeting.GreetingName.newBuilder()
                                                                                .setFirstName("sai"))
                                             .build());

        requestStreamObserver.onCompleted();


        try {
            latch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
