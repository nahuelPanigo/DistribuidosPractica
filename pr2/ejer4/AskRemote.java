/*
* AskRemote.java	CLIENTE
* a) Looks up for the remote object
* b) "Makes" the RMI
*/
import java.rmi.Naming; /* lookup */
import java.rmi.registry.Registry; /* REGISTRY_PORT */
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class AskRemote
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


  private int cantIter(int total, int tam){
    if ((tam % total) == 0) return tam/total;
    return (tam/total) + 1;
  }


  private byte[] devolverBytes(int it, byte[] buf){
     byte [] buf2;
     int j=0;
     int fin = buf.length;
     int principio = it * 100000;
     if ((principio + 100000) <= buf.length ){
      buf2 = new byte [100000];
      fin = principio + 100000;
     }
     else{   
      buf2 = new byte [100000 - ((principio + 100000) - buf.length)];
    }
    for (int i= principio; i< fin; i++){
        buf2[j]= buf[i];
        j++;
    }
    return buf2;
  }

public static void main(String[] args)
{
int cantBytes = 100;
/* Look for hostname and msg length in the command line */
System.setProperty("java.rmi.server.hostname","172.17.0.2");
if (args.length != 1)
{
System.out.println("1 argument needed: (remote) hostname");
System.exit(1);
}
try {
		String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
		//System.out.println("hasta aca llega 0");
		IfaceRemoteClass remote = (IfaceRemoteClass) Naming.lookup(rname);
		Scanner teclado = new Scanner(System.in);
		System.out.println("ingrese nombre del archivo de donde desea sacar la informacion");
        String archive = teclado. nextLine();
        AskRemote a = new AskRemote();
        byte [] bytes = a.leer(archive);
        int cant = bytes.length;
        int totalEscritos = 0;
        int total = a.cantIter(cantBytes, cant);
        for (int i=0; i<total; i++){
            byte[] datos= a.devolverBytes(i, bytes);
            totalEscritos += remote.put("/pdytr/pr2/nuevo.txt",datos,datos.length);
        }
		System.out.println("se pudo hacer y me devolvio "+ totalEscritos);

}catch (Exception e) {
	e.printStackTrace();
}
}
}