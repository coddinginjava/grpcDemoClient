package sai.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.greet.GreetServiceGrpc;
import proto.greet.Greeting;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000)
                                                      .usePlaintext()
                                                      .build();

        System.out.println("creating stub");
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting.GreetingName greetingName = Greeting.GreetingName.newBuilder()
                                                                  .setFirstName("sai")
                                                                  .setLastName("prakash")
                                                                  .build();

        Greeting.GreetRequest request = Greeting.GreetRequest.newBuilder()
                                                             .setGreetings(greetingName)
                                                             .build();

        for (int i = 0; i < 20; i++) {
            Greeting.GreetResponse response = greetClient.greet(request);
            System.out.println("response = " + response);
        }


        System.out.println("shuting down channel");
        channel.shutdown();
    }
}
