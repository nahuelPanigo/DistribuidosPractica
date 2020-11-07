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
  static int cantBytes =100;
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


  private int cantIter(int total, int tam){
    if ((tam % total) == 0) return tam/total;
    return (tam/total) + 1;
  }


  private byte[] devolverBytes(int it, byte[] buf){
     byte [] buf2;
     int j=0;
     int fin = buf.length;
     int principio = it * cantBytes;
     if ((principio + cantBytes) <= buf.length ){
      buf2 = new byte [cantBytes];
      fin = principio + cantBytes;
     }
     else{   
      buf2 = new byte [cantBytes - ((principio + cantBytes) - buf.length)];
    }
    for (int i= principio; i< fin; i++){
        buf2[j]= buf[i];
        j++;
    }
    return buf2;
  }

    public static void main( String[] args ) throws Exception
    {
      Scanner teclado = new Scanner(System.in);
      final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext(true)
        .build();
        
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      
      System.out.println("ingrese el nombre del archivo que quiere crear/sobreescribir");
      String nombre = teclado. nextLine();
      
      System.out.println("Ingrese opcion 1 (escribir) opcion 2 (leer)");
      int op = teclado. nextInt();
      
      if ( op == 1) {
     
          System.out.println("ingrese nombre del archivo de donde desea sacar la informacion");
          String archive = teclado. nextLine();
          archive = teclado. nextLine();
          Client c = new Client();
          byte [] bytes = c.leer(archive);
          int cant = bytes.length;
          int totalEscritos = 0;
          int total = c.cantIter(cantBytes, cant);
          for (int i=0; i<total; i++){
              byte[] datos= c.devolverBytes(i, bytes);
              ByteString buf = ByteString.copyFrom(datos);
              GreetingServiceOuterClass.Escribir request = GreetingServiceOuterClass.Escribir.newBuilder().setName(nombre).setBuf(buf).setCant(cant).build();
              GreetingServiceOuterClass.DevolverEscribir response = stub.write(request);
              totalEscritos += response.getCant();
          }
          
          System.out.println("la cantidad de bytes que se escribio fue: "+ totalEscritos);
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