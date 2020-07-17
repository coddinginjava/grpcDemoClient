package sai.service;

import io.grpc.ManagedChannel;
import proto.prime.Prime;
import proto.prime.PrimeNumberDecompositionGrpc;
import proto.prime.PrimeNumberDecompositionGrpc.PrimeNumberDecompositionBlockingStub;

import java.util.Iterator;

public class ClientServiceImpl {

    public void getClientInfo(ManagedChannel channel) {
        getPrimeNumberDecomposition(channel);


    }

    private void getPrimeNumberDecomposition(ManagedChannel channel) {
        PrimeNumberDecompositionBlockingStub primeClient = PrimeNumberDecompositionGrpc.newBlockingStub(channel);

        Prime.Request request = Prime.Request.newBuilder().setN(567890304).build();

        Iterator<Prime.Response> primeNumberDecomposition = primeClient.getAll(request);

        primeNumberDecomposition.forEachRemaining(System.out::println);
    }
}
