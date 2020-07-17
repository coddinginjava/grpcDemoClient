package proto.sum;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: sum.proto")
public final class AdditionGrpc {

  private AdditionGrpc() {}

  public static final String SERVICE_NAME = "Addition";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Sum.Request,
      Sum.Response> getAddThemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addThem",
      requestType = Sum.Request.class,
      responseType = Sum.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Sum.Request,
      Sum.Response> getAddThemMethod() {
    io.grpc.MethodDescriptor<Sum.Request, Sum.Response> getAddThemMethod;
    if ((getAddThemMethod = AdditionGrpc.getAddThemMethod) == null) {
      synchronized (AdditionGrpc.class) {
        if ((getAddThemMethod = AdditionGrpc.getAddThemMethod) == null) {
          AdditionGrpc.getAddThemMethod = getAddThemMethod = 
              io.grpc.MethodDescriptor.<Sum.Request, Sum.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Addition", "addThem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Sum.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Sum.Response.getDefaultInstance()))
                  .setSchemaDescriptor(new AdditionMethodDescriptorSupplier("addThem"))
                  .build();
          }
        }
     }
     return getAddThemMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdditionStub newStub(io.grpc.Channel channel) {
    return new AdditionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdditionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AdditionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdditionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AdditionFutureStub(channel);
  }

  /**
   */
  public static abstract class AdditionImplBase implements io.grpc.BindableService {

    /**
     */
    public void addThem(Sum.Request request,
                        io.grpc.stub.StreamObserver<Sum.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getAddThemMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddThemMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Sum.Request,
                Sum.Response>(
                  this, METHODID_ADD_THEM)))
          .build();
    }
  }

  /**
   */
  public static final class AdditionStub extends io.grpc.stub.AbstractStub<AdditionStub> {
    private AdditionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdditionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdditionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdditionStub(channel, callOptions);
    }

    /**
     */
    public void addThem(Sum.Request request,
                        io.grpc.stub.StreamObserver<Sum.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddThemMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AdditionBlockingStub extends io.grpc.stub.AbstractStub<AdditionBlockingStub> {
    private AdditionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdditionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdditionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdditionBlockingStub(channel, callOptions);
    }

    /**
     */
    public Sum.Response addThem(Sum.Request request) {
      return blockingUnaryCall(
          getChannel(), getAddThemMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AdditionFutureStub extends io.grpc.stub.AbstractStub<AdditionFutureStub> {
    private AdditionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdditionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdditionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdditionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Sum.Response> addThem(
        Sum.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getAddThemMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_THEM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdditionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdditionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_THEM:
          serviceImpl.addThem((Sum.Request) request,
              (io.grpc.stub.StreamObserver<Sum.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AdditionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdditionBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Sum.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Addition");
    }
  }

  private static final class AdditionFileDescriptorSupplier
      extends AdditionBaseDescriptorSupplier {
    AdditionFileDescriptorSupplier() {}
  }

  private static final class AdditionMethodDescriptorSupplier
      extends AdditionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdditionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdditionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdditionFileDescriptorSupplier())
              .addMethod(getAddThemMethod())
              .build();
        }
      }
    }
    return result;
  }
}
