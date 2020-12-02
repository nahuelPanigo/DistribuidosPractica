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
public byte[] get(String archive, int pos, int bytesRead) throws RemoteException;
public int put(String archive,byte[] data, int length) throws RemoteException;
}

