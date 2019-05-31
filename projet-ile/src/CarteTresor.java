public abstract class CarteTresor {

	private Aventurier proprietaire;

	public void setPropriétaire(Aventurier joueur){
		this.proprietaire = joueur;
	}

	public Aventurier getProprietaire(){
		return this.proprietaire;
	}

	public abstract void action();

	public abstract String getType();

	public abstract TypeTresor getTypeTresor();

}