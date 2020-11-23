import jade.core.*;
import jade.wrapper.ContainerController;

public class AgenteMovil extends Agent
{
static String[] vector = new String[6];


public void initializeArray(){
	for (int i=2; i<6; i++ ){
		vector[i] = "container-" + i;
	}
	vector[0] = "Container-1";
	vector[1] = "Main-Container";
}


public String getDestino(String origen){
	System.out.println(origen);
	for(int i=0;i<6;i++){
		if(origen.equals(vector[i])){
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


public void setup()
{
	Location origen = here();
	this.initializeArray();
	System.out.println("\n\nHola, agente con nombre local " + getLocalName());
	System.out.println("Y nombre completo... " + getName());
	System.out.println("Y en location " + origen.getID() + "\n\n");
	this.createAllContainer();
try {
	//System.out.println(this.getDestino(origen.getID()));
	System.out.println("HASTA ACA LLEGO");
	ContainerID destino = new ContainerID("Main-Container", null);
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
	Location origen = here();
	System.out.println("\n\nHola, agente migrado con nombre local " + getLocalName());
	System.out.println("Y nombre completo... " + getName());
	System.out.println("Y en location " + origen.getID() + "\n\n");
	if( origen.getID().equals(vector[0])){
		System.out.println("termino. el resultado es");
	}
	
	}
}
