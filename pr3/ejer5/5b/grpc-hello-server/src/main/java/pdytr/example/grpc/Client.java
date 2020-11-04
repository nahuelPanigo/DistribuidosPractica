package pdytr.example.grpc;
import io.grpc.*;

import io.grpc.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;
import io.grpc.Deadline;


public class Client
{
    public static void main( String[] args ) throws Exception
    {

      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();

      double prom =4025.83333;
      double prom =50000;
      int arg = Integer. parseInt(args[1]);
      double lineMs =prom-(prom*arg*0.1);
      int deadlineMs = (int)lineMs;
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      GreetingServiceOuterClass.Time request = GreetingServiceOuterClass.Time.newBuilder().setTime(500).build();
      
      GreetingServiceOuterClass.Response response = stub.withDeadlineAfter(deadlineMs, TimeUnit.MILLISECONDS).greeting(request);

      // A Channel should be shutdown before stopping the process.

      channel.shutdownNow();
    }
}