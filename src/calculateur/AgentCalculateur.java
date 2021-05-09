package calculateur;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import voyageur.AgentVoyageur;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Outils.PVC;
import Outils.Ville;

public class AgentCalculateur extends Agent {

    //------------- Pour initialiser l'agent calculateur --------------
    @Override
    protected void setup(){

        System.out.println("L'agent calculateur est d�marr�");

        //cr�ation d'une instance de ParallelBehaviour pour ex�cuter plusieurs Behaviours en parall�le
        ParallelBehaviour comportementparallele = new ParallelBehaviour();
        //L'ajout de sous-Behaviour
        addBehaviour(comportementparallele);

        // l'ajout d'un  CyclicBehaviour pour afficher un message � chaque fois qu'il s'execute
        comportementparallele.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {

                //Pr�paration du template pour recevoir des messages
                MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("Calcul"));
                //Recevoir les messages des autres agents
                ACLMessage reponse1 = receive(mt1);


                if(reponse1 != null){

                    try{
                        //On r�cup�re le contenu de reponse1 (ACLMessage)
                        List<Ville> villes = (List<Ville>)reponse1.getContentObject();

                        //On cr�� une instance de la classe PVC
                        PVC pvc = new PVC();

                        //On r�cup�re les villes dont la distance entre eux est minimale
						Ville[] villesOrdonnees =  pvc.getPlusCourteDist(villes);

                        ACLMessage reponse2 = new ACLMessage(ACLMessage.INFORM);
                        //Modification des param�tres de la requete ACLMessage
                        reponse2.addReceiver(new AID("Intermediaire", AID.ISLOCALNAME));
                        //On met le tableau des villes ordonnees dans le message
                        reponse2.setContentObject(villesOrdonnees);
                        reponse2.setOntology("Calcul du chemin optimal");
                        //Envoi de message
                        send(reponse2);

                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //On bloque le Behaviour pour ne pas planifier son execution
                else block();

            }
        });
    }

}
