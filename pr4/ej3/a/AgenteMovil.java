import jade.core.*;
import jade.lang.acl.ACLMessage;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class AgenteMovil extends Agent
{


public void sendMessageLeer(String nombre, String pos, String cant){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent("leer"); 
	message.addReceiver(getAID());
	send(message);
	message.setContent(nombre);
	send(message);
	message.setContent(pos);
	send(message);
	message.setContent(cant);
	send(message);
}

public void sendMessage(String content){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	message.addReceiver(getAID());
	send(message);
}


public void sendMessageEscribir(String nombre, byte[] data){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent("escribir"); 
	message.addReceiver(getAID());
	send(message);
	message.setContent(nombre); 
	send(message);
	message.setByteSequenceContent(data); 
	send(message);
}


public String receiveMessage(){
	ACLMessage msg = blockingReceive();
	if(msg.getContent().equals("leer")){
		int [] array=this.recibirLeer();
		return "la cantidad de datos leidos fue "+ array[0]+ " la cantidad de datos solicitados era "+array[1];
	}
	if(msg.getContent().equals("escribir")){
			return "la cantidad de bytes escritos fueron : "+ this.recibirEscribir();
		}
	return msg.getContent();
}


public int[] recibirLeer(){
	ACLMessage msg = blockingReceive();
	String nombre=msg.getContent();
	msg = blockingReceive();
	int pos=Integer.parseInt(msg.getContent());
	msg = blockingReceive();
	int cant=Integer.parseInt(msg.getContent());
	int [] array = new int [2];
	array[0]=leer(nombre,pos,cant).length;
	array[1]=cant;
	return array;
}

public int recibirEscribir(){
	ACLMessage msg = blockingReceive();
	String nombre=msg.getContent();
	msg = blockingReceive();
	byte [] data=msg.getByteSequenceContent();
	return this.escribir(nombre,data);
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
	//sendMessageLeer("/home/nahuel/Desktop/dockerREADME","1","250");
	String s= "holaaaa";
	sendMessageEscribir("/home/jimena/Documents/foto.png",s.getBytes());
	ContainerID destino = new ContainerID("Main-Container", null);
	doMove(destino);
	
} catch (Exception e) {
	System.out.println("\n\n\nNo fue posible migrar el agente\n\n\n");}
}



protected void afterMove()
{
	Location origen = here();
	String s = this.receiveMessage();
	if (origen.getID().equals("Main-Container@172.17.0.1")) {
		this.sendMessage(s);
		ContainerID destino = new ContainerID("Container-1", null);
		doMove(destino);
	}
	else {
		System.out.println(s);
	}
	}
}
