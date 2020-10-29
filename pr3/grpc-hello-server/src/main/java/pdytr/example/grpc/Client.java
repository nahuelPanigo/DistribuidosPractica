package pdytr.example.grpc;
import io.grpc.*;

import io.grpc.*;

public class Client
{
    public static void main( String[] args ) throws Exception
    {
      // Channel is the abstraction to connect to a service endpoint
      // Let's use plaintext communication because we don't have certs
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();

      // crea un canal que va a estar en el localost e el puerto q defini en el servidor
      //se genera un stub (puede ser asincorinico o sincronico, aca es sincronico)
      // se genera el request diciendole q hace el setName() y lo buildea y espera la respuesta con ese response
      // imprime la respuesta q le llego y hace un cierre de la comunicacion.
      // It is up to the client to determine whether to block the call
      // Here we create a blocking stub, but an async stub,
      // or an async stub with Future are always possible.
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      GreetingServiceOuterClass.User request = GreetingServiceOuterClass.User.newBuilder().setUsername("nahuel").setPassword("123").build();
      System.out.println(request);
      // Finally, make the call using the stub
      GreetingServiceOuterClass.Response response = 
        stub.greeting(request);

      System.out.println(response);
      // A Channel should be shutdown before stopping the process.
      channel.shutdownNow();
    }
}