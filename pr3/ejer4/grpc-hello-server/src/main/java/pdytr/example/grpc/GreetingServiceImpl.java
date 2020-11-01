package pdytr.example.grpc;

import io.grpc.stub.StreamObserver;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import com.google.protobuf.ByteString;

 
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

public int lastPos(int posA, int posB) {
  if (posA < posB){
    return posA;
    }
  return posB;

}

public byte[] leer (String name, int pos, int cantData){
    File archivo = new File (name);
    byte[] fileArray = new byte[(int) archivo.length()];
    int last= this.lastPos(fileArray.length, pos + cantData);
    int max = (last-pos > 0) ? last-pos : 0;
    byte[] fileArray2 = new byte[max];
    try{
      if (archivo.isFile()){
        FileInputStream fileInputStream = new FileInputStream(archivo);
        fileInputStream.read(fileArray);
        int j = 0;
        for (int i = pos; i < last; i++) {
          fileArray2[j] = fileArray[i];
          j++;
        }
        fileInputStream.close();      
      }
      
    }catch(Exception e){
       System.out.println("no se pudo leer el archivo");
    } 
    return fileArray2;
}

public int escribir (String name, int cant, byte [] buf){
     try{
      File archivo = new File (name);
      archivo.createNewFile();
      FileOutputStream fileOuputStream = new FileOutputStream(archivo);
      fileOuputStream.write(buf);
      fileOuputStream.close();
    } catch (Exception e) {
        System.out.println("no se pudo escribir el archivo");
    }
      return buf.length;
}



  @Override
  public void read(GreetingServiceOuterClass.Leer request,
        StreamObserver<GreetingServiceOuterClass.DevolverLeer> responseObserver){
        try{    
            byte[] bytes = this.leer(request.getName(), request.getPos(), request.getCant());
            GreetingServiceOuterClass.DevolverLeer response = GreetingServiceOuterClass.DevolverLeer.newBuilder().setCantLeida(bytes.length).setCantAleer(request.getCant()).build();  
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e){
          System.out.println("exception");
        }
  }


  @Override
  public void write(GreetingServiceOuterClass.Escribir request,
        StreamObserver<GreetingServiceOuterClass.DevolverEscribir> responseObserver){
          try{ 
              int cantE = this.escribir(request.getName(),request.getCant(),request.getBuf().toByteArray());
              GreetingServiceOuterClass.DevolverEscribir response = GreetingServiceOuterClass.DevolverEscribir.newBuilder().setCant(cantE).build();
              responseObserver.onNext(response);
              responseObserver.onCompleted();
          } catch (Exception e){
              System.out.println("exception");
          }
  }
}