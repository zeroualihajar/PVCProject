package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainContainer {
	public static void main(String[] args) {
		try {
			//instance pour créer l'agent container
		Runtime runtime=Runtime.instance();
		Properties properties=new ExtendedProperties();
		properties.setProperty(Profile.GUI, "true");
		//Crée une implémentation de profil en utilisant les propriétés données pour configurer le processus de démarrage de la plateforme
		ProfileImpl profileImpl=new ProfileImpl(properties);
		AgentContainer mainContainer=runtime.createMainContainer(profileImpl);
		
        
		//start the agent instance
		mainContainer.start();
	}
	catch(Exception e) { e.printStackTrace();}
}
}