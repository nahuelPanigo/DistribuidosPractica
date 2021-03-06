/*
* IfaceRemoteClass.java
* Interface defining only one method which can be invoked remotely
*
*/
/* Needed for defining remote method/s */
import java.rmi.Remote;
import java.rmi.RemoteException;
/* This interface will need an implementing class */
public interface IfaceRemoteClass extends Remote
{
/* It will be possible to invoke this method from an application in other JVM */
public byte[] sendThisBack(byte[] data) throws RemoteException;
public byte[] get(String archive, int pos, int bytesRead) throws RemoteException;
public byte[] put(String archive,byte[] data, int length) throws RemoteException;
public void crearCopiasGet(String nombre) throws RemoteException;
public void crearCopiasPut(String nombre, byte[] data, int length) throws RemoteException;
public int list(byte[] data) throws RemoteException;
public void loopInfinito() throws RemoteException;
}

