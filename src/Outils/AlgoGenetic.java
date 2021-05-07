package Outils;

public class AlgoGenetic {

	private int nbreSolution;
	private double tauxMutation; 
	private double tauxCrossover;
	private int nbreElitism;
	protected int tailleTournoi;
	
	
	public AlgoGenetic(int nbreSolution, double tauxMutation, double tauxCrossover, int nbreElitism,
			int tailleTournoi) {
		super();
		this.nbreSolution = nbreSolution;
		this.tauxMutation = tauxMutation;
		this.tauxCrossover = tauxCrossover;
		this.nbreElitism = nbreElitism;
		this.tailleTournoi = tailleTournoi;
	}
	
	public Solutions initSolutions(int nbreVille)
	{
		Solutions sol = new Solutions(this.nbreSolution, nbreVille);
		return sol;
				
	}
	
	// vérifier si le nombre de génération maximale est dépassé
	public boolean conditionFin(int nbreGeneration, int maxGeneration)
	{
		return(nbreGeneration > maxGeneration);
	}
	
	
	
	public double calculerScore(Chemin chemin, Ville villes[])
	{
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
