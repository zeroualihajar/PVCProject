package voyageur;
import jade.gui.GuiAgent;

public class AgentVoyageur extends GuiAgent {
	
	private Outils.TSPUI agentInterface;
	
	
	protected void setup() {
		System.out.println("Bienvenu ! C'est le voyageur Agent");
		
		agentInterface = new Outils.TSPUI();
		agentInterface
		
	}

}
