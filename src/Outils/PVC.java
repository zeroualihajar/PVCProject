package Outils;

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
	

}
