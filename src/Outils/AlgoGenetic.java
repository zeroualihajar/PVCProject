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
	
	/*
	 *	inverser la distance pour identifier le score (si la distance est grande le score est faible)
	 *	remarque: on cherche le plus court chemin
	 */
	public double calculerScore(Chemin chemin, Ville villes[])
	{
		Distance distance = new Distance(chemin, villes);
		double score = 1/ distance.getDistance();
		
		chemin.setScore(score);
		
		return score;
	}
	
	/*
	 * definir le score pour chaque individu 
	 */
	
	public void definitScoreChemin(Solutions solutions, Ville villes[])
	{
		double scoreSolution = 0;
		
		//calculer le score pour les solutions 
		for(Chemin  chemin: solutions.getChemins())
		{
			scoreSolution += this.calculerScore(chemin, villes);
		}
		
		double moyenneScore = scoreSolution / solutions.taille();
		solutions.setScore(moyenneScore);
	}
	
	/*
	 * selectionner le meilleur chemin en utilisant le tournoi
	 */
	public Chemin select(Solutions solutions)
	{
		Solutions tournoi =  new Solutions(this.tailleTournoi);
		
		//melanger le contenu des solutions créer aléatoirement pour commençer le tournoi 
		solutions.shuffle();
		
		for(int i = 0; i < this.tailleTournoi; i++)
		{
			Chemin cheminTournoi = solutions.getChemin(i);
			//tournoi.setChemin(i, cheminTournoi);
		}
		
		return tournoi.getChemin(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
