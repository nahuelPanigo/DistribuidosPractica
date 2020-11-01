package pdytr.example.grpc;
import io.grpc.*;


public class App
{
    public static void main( String[] args ) throws Exception
    {
      
      // Create a new server to listen on port 8080
      Server server = ServerBuilder.forPort(8080)
        .addService(new GreetingServiceImpl())
        .build();
      // Start the server
      // arma un servidor q le dice en que puerto lo quiere tener
     try{ // le agrega el servicio de rpc, lo startea y espera
      server.start();
      // Server threads are running in the background.
      System.out.println("Server started");
      // Don't exit the main thread. Wait until server is terminated.
      } catch (Exception e) {
            System.out.println("llego a la excepcion");
          
    }
    server.awaitTermination();
  }
}