package intermediaire;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import Outils.Ville;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class AgentIntermediaire extends Agent {

    //------------- Pour initialiser un agent --------------
    @Override
    protected void setup(){

        System.out.println("L'agent intermediaire est demarree");

        //crï¿½ation d'une instance de ParallelBehaviour pour exï¿½cuter plusieurs Behaviours en parallï¿½le
        ParallelBehaviour comportementparallele = new ParallelBehaviour();
        //L'ajout de sous-Behaviour
        addBehaviour(comportementparallele);

        // l'ajout d'un  CyclicBehaviour pour afficher un message ï¿½ chaque fois qu'il s'execute
        comportementparallele.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {

<<<<<<< Updated upstream
                //Prï¿½paration du template pour recevoir des messages
                MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ca marche"));
               
=======
                //Préparation du template pour recevoir des messages
                MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ça marche"));
>>>>>>> Stashed changes
                //Recevoir les messages des autres agents
//                MessageTemplate mt1 = MessageTemplate.MatchConversationId("VI");
                ACLMessage reponse1 = receive(mt1);
                
<<<<<<< Updated upstream
                MessageTemplate mt2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("Calcul du chemin optimal"));
                ACLMessage reponse2 = receive(mt2);

=======
                
                MessageTemplate mt2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("Calcul du chemin optimal"));
                ACLMessage reponse2 = receive(mt2);
                
			 
                
>>>>>>> Stashed changes
                if(reponse1 != null){
                    try{
                        //On rï¿½cupï¿½re le contenu de reponse1 (ACLMessage)
                        List<Ville> villes = (List<Ville>)reponse1.getContentObject();

                        ACLMessage reponse3 = new ACLMessage(ACLMessage.REQUEST);

                        //Modification des paramï¿½tres de la requete ACLMessage
                        reponse3.addReceiver(new AID("calculateur", AID.ISLOCALNAME));
                        //On met la liste des ville dans le message
                        reponse3.setContentObject((Serializable) villes);
                        reponse3.setOntology("Calcul");
<<<<<<< Updated upstream
=======
//                        reponse3.setConversationId("IC");
                        
>>>>>>> Stashed changes
                        //Envoi de message
                        send(reponse3);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(reponse2 != null){
                    try {

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
                        Ville[] villesOrdonnees = (Ville[]) reponse2.getContentObject();

                        ACLMessage reponse3 = new ACLMessage(ACLMessage.INFORM);

                        //Modification des paramï¿½tres de la requete ACLMessage
                        reponse3.addReceiver(new AID("Voyageur", AID.ISLOCALNAME));
                        //On met le tableau des villes ordonnï¿½es dans le message
                        reponse3.setContentObject((Serializable) villesOrdonnees);
                        reponse3.setOntology("Tableau des villes ordonnees");
                        //Envoi de message
                        send(reponse3);

                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //On bloque le Behaviour pour ne pas planifier son execution
                else block();

            }
        });
    };
}
