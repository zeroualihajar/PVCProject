package Outils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PVC {
	
	public static int maxGenerations = 15000;
	
	public int etapeCourante;
	public double meilleureDistance;
	public Ville villes[];
	
	//-------------- Getters && Setters -----------------
	
	public double getMeilleureDistance() {
		return meilleureDistance;
	}
	public int getEtapeCourante() {
		return etapeCourante;
	}
	public void setEtapeCourante(int etapeCourante) {
		this.etapeCourante = etapeCourante;
	}
	public void setMeilleureDistance(double meilleureDistance) {
		this.meilleureDistance = meilleureDistance;
	}
	public Ville[] getVilles() {
		return villes;
	}
	public void setVilles(Ville[] villes) {
		this.villes = villes;
	}
	
	//----------- Etape précédente --------------
	public void EtapePrecedente() {
		if(this.etapeCourante > 0) {
			this.etapeCourante--;
		}
	}
	
	//----------- Etape suivante  --------------
	public void EtapeSuivante() {
		//--- on vérifie s'il nous reste des villes à parcourir ----
		if(this.etapeCourante < this.villes.length -1) {
			this.etapeCourante++;
		}
	}
	
	//---------- Dernière Etape --------
	public void DerniereEtape() {
		this.etapeCourante = this.villes.length;
	}
	
	//-------- Retourne un tableau des villes dont la distance entre eux est minimale ---
	public Ville[] getPlusCourteDist(List<Ville> villesList) {
		
		//---- On créé un tableau des villes à partir de la liste donnée en paramètre ---
		this.villes = villesList.toArray(new Ville[villesList.size()]);
		
		AlgoGenetic aGenit = new AlgoGenetic(100, 0.001, 0.9, 2, 5);
		
		//----- initialisation des solutions --------
		Solutions sol = aGenit.initSolutions(villes.length);
		
		//-- On définit le score du chemin ---
		aGenit.definitScoreChemin(sol, villes);
		
		Distance debutDist = new Distance(sol.CheminApte(0), villes);
		
		//------ on créé une génération courante ------------
		int generation = 1;
		
		while(aGenit.conditionFin(generation, maxGenerations) == false) {
			
			//--- score du chemin à partir de la solution ---
			Distance d = new Distance(sol.CheminApte(0), villes);
			
			sol = aGenit.crossoverSolution(sol);
			
			sol = aGenit.muterSolution(sol);
			
			//--- on définit le score du chemin ---
			aGenit.definitScoreChemin(sol, villes);
			
			//------ On incrémente la génération courante ---------
			generation++;
			
		}
		
		//--- La meilleure distance ----
		Distance d = new Distance(sol.CheminApte(0), villes);
		meilleureDistance = d.getDistance();
		
		//----- Test ---
		List<Ville> lv = new ArrayList<Ville>(Arrays.asList(d.getParcours()));
		
		lv.add(lv.get(0));
		
		return lv.toArray(new Ville[villesList.size()]);
		
		
	}

}
