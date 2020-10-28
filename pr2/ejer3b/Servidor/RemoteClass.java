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
				for (int i = pos; i < last; i++) {
					fileArray2[i] = fileArray[i];
				}
				System.out.println(fileArray2.length);
				fileInputStream.close();			
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return fileArray2;
		}	
	return fileArray2;
}

public void cpGet(String nombre)throws java.io.IOException{
	File archivo = new File (nombre);
	archivo.createNewFile();
	byte[] fileArray = this.get(nombre, 0, (int)archivo.length());
	FileOutputStream fileOuputStream1 = new FileOutputStream(archivo);
	fileOuputStream1.write(fileArray);
	fileOuputStream1.close();
}



public void crearCopiasGet (String archive) throws RemoteException{

	try{
		this.cpGet("/pdytr/pr2/ejer3b/Servidor/original");
		System.out.println("el archivo original se copio correctamente en el servidor");
		this.cpGet("/pdytr/pr2/ejer3b/Servidor/copiaS");
		System.out.println("la copia del archivo se copio correctamente en el servidor");
		this.cpGet("/pdytr/pr2/ejer3b/Cliente/copiaC");
		System.out.println("la copia del archivo se copio correctamente en el cliente");

	} catch (Exception e) {
			System.out.println("hubo un error");
			System.out.println(e);
		}
}

public void cpPut(String nombre, byte[] data, int length)throws java.io.IOException{
	File archivo = new File (nombre);
	archivo.createNewFile();
	byte[] fileArray = this.put(nombre, data, length);
	FileOutputStream fileOuputStream1 = new FileOutputStream(archivo);
	fileOuputStream1.write(fileArray);
	fileOuputStream1.close();
}



public void crearCopiasPut (String archive,byte[] data, int length) throws RemoteException{

	try{
		this.cpPut("/pdytr/pr2/ejer3b/Servidor/original", data, length);
		System.out.println("el archivo original se copio correctamente en el servidor");
		this.cpPut("/pdytr/pr2/ejer3b/Servidor/copiaS", data, length);
		System.out.println("la copia del archivo se copio correctamente en el servidor");
		this.cpPut("/pdytr/pr2/ejer3b/Cliente/copiaC", data, length);
		System.out.println("la copia del archivo se copio correctamente en el cliente");

	} catch (Exception e) {
			System.out.println("hubo un error");
			System.out.println(e);
		}
}

public byte[] put(String  archive, byte[] data, int length) throws RemoteException{
	File archivo = new File (archive);
	byte[] fileArray = new byte[(int) archivo.length()];
		try{
			archivo.createNewFile();
			FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}catch(Exception e){
			e.printStackTrace();
			return fileArray;
		}	
	return fileArray;
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
