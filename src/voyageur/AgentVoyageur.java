package voyageur;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Outils.Ville;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;


public class AgentVoyageur extends GuiAgent {

	/* La classe AgentVoyageur ser a demarrer l'agent voyageur et effectuer l'ensemble des options que nous avons lui deja offrir */
	
	
	private voyageur.InterfaceVoyageur interfaceAgent;
	
	
	protected void setup() {
		System.out.println("Bienvenu ! C'est le voyageur Agent");
		
		interfaceAgent = new voyageur.InterfaceVoyageur();
		interfaceAgent.setVisible(true);
		interfaceAgent.setAgentVoyageur(this);
		
		System.out.println("Le Voyageur est pret !");
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			
			private int compteur;
			@Override
			public void action() {
				++compteur;
				
				MessageTemplate message1  = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("�a marche"));
				ACLMessage acl1 = receive(message1);
				
				System.out.print("\n message 1: "+ message1);
				MessageTemplate message2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("Tableau des villes ordonn�es"));
				ACLMessage acl2 = receive(message2);
				System.out.print("\n message 2: "+ message2);
				
				if(acl1 !=null) {
					System.out.println("L'emetteur de message : " +acl1.getSender());
					System.out.println("Le destinataire de message : "+acl1.getInReplyTo());
					System.out.println("L'acte de communication : " +acl1.getPerformative());
					System.out.println("LE contenu de message : " +acl1.getContent());
					System.out.println("Langage : " +acl1.getLanguage());
					System.out.println("L'ontology : " +acl1.getOntology());
						
				}
				else if(acl2 != null) {
					
					try {
						Ville[] villeOrdonnee = (Ville[]) acl2.getContentObject();
						interfaceAgent.setVilles(villeOrdonnee);
						interfaceAgent.map.setDistances(new ArrayList<Ville>(Arrays.asList(villeOrdonnee)));
						interfaceAgent.map.setPlusCourt(true);
						
						interfaceAgent.getPvc().setEtapeCourante(villeOrdonnee.length);
						interfaceAgent.map.setEtapeActuelle(interfaceAgent.getPvc().getEtapeCourante());
						
						
						System.out.println("L'�tape actuelle : " +interfaceAgent.getPvc().getEtapeCourante());
						
						interfaceAgent.map.repaint();
					} catch(UnreadableException ex) {
						System.out.println(ex);
					}
					
				}
				else
					block();
				
			}
		});
	}
	
	@Override
	protected void onGuiEvent(GuiEvent event) {
		switch (event.getType()) {
		case 1:
			System.out.println("En Gui Event");
			Map<String, Object> params = (Map<String, Object>) event.getParameter(0);
			
			List<Ville> villes = (List<Ville>)params.get("v1");
			
			System.out.println("Villes : "+villes.toString());
			
			ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
			aclMessage.addReceiver(new AID("Intermediaire", AID.ISLOCALNAME));
			
			try {
				aclMessage.setContentObject((Serializable) villes);
				System.out.println("Content " +aclMessage.getContent());
				
			} catch (IOException ex) {
				System.out.println(ex);
			}
			aclMessage.setOntology("�a marche");
			send(aclMessage);
			System.out.println(aclMessage); 
			break;
			
			default:
				break;
				
		}}
}