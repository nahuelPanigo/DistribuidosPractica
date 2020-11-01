package pdytr.example.grpc;
import io.grpc.*;
import java.io.File;
import java.io.FileOutputStream;
import io.grpc.*;
import com.google.protobuf.ByteString;
import java.util.Scanner;


public class Client
{
    public static void main( String[] args ) throws Exception
    {
      // Channel is the abstraction to connect to a service endpoint
      // Let's use plaintext communication because we don't have certs
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();
        
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

      Scanner teclado = new Scanner(System.in);
      System.out.println("ingrese nombre del archivo que desea leer o escribir");
      String nombre = teclado. nextLine();
      System.out.println("Ingrese opcion 1 (escribir) opcion 2 (leer)");
      int op = teclado. nextInt();
      if ( op == 1) {
         
          byte[] bytes = " Texto que se agrega al final si existe el archivo".getBytes();
          int cant = bytes.length;
          ByteString buf = ByteString.copyFrom(bytes);
          GreetingServiceOuterClass.Escribir request = GreetingServiceOuterClass.Escribir.newBuilder().setName(nombre).setBuf(buf).setCant(cant).build();
          GreetingServiceOuterClass.DevolverEscribir response = stub.write(request);
          System.out.println("la cantidad de bytes que se escribio fue: "+ response);
      }else {
          
          System.out.println("ingrese la posicion desde donde desea leer");
          int pos = teclado. nextInt();
          System.out.println("ingrese la cantidad de bytes que quiere leer");
          int cant = teclado. nextInt();
          GreetingServiceOuterClass.Leer request = GreetingServiceOuterClass.Leer.newBuilder().setName(nombre).setPos(pos).setCant(cant).build();
          GreetingServiceOuterClass.DevolverLeer response = stub.read(request);
          System.out.println("la cantidad de bytes que se leyo fue: " + response.getCantLeida());
      }
    
      // A Channel should be shutdown before stopping the process.
      channel.shutdownNow();
    }
}