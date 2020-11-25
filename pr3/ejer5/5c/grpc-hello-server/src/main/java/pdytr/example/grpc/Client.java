package pdytr.example.grpc;

import io.grpc.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;

public class Client
{


    public static void main( String[] args ) throws Exception
    {
      // Channel is the abstraction to connect to a service endpoint
      // Let's use plaintext communication because we don't have certs
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("172.17.0.2:8080")
        .usePlaintext(true)
        .build();

      Scanner teclado = new Scanner(System.in);
      System.out.println("ingrese el numero");
      int pos = teclado. nextInt();
      System.out.println(pos);
      int deadlineMs = pos;


      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      GreetingServiceOuterClass.Time request = GreetingServiceOuterClass.Time.newBuilder().setTime(40).build();
      // Finally, make the call using the stub   
      GreetingServiceOuterClass.Response response = stub.withDeadlineAfter(deadlineMs, TimeUnit.MILLISECONDS).greeting(request);
 
      // A Channel should be shutdown before stopping the process.

      channel.shutdownNow();
    }
}