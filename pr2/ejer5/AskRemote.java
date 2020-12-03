/*
* AskRemote.java
* a) Looks up for the remote object
* b) "Makes" the RMI
*/
import java.rmi.Naming; /* lookup */
import java.rmi.registry.Registry; /* REGISTRY_PORT */
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;
public class AskRemote
{

 public void setTimeInTxt(long timeCommSeg){
        try {
        File archivo = new File ("tiempo.txt");
        if (!archivo.isFile()){
        	archivo.createNewFile();
    	}
    	double times = Long.valueOf(timeCommSeg).doubleValue();
        String str = Double.toString(times);
        str+="\n";
        byte[] bytes = str.getBytes();
        FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
        fileOuputStream.write(bytes);
        fileOuputStream.close();
        }catch(Exception e){
          System.out.println("error handling");
        }
    }

public static void main(String[] args)
{
/* Look for hostname and msg length in the command line */
if (args.length != 1)
{
System.out.println("1 argument needed: (remote) hostname");
System.exit(1);
}
try {
String rname = "//" + args[0] + ":" + Registry.REGISTRY_PORT + "/remote";
IfaceRemoteClass remote = (IfaceRemoteClass) Naming.lookup(rname);
int bufferlength = 100;
byte[] buffer = new byte[bufferlength];
long startTime = System.currentTimeMillis();
remote.sendThisBack(buffer);
long timeCommunication = ((System.currentTimeMillis()-startTime)/2);
AskRemote cliente = new AskRemote();
cliente.setTimeInTxt(timeCommunication);
//System.out.println(timeCommunication);
} catch (Exception e) {
e.printStackTrace();
}
}
}