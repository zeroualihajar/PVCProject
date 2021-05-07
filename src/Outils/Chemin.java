package Outils;
/*
	Cette classe sert � trouver des points solution donn�e est proche de la 
	solution optimale du probl�me souhait�. 
	Il d�termine l'ad�quation d'une solution.
*/

public class Chemin {

	
	private int [] villeParcourus; // ensemble des villes d�j� visit�
	private double score = -1; 
	
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
	
	
}
