package Outils;
/*
	Cette classe sert à trouver des points solution donnée est proche de la 
	solution optimale du problème souhaité. 
	Il détermine l'adéquation d'une solution.
*/

public class Chemin {

	
	private int [] villeParcourus; // ensemble des villes déjà visité
	private double score = -1; 
	
	public Chemin(int[] villeParcourus)
	{
		this.villeParcourus = villeParcourus;
	}

	public Chemin(int nbreVilleParcourus)
	{
		//initialisation du tableaux
		int[] villerempli = new int[nbreVilleParcourus];
		
		
		//créer un chemin aléatoire  
		for(int ville = 0; ville < nbreVilleParcourus; ville++)
		{
			villeParcourus[ville] = ville; 
		}
		
		this.villeParcourus = villerempli;
	}
	
	public int[] getVilleParcourus() {
		return villeParcourus;
	}

	public void setVilleParcourus(int[] villeParcourus) {
		this.villeParcourus = villeParcourus;
	}
	
	
}
