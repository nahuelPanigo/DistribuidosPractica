package pdytr.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: GreetingService.proto")
public final class GreetingServiceGrpc {

  private GreetingServiceGrpc() {}

  public static final String SERVICE_NAME = "pdytr.example.grpc.GreetingService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.GreetingServiceOuterClass.Leer,
      pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer> METHOD_READ =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.GreetingServiceOuterClass.Leer, pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "pdytr.example.grpc.GreetingService", "read"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.GreetingServiceOuterClass.Leer.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer.getDefaultInstance()))
          .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("read"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.GreetingServiceOuterClass.Escribir,
      pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir> METHOD_WRITE =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.GreetingServiceOuterClass.Escribir, pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "pdytr.example.grpc.GreetingService", "write"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.GreetingServiceOuterClass.Escribir.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir.getDefaultInstance()))
          .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("write"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingServiceStub newStub(io.grpc.Channel channel) {
    return new GreetingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreetingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void read(pdytr.example.grpc.GreetingServiceOuterClass.Leer request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_READ, responseObserver);
    }

    /**
     */
    public void write(pdytr.example.grpc.GreetingServiceOuterClass.Escribir request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_WRITE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_READ,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.GreetingServiceOuterClass.Leer,
                pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer>(
                  this, METHODID_READ)))
          .addMethod(
            METHOD_WRITE,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.GreetingServiceOuterClass.Escribir,
                pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir>(
                  this, METHODID_WRITE)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingServiceStub extends io.grpc.stub.AbstractStub<GreetingServiceStub> {
    private GreetingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceStub(channel, callOptions);
    }

    /**
     */
    public void read(pdytr.example.grpc.GreetingServiceOuterClass.Leer request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void write(pdytr.example.grpc.GreetingServiceOuterClass.Escribir request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WRITE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingServiceBlockingStub extends io.grpc.stub.AbstractStub<GreetingServiceBlockingStub> {
    private GreetingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer read(pdytr.example.grpc.GreetingServiceOuterClass.Leer request) {
      return blockingUnaryCall(
          getChannel(), METHOD_READ, getCallOptions(), request);
    }

    /**
     */
    public pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir write(pdytr.example.grpc.GreetingServiceOuterClass.Escribir request) {
      return blockingUnaryCall(
          getChannel(), METHOD_WRITE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingServiceFutureStub extends io.grpc.stub.AbstractStub<GreetingServiceFutureStub> {
    private GreetingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer> read(
        pdytr.example.grpc.GreetingServiceOuterClass.Leer request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_READ, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir> write(
        pdytr.example.grpc.GreetingServiceOuterClass.Escribir request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WRITE, getCallOptions()), request);
    }
  }

  private static final int METHODID_READ = 0;
  private static final int METHODID_WRITE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreetingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_READ:
          serviceImpl.read((pdytr.example.grpc.GreetingServiceOuterClass.Leer) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverLeer>) responseObserver);
          break;
        case METHODID_WRITE:
          serviceImpl.write((pdytr.example.grpc.GreetingServiceOuterClass.Escribir) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.GreetingServiceOuterClass.DevolverEscribir>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreetingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pdytr.example.grpc.GreetingServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreetingService");
    }
  }

  private static final class GreetingServiceFileDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier {
    GreetingServiceFileDescriptorSupplier() {}
  }

  private static final class GreetingServiceMethodDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreetingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreetingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingServiceFileDescriptorSupplier())
              .addMethod(METHOD_READ)
              .addMethod(METHOD_WRITE)
              .build();
        }
      }
    }
    return result;
  }
}
