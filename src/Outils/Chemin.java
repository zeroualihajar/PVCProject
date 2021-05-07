package Outils;
/*
	Cette classe sert � trouver des chemins qui represente une solution donn�e est proche de la 
	solution optimale du probl�me souhait�. 
	Il d�termine l'ad�quation d'une solution.
*/

import java.util.Arrays;

public class Chemin {

	
	private int [] villeParcourus; // ensemble des villes d�j� visit�
	private double score = -1;  // score convenable au chemin  
	
	public Chemin(int[] villeParcourus)
	{
		this.villeParcourus = villeParcourus;
	}

	public Chemin(int nbreVilleParcourus)
	{
		//initialisation du tableaux
		int[] villerempli = new int[nbreVilleParcourus];
		
		
		//cr�er un chemin al�atoire  
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Chemin [villeParcourus=" + Arrays.toString(villeParcourus) + ", score=" + score + "]";
	}
	
	// verifier si une ville existe sur un chemin 
	public boolean verifierVille(int ville)
	{
		for(int i = 0; i < this.villeParcourus.length; i++)
			if(this.villeParcourus[i] == ville)
				return true;
		
		
		return false;
	}
	
	
}
