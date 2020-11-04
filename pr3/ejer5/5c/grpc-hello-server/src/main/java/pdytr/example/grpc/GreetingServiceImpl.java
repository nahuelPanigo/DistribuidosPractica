package pdytr.example.grpc;

import io.grpc.stub.StreamObserver;



public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  @Override
  public void greeting(GreetingServiceOuterClass.Time request,
        StreamObserver<GreetingServiceOuterClass.Response> responseObserver) {

    try{
    int time=request.getTime();
    // You must use a builder to construct a new Protobuffer object
    GreetingServiceOuterClass.Response response = GreetingServiceOuterClass.Response.newBuilder()
      .setMensaje("termino")
      .build();

    Thread.sleep(time);
  
    
      // retorna el response con el nombre
    // Use responseObserver to send a single response back
    responseObserver.onNext(response);
    // When you are done, you must call onCompleted.
    responseObserver.onCompleted();

      }catch(Exception e){
      System.out.println("hubo un error");
    }
  }
}