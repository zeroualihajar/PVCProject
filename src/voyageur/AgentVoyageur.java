package voyageur;
import java.util.ArrayList;
import java.util.Arrays;

import Outils.Ville;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentVoyageur extends GuiAgent {
	
	private voyageur.InterfaceVoyageur interfaceAgent;
	
	
	protected void setup() {
		System.out.println("Bienvenu ! C'est le voyageur Agent");
		
		interfaceAgent = new voyageur.InterfaceVoyageur();
		interfaceAgent.createAndDisplay();
		interfaceAgent.setAgentVoyageur(this);
		
		System.out.println("Le Voyageur est pret !");
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			
			private int compteur;
			@Override
			public void action() {
				++compteur;
				
				MessageTemplate message1  = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("Il marche"));
				ACLMessage acl1 = receive(message1);
				
				MessageTemplate message2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("Ordonner les villes"));
				ACLMessage acl2 = receive(message2);
				
				
				if(acl1 !=null) {
					System.out.println("L'emetteur de message : " +acl1.getSender());
					System.out.println("Le destinataire de message : "+acl1.getInReplyTo());
					System.out.println("L'acte de communication : " +acl1.getPerformative());
					System.out.println("LE contenu de message : " +acl1.getContent());
					System.out.println("Langage : " +acl1.getLanguage());
					System.out.println("L'ontology : " +acl1.getOntology());
						
				}
				else if(acl2 != null) {
					Ville[] villeOrdonnee = (Ville[]) acl2.getContentObject();
					interfaceAgent.getBar().villes = villeOrdonnee;
					interfaceAgent.mapPanel().setVillesroute(new ArrayList<Ville>(Arrays.asList(villeOrdonnee)));
					
					
				}
				
			}
		}
				
				
				);
		
	}

}
