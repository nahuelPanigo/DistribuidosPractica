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
		byte[] bytes = "holacomoes".getBytes();
		int cant = bytes.length;
		int numero = remote.put("/pdytr/pr2/nuevo.txt",bytes,cant);
		System.out.println("se pudo hacer y me devolvio "+ numero);

}catch (Exception e) {
	e.printStackTrace();
}
}
}