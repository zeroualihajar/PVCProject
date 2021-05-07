package Outils;

public class Ville {
	
	private int idVille;
	private String nomVille;
	private double posX;
	private double posY;
	private boolean visite;
	
	
	public Ville(int idVille, String nomVille, double posX, double posY, boolean visite) {
		super();
		this.idVille = idVille;
		this.nomVille = nomVille;
		this.posX = posX;
		this.posY = posY;
		this.visite = visite;
	}


	public int getIdVille() {
		return idVille;
	}


	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}


	public String getNomVille() {
		return nomVille;
	}


	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}


	public double getPosX() {
		return posX;
	}


	public void setPosX(double posX) {
		this.posX = posX;
	}


	public double getPosY() {
		return posY;
	}


	public void setPosY(double posY) {
		this.posY = posY;
	}


	public boolean isVisite() {
		return visite;
	}


	public void setVisite(boolean visite) {
		this.visite = visite;
	}
	
	
	
	
}
