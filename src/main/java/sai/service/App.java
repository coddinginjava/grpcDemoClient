package sai.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.sum.AdditionGrpc;
import proto.sum.Sum;

import java.io.IOException;

public class App {
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

        AdditionGrpc.AdditionBlockingStub addingClient = AdditionGrpc.newBlockingStub(channel);

        Sum.Request request = Sum.Request.newBuilder().setA(3).setB(10).build();

        Sum.Response response = addingClient.addThem(request);

        System.out.println("sum -> " + response.getC());

        System.out.println("shuting down channel");
        channel.shutdown();
    }
}
