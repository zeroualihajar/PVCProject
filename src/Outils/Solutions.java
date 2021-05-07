package Outils;


public class Solutions {
	
	private Chemin Chemins[]; //---- liste des chemins possibles
	private double score = -1;
	
	
	//------ Constructer -> Initialisation
	public Solutions(int taille) {
		super();
		this.Chemins = new Chemin[taille];
	}
	
	public Solutions(int taille, int tailleSol) {
		this.Chemins = new Chemin[taille];
		
		

	}
	

}
