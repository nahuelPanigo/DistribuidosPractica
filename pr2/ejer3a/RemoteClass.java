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

public int lastPos(int posA, int posB) {
	if (posA < posB){
		return posA;
		}
	return posB;

}

public byte[] get(String archive, int pos, int cantData) throws RemoteException{
	File archivo = new File (archive);
	byte[] fileArray = new byte[(int) archivo.length()];
	int last= this.lastPos(fileArray.length, pos + cantData);
	int max = (last-pos > 0) ? last-pos : 0;
	byte[] fileArray2 = new byte[(int) max];
	try{
			if (archivo.isFile()){
				FileInputStream	fileInputStream = new FileInputStream(archivo);
				fileInputStream.read(fileArray);
				int j =0;
				for (int i = pos; i < last; i++) {
					fileArray2[j] = fileArray[i];
					j++;
				}
				fileInputStream.close();			
			}
			
		}catch(Exception e){
			System.out.println("se rompio");
			e.printStackTrace();
			return fileArray2;
		}	
	return fileArray2;


}
public int put(String  archive, byte[] data, int length) throws RemoteException{

		try{
			File archivo = new File (archive);
			archivo.createNewFile();
			FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}	
	return data.length;
}



public int list(byte[] data) throws RemoteException{
	return 1;
}

public void loopInfinito() throws RemoteException{
boolean ok = true;
try{
	while (ok) {
		
	}

}catch(Exception e){
	e.printStackTrace();
}

}
}
