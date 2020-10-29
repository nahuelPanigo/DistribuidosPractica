package pdytr.example.grpc;

import io.grpc.stub.StreamObserver;



public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {


  @Override
  public void greeting(GreetingServiceOuterClass.User request,
        StreamObserver<GreetingServiceOuterClass.Response> responseObserver) {
    // HelloRequest has toString auto-generated.
    System.out.println(request);
    // se implementa el proto. tiene el request de entrada
    String username= request.getUsername();
    String password= request.getPassword();
    String mensaje;
    if((username.equals("nahuel"))&&(password.equals("123"))){
       mensaje="correcto";
    }else{
       mensaje="incorrecto el usuario y /o la contra";
    }
    // You must use a builder to construct a new Protobuffer object
    GreetingServiceOuterClass.Response response = GreetingServiceOuterClass.Response.newBuilder()
      .setMensaje(mensaje)
      .build();
      // retorna el response con el nombre
    // Use responseObserver to send a single response back
    responseObserver.onNext(response);

    // When you are done, you must call onCompleted.
    responseObserver.onCompleted();
  }
}