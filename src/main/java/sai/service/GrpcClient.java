package sai.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.greet.GreetServiceGrpc;
import proto.greet.Greeting;

import java.io.IOException;
import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000)
                                                      .usePlaintext()
                                                      .build();

        System.out.println("creating stub");
        /*GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting.GreetingName greetingName = Greeting.GreetingName.newBuilder()
                                                                  .setFirstName("sai")
                                                                  .setLastName("prakash")
                                                                  .build();

        Greeting.GreetRequest request = Greeting.GreetRequest.newBuilder()
                                                             .setGreetings(greetingName)
                                                             .build();

        Greeting.GreetResponse response = greetClient.greet(request);*/

        /*AdditionGrpc.AdditionBlockingStub addingClient = AdditionGrpc.newBlockingStub(channel);

        Sum.Request request = Sum.Request.newBuilder().setA(3).setB(10).build();

        Sum.Response response = addingClient.addThem(request);

        System.out.println("sum -> " + response.getC());*/

        GreetServiceGrpc.GreetServiceBlockingStub grpcClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting.GreetRequest request = Greeting.GreetRequest.newBuilder()
                                                             .setGreetings(Greeting.GreetingName.newBuilder().setFirstName("STG"))
                                                             .build();

        Iterator<Greeting.GreetResponse> greetResponseIterator = grpcClient.greetManyTimes(request);

        greetResponseIterator.forEachRemaining(System.out::println);

        System.out.println("shuting down channel");
        channel.shutdown();
    }
}
