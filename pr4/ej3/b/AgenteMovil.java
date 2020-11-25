import jade.core.*;
import jade.lang.acl.ACLMessage;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Scanner;

public class AgenteMovil extends Agent
{


public void sendMessage(String content){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	message.addReceiver(getAID());
	send(message);
}


public void sendMessageByte(byte[] data){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.addReceiver(getAID());
	message.setByteSequenceContent(data); 
	send(message);
}


public String receiveMessage(){
	ACLMessage msg = blockingReceive();
	return msg.getContent();
}


public byte[] recibirBytes(){
	ACLMessage msg = blockingReceive();
	return msg.getByteSequenceContent();
	
}


private int lastPos(int posA, int posB) {
  if (posA < posB){
    return posA;
    }
  return posB;

}

private int escribir (String name,byte [] buf){
     try{
      File archivo = new File (name);
      archivo.createNewFile();
      FileOutputStream fileOuputStream = new FileOutputStream(archivo,true);
      fileOuputStream.write(buf);
      fileOuputStream.close();
    } catch (Exception e) {
        System.out.println("no se pudo escribir el archivo");
    }
      return buf.length;
}
	
private byte[] leerCompleto(String name){
	File archivo = new File (name);
    return this.leer(name,0,(int) archivo.length());
}


private byte[] leer (String name, int pos, int cantData){
    File archivo = new File (name);
    byte[] fileArray = new byte[(int) archivo.length()];
    int last= this.lastPos(fileArray.length, pos + cantData);
    int max = (last-pos > 0) ? last-pos : 0;
    byte[] fileArray2 = new byte[max];
    try{
      if (archivo.isFile()){
        FileInputStream fileInputStream = new FileInputStream(archivo);
        fileInputStream.read(fileArray);
        int j = 0;
        for (int i = pos; i < last; i++) {
          fileArray2[j] = fileArray[i];
          j++;
        }
        fileInputStream.close();      
      }
      
    }catch(Exception e){
       System.out.println("no se pudo leer el archivo");
    } 
    return fileArray2;
}



public void setup()
{
	Location origen = here();
try {
	Scanner teclado = new Scanner(System.in);
	System.out.println("ingrese el nombre del archivo que quiere realizar la copia");
    String nombre = teclado. nextLine();
	sendMessage(nombre);
	ContainerID destino = new ContainerID("Main-Container", null);
	doMove(destino);
	
} catch (Exception e) {
	System.out.println("\n\n\nNo fue posible migrar el agente\n\n\n");}
}



protected void afterMove()
{
	String s = this.receiveMessage();
	Location origen = here();
	if (origen.getID().equals("Main-Container@172.17.0.1")) {
		byte [] array = this.leerCompleto(s);
		this.escribir(s+"CopiaServidor", array);
		sendMessage(s);
		this.sendMessageByte(array);
		ContainerID destino = new ContainerID("Container-1", null);
		doMove(destino);
	}
	else {
		this.escribir(s+"CopiaCliente",this.recibirBytes());
	}
	System.out.println("se realizo la copia correctamente");
	}

}
