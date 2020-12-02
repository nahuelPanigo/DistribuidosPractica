/*
* AskRemote.java	CLIENTE
* a) Looks up for the remote object
* b) "Makes" the RMI
*/
import java.rmi.Naming; /* lookup */
import java.rmi.registry.Registry; /* REGISTRY_PORT */
import java.util.Scanner;

public class AskRemote
{
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
int bufferlength = 100;
byte[] bytes = "holacomoes".getBytes();
int cant = bytes.length;
Scanner teclado = new Scanner(System.in);
System.out.println("ingrese el nombre del archivo");
String nombre = teclado. nextLine();
System.out.println("ingrese opcion 1 (put) u opcion 2 (get)");
int opcion = teclado. nextInt();
if (opcion == 1) {
	int n = remote.put(nombre, bytes, cant);
	System.out.println("La cantidad de caracteres que se escribio fue " + n);
}
else {
	System.out.println("ingrese la posicion desde donde leer ");
	int pos = teclado. nextInt();
	System.out.println("ingrese la cantidad de bytes que quiere leer ");
	int cantB = teclado. nextInt();
	bytes = remote.get(nombre, pos, cantB);
	System.out.println("La cantidad de caracteres que se leyo fue " + bytes.length);
}
} catch (Exception e) {
e.printStackTrace();
}
}
}