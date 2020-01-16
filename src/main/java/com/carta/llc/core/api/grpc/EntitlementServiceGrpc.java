package com.carta.llc.core.api.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: EntitlementService.proto")
public final class EntitlementServiceGrpc {

  private EntitlementServiceGrpc() {}

  public static final String SERVICE_NAME = "com.carta.llc.core.api.grpc.EntitlementService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.carta.llc.core.api.grpc.EntitlementServiceRequest,
      com.carta.llc.core.api.grpc.EntitlementServiceResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = com.carta.llc.core.api.grpc.EntitlementServiceRequest.class,
      responseType = com.carta.llc.core.api.grpc.EntitlementServiceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.carta.llc.core.api.grpc.EntitlementServiceRequest,
      com.carta.llc.core.api.grpc.EntitlementServiceResponse> getGetMethod() {
    io.grpc.MethodDescriptor<com.carta.llc.core.api.grpc.EntitlementServiceRequest, com.carta.llc.core.api.grpc.EntitlementServiceResponse> getGetMethod;
    if ((getGetMethod = EntitlementServiceGrpc.getGetMethod) == null) {
      synchronized (EntitlementServiceGrpc.class) {
        if ((getGetMethod = EntitlementServiceGrpc.getGetMethod) == null) {
          EntitlementServiceGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<com.carta.llc.core.api.grpc.EntitlementServiceRequest, com.carta.llc.core.api.grpc.EntitlementServiceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.carta.llc.core.api.grpc.EntitlementServiceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.carta.llc.core.api.grpc.EntitlementServiceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EntitlementServiceMethodDescriptorSupplier("get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EntitlementServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceStub>() {
        @java.lang.Override
        public EntitlementServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EntitlementServiceStub(channel, callOptions);
        }
      };
    return EntitlementServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EntitlementServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceBlockingStub>() {
        @java.lang.Override
        public EntitlementServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EntitlementServiceBlockingStub(channel, callOptions);
        }
      };
    return EntitlementServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EntitlementServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EntitlementServiceFutureStub>() {
        @java.lang.Override
        public EntitlementServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EntitlementServiceFutureStub(channel, callOptions);
        }
      };
    return EntitlementServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EntitlementServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.carta.llc.core.api.grpc.EntitlementServiceRequest request,
        io.grpc.stub.StreamObserver<com.carta.llc.core.api.grpc.EntitlementServiceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.carta.llc.core.api.grpc.EntitlementServiceRequest,
                com.carta.llc.core.api.grpc.EntitlementServiceResponse>(
                  this, METHODID_GET)))
          .build();
    }
  }

  /**
   */
  public static final class EntitlementServiceStub extends io.grpc.stub.AbstractAsyncStub<EntitlementServiceStub> {
    private EntitlementServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EntitlementServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EntitlementServiceStub(channel, callOptions);
    }

    /**
     */
    public void get(com.carta.llc.core.api.grpc.EntitlementServiceRequest request,
        io.grpc.stub.StreamObserver<com.carta.llc.core.api.grpc.EntitlementServiceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EntitlementServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EntitlementServiceBlockingStub> {
    private EntitlementServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EntitlementServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EntitlementServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.carta.llc.core.api.grpc.EntitlementServiceResponse get(com.carta.llc.core.api.grpc.EntitlementServiceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EntitlementServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EntitlementServiceFutureStub> {
    private EntitlementServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EntitlementServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EntitlementServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.carta.llc.core.api.grpc.EntitlementServiceResponse> get(
        com.carta.llc.core.api.grpc.EntitlementServiceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EntitlementServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EntitlementServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.carta.llc.core.api.grpc.EntitlementServiceRequest) request,
              (io.grpc.stub.StreamObserver<com.carta.llc.core.api.grpc.EntitlementServiceResponse>) responseObserver);
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

  private static abstract class EntitlementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EntitlementServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.carta.llc.core.api.grpc.EntitlementServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EntitlementService");
    }
  }

  private static final class EntitlementServiceFileDescriptorSupplier
      extends EntitlementServiceBaseDescriptorSupplier {
    EntitlementServiceFileDescriptorSupplier() {}
  }

  private static final class EntitlementServiceMethodDescriptorSupplier
      extends EntitlementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EntitlementServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EntitlementServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EntitlementServiceFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
