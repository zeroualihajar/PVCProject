package Outils;

import java.util.Arrays;

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
	
	// v�rifier si le nombre de g�n�ration maximale est d�pass�
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
		double score = 1 / distance.getDistance();
		
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
		
		//melanger le contenu des solutions cr�er al�atoirement pour commen�er le tournoi 
		solutions.Melanger();
		
		for(int i = 0; i < this.tailleTournoi; i++)
		{
			Chemin cheminTournoi = solutions.getChemin(i);
			tournoi.setChemin(cheminTournoi, i);
		}
		
		return tournoi.CheminApte(0);
	}
	
	public Solutions crossoverSolution(Solutions solutions)
	{
		Solutions nouvSolutions = new Solutions(solutions.taille());
		
		
		for(int solIndex = 0; solIndex < nouvSolutions.taille() ; solIndex++)
		{
			Chemin parent1 = solutions.CheminApte(solIndex);
			
			if(this.tauxCrossover > Math.random() &&  solIndex >= this.nbreElitism)
			{
				Chemin parent2 = this.select(solutions);
				
				int villeParcourus[] = new int[parent1.getnbreVilleParcourus()];
				Arrays.fill(villeParcourus, -1);
				Chemin enfant = new Chemin(villeParcourus);
			
				
				int substrpos1 = (int) (Math.random() * parent1.getnbreVilleParcourus());
				int substrpos2 = (int) (Math.random() * parent1.getnbreVilleParcourus());
			
				final int debutPos = Math.min(substrpos1, substrpos2);
				final int finPos = Math.max(substrpos1, substrpos2);
				
				for(int i = debutPos; i < finPos ; i++)
				{
					enfant.setVille(i, parent1.getVille(i));
				}
				
				
				for(int i = 0; i < parent2.getnbreVilleParcourus(); i++)
				{
					int parent2Ville = i + finPos;
					if(parent2Ville >= parent2.getnbreVilleParcourus()) {
						parent2Ville -= parent2.getnbreVilleParcourus();
					}
					
					if(enfant.verifierVille(parent2.getVille(parent2Ville)) == false)
					{
						
						for (int j = 0; j < enfant.getnbreVilleParcourus(); j++)
						{
							if(enfant.getVille(j) == -1)
							{
								enfant.setVille(j, parent2.getVille(parent2Ville));
								break;
							}
						}
					}
				}
				
				nouvSolutions.setChemin(enfant, solIndex);
			}
			else {
					 nouvSolutions.setChemin(parent1, solIndex);
				}
			}
		return nouvSolutions;
		}

	

	public Solutions muterSolution(Solutions solutions)
	{
		Solutions nouvSolutions = new Solutions(this.nbreSolution);
		
		for(int solIndex = 0; solIndex < solutions.taille(); solIndex++)
		{
			Chemin chemin = solutions.CheminApte(solIndex);
			
			if(solIndex >= this.nbreElitism)
			{
				for(int indexVille = 0; indexVille < chemin.getnbreVilleParcourus(); indexVille++ )
				{
					if(this.tauxMutation > Math.random())
					{
						
						int newPosVille = (int) (Math.random() * chemin.getnbreVilleParcourus());
						
						int ville1 = chemin.getVille(newPosVille);
						int ville2 = chemin.getVille(indexVille);
						
						chemin.setVille(indexVille, ville1);
						chemin.setVille(newPosVille, ville2);
					}
				}
			}
			nouvSolutions.setChemin(chemin, solIndex);
		}
		return nouvSolutions;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
