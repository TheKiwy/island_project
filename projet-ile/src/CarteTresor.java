import java.util.ArrayList;

public abstract class CarteTresor {

	private Aventurier proprietaire;

	public void setPropriétaire(Aventurier joueur){
		this.proprietaire = joueur;
	}

	public Aventurier getProprietaire(){
		return this.proprietaire;
	}

	public abstract String getType();

	public TypeTresor getTypeTresor(){
		return null;
	}

	// à override dans CarteHelicoptere (rien à mettre ici)
	public void action(Tuile tuileDepart, Tuile tuileArrivee, ArrayList<CarteTresor> defausseTresor) {
		// vide
	}

	// à override dans CarteMonteeEaux (rien à mettre ici)
	public void action(int niveauDEau, ArrayList<CarteInondation> pileInondation, ArrayList<CarteInondation> defausseInondation) {
		// vide
	}

	// à override dans CarteSac (rien à mettre ici)
	public void action(Tuile tuile, ArrayList<CarteTresor> defausseTresor) {
		// vide
	}
}