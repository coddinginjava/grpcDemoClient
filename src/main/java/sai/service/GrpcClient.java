package sai.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        GrpcClient grpcClient = new GrpcClient();
        grpcClient.runClient();

    }

    private void runClient() {
        System.out.println("Starting the client");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000)
                                                      .usePlaintext()
                                                      .build();
//        System.out.println("Unary Operations");
//        new ClientServiceImpl().unaryOperations(channel);

//        System.out.println("Server Streaming Operations");
//        new ClientServiceImpl().serverStreamingOperations(channel);

        System.out.println("Client side Streaming operations");
        new ClientServiceImpl().clientStreamingOperaions(channel);


        System.out.println("shutting down channel");
        channel.shutdown();
    }
}
