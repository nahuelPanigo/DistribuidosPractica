import jade.core.*;
import jade.wrapper.ContainerController;
import com.sun.management.OperatingSystemMXBean; 
import java.lang.management.ManagementFactory;
import jade.lang.acl.ACLMessage;


public class AgenteMovil extends Agent
{
static String[] vector = new String[6];
	

public void initializeArray(){
	for (int i=2; i<6; i++ ){
		vector[i] = "container-" + i ;
	}
	vector[0] = "Container-1";
	vector[1] = "Main-Container";
}


public String getDestino(String origen){
	for (int i = 0; i<6; i++){
		if(origen.equals(vector[i] + "@172.17.0.1")){
			if(i == 5){
				return vector[0];
			}
			return vector[i+1];
		}
}
		return "holaa";
}

public void createAllContainer(){
	for (int i=2; i<6; i++){
		createContainer(vector[i]);
	}
}

public void sendMessage(String content){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	// message.addReceiver(new AID(vector[0], AID.ISLOCALNAME)); 
	send(message);
}

public void receiveAllMessages(){
	ACLMessage msg = receive();
	while (msg != null ) {
		System.out.println(msg);
		msg = receive();
	}
}

public void setup()
{
	Location origen = here();
	this.initializeArray();
	System.out.println("\n\nHola, agente con nombre local " + getLocalName());
	System.out.println("Y nombre completo... " + getName());
	System.out.println("Y en location " + origen.getID() + "\n\n");
	this.createAllContainer();
try {
	
	ContainerID destino = new ContainerID(this.getDestino(origen.getID()), null);
	System.out.println("Migrando el agente a " + destino.getID());
	doMove(destino);
	
} catch (Exception e) {
	System.out.println("\n\n\nNo fue posible migrar el agente\n\n\n");}
}

protected ContainerController createContainer(String name) {
		// Get the JADE runtime interface (singleton)
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		// Create a Profile, where the launch arguments are stored
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, name);
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		// create a non-main agent container
		return runtime.createAgentContainer(profile);
	}


protected void afterMove()
{
	OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	this.initializeArray();
	Location origen = here();
	if( origen.getID().equals(vector[0] + "@172.17.0.1")){
		this.receiveAllMessages();
		System.out.println("termino. el resultado es");
	}
	else {
		ContainerID destino = new ContainerID(this.getDestino(origen.getID()), null);
		String s = bean.getFreePhysicalMemorySize()+ origen.getID();
		this.sendMessage(s);
		doMove(destino);
	}
	}
}
