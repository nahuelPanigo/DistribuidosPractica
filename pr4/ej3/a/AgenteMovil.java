import jade.core.*;
import jade.lang.acl.ACLMessage;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AgenteMovil extends Agent
{


public void sendMessageLeer(String content, String pos, String cant){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	message.addReceiver(getAID());
	send(message);
	message.setContent(pos);
	send(message);
	message.setContent(cant);
	send(message);
}

public void sendMessageEscribir(String content, String pos){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	message.addReceiver(getAID());
	send(message);
}

public String recibirLeer(){
	
}

public String recibirEscribir(){

}

public String receiveMessage(){
	ACLMessage msg = blockingReceive();
	if(msg.getContent().equals("leer")){
		return this.recibirLeer();
	}
	return this.recibirEscribir();
}

private int leer (String name){
    BufferedReader reader;
    int total=0;
     File archivo = new File (name);
		try {
			if (archivo.isFile()){
			reader = new BufferedReader(new FileReader(name));
			String line = reader.readLine();
			while (line != null) {
				total = total + Integer.parseInt(line);
				line = reader.readLine();
			}
			reader.close();
		}
		else {
			return -1;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}

public void setup()
{
	Location origen = here();
try {
	sendMessageLeer();
	//sendMessageEscribir();
	ContainerID destino = new ContainerID("Main-Container", null);
	doMove(destino);
	
} catch (Exception e) {
	System.out.println("\n\n\nNo fue posible migrar el agente\n\n\n");}
}



protected void afterMove()
{
	Location origen = here();
	String s = this.receiveMessage();
	System.out.println(origen.getID());
	if (origen.getID().equals("Main-Container@172.17.0.1")) {
		sendMessage(Integer.toString(this.leer(s)));
		ContainerID destino = new ContainerID("Container-1", null);
		doMove(destino);

	}
	else {
		System.out.println("la suma de los valores es: " + s);
	}
	}
}
