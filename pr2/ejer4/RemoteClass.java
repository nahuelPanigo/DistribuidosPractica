/*
* RemoteClass.java
* Just implements the RemoteMethod interface as an extension to
* UnicastRemoteObject
*
*/
/* Needed for implementing remote method/s */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.nio.*;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


/* This class implements the interface with remote methods */
public class RemoteClass extends UnicastRemoteObject implements IfaceRemoteClass
{
protected RemoteClass() throws RemoteException
{
super();
}
/* Remote method implementation */
public byte[] sendThisBack(byte[] data) throws RemoteException
{
System.out.println("Data back to client");
return data;
}


public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}


public int comunicacionConcurrente() throws RemoteException
{
	this.wait(100);
	return -1;
}



public int put(String  archive, byte[] data, int length) throws RemoteException{

		try{
			File archivo = new File (archive);
			archivo.createNewFile();
			this.wait(10000);
			FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}	
	return data.length;
}




}
