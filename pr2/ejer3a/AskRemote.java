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

public static void main(String[] args)
{
/* Look for hostname and msg length in the command line */
System.setProperty("java.rmi.server.hostname","172.17.0.2");
if (args.length != 1)
{
System.out.println("1 argument needed: (remote) hostname");
System.exit(1);
}
try {
String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
IfaceRemoteClass remote = (IfaceRemoteClass) Naming.lookup(rname);


Scanner teclado = new Scanner(System.in);
System.out.println("ingrese el nombre del archivo");
String nombre = teclado. nextLine();
System.out.println("ingrese opcion 1 (put) u opcion 2 (get)");
int opcion = teclado. nextInt();

if (opcion == 1) {
	System.out.println("ingrese nombre del archivo de donde desea sacar la informacion");
    String archive = teclado. nextLine();
    archive = teclado. nextLine();
    AskRemote a = new AskRemote();
    byte [] bytes = a.leer(archive);
    int cant = bytes.length;
	int n = remote.put(nombre, bytes, cant);
	System.out.println("La cantidad de caracteres que se escribio fue " + n);
}
else {
	System.out.println("ingrese la posicion desde donde leer ");
	int pos = teclado. nextInt();
	System.out.println("ingrese la cantidad de bytes que quiere leer ");
	int cantB = teclado. nextInt();
	byte [] bytes = remote.get(nombre, pos, cantB);
	System.out.println("La cantidad de caracteres que se leyo fue " + bytes.length);
}
} catch (Exception e) {
e.printStackTrace();
}
}
}