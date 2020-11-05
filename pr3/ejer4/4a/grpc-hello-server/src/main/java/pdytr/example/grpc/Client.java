package pdytr.example.grpc;
import io.grpc.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import io.grpc.*;
import com.google.protobuf.ByteString;
import java.util.Scanner;


public class Client
{

  private byte[] leer (String name){
    File archivo = new File (name);
    byte[] fileArray = new byte[(int) archivo.length()];
    try{
      if (archivo.isFile()){
          FileInputStream fileInputStream = new FileInputStream(archivo);
          fileInputStream.read(fileArray);
          int j = 0;
          fileInputStream.close();      
      }
    }catch(Exception e){
       System.out.println("no se pudo leer el archivo");
    } 
    return fileArray;
  }

    public static void main( String[] args ) throws Exception
    {
      Scanner teclado = new Scanner(System.in);
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();
        
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      
      System.out.println("ingrese nombre del archivo que desea leer o escribir");
      String nombre = teclado. nextLine();
      
      System.out.println("Ingrese opcion 1 (escribir) opcion 2 (leer)");
      int op = teclado. nextInt();
      
      if ( op == 1) {
        /*
          String  texto= "la cantidad de caracteres del texto son 43\n";
          byte[] bytes = texto.getBytes();
                  */

          System.out.println("ingrese nombre del archivo que desea leer o escribir");
          String archive = teclado. nextLine();
          archive = teclado. nextLine();
          Client c = new Client();
          byte [] bytes = c.leer(archive);
          ByteString buf = ByteString.copyFrom(bytes);
          int cant = bytes.length;
          GreetingServiceOuterClass.Escribir request = GreetingServiceOuterClass.Escribir.newBuilder().setName(nombre).setBuf(buf).setCant(cant).build();
          GreetingServiceOuterClass.DevolverEscribir response = stub.write(request);
          System.out.println("la cantidad de bytes que se escribio fue: "+ response.getCant());
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