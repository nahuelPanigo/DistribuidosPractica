package pdytr.example.grpc;

import io.grpc.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;

public class Client
{

    public void setTimeInTxt(float timeCommSeg){
        try {
        File archivo = new File ("nuevo.txt");
        archivo.createNewFile();
        String str = Float.toString(timeCommSeg);
        str+="\n";
        byte[] bytes = str.getBytes();
        FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
        fileOuputStream.write(bytes);
        fileOuputStream.close();
        }catch(Exception e){
          System.out.println("error handling");
        }
    }
    public static void main( String[] args ) throws Exception
    {
      // Channel is the abstraction to connect to a service endpoint
      // Let's use plaintext communication because we don't have certs
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();
      int deadlineMs = 4000;
      int time = 4000;
      
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      GreetingServiceOuterClass.Time request = GreetingServiceOuterClass.Time.newBuilder().setTime(time).build();
      // Finally, make the call using the stub   
      long start = System.currentTimeMillis();
      GreetingServiceOuterClass.Response response = stub.withDeadlineAfter(deadlineMs, TimeUnit.MILLISECONDS).greeting(request);
      long timeCommunication = System.currentTimeMillis()-start;
      float timeCommSeg = (timeCommunication-time/2)/1000F;
      Client c =new Client();
      c.setTimeInTxt(timeCommSeg);
      // A Channel should be shutdown before stopping the process.

      channel.shutdownNow();
    }
}