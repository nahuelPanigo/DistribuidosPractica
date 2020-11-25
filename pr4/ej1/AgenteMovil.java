import jade.core.*;
import jade.wrapper.ContainerController;
import com.sun.management.OperatingSystemMXBean; 
import java.lang.management.ManagementFactory;
import jade.lang.acl.ACLMessage;


public class AgenteMovil extends Agent
{
static String[] vector = new String[6];
long startTime;

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
		return "";
}

public void createAllContainer(){
	for (int i=2; i<6; i++){
		createContainer(vector[i]);
	}
}

public void sendMessage(String content){
	ACLMessage message = new ACLMessage(ACLMessage.INFORM); 
	message.setContent(content); 
	message.addReceiver(getAID());
	//message.addReceiver(new AID(vector[0], AID.ISLOCALNAME)); 
	send(message);
}

public void receiveAllMessages(){
	for (int i=0; i<5; i++) {
		ACLMessage msg = blockingReceive();
		System.out.println(msg.getContent());

	}
}

public void setup()
{
	Location origen = here();
	this.initializeArray();
	this.createAllContainer();
try {
	ContainerID destino = new ContainerID(this.getDestino(origen.getID()), null);
	startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("\n\n el tiempo total del recorrido es: " + endTime + "ms" );
	}
	else {
		ContainerID destino = new ContainerID(this.getDestino(origen.getID()), null);
		String s = "\n la cantidad memoria total disponible es: " + bean.getFreePhysicalMemorySize()/1000000 + " MB"+" el nombre de la computadora es: "+ origen.getID() +" la carga de procesamiento es: "+ String.format("%.2f",bean.getSystemCpuLoad()*100) + "%"; 
		this.sendMessage(s);
		doMove(destino);
	}
	}
}
