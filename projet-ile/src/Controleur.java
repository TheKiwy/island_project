import java.util.*;

public class Controleur implements Observateur {

	// Attributs
	private ArrayList<CarteInondation> defausseInondation;
	private ArrayList<CarteInondation> pileInondation;
	private Grille grille;
	private HashMap<String, Aventurier> joueurs;
	private VueAventurier vueAventurier;
	private VueGrille vueGrille;
	private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteTresor> defausseTresor;
	private int niveauDEau;

	// Constructor
	Controleur() {

		// Start by Player Settings
		VueSettings vueSettings = new VueSettings();
		vueSettings.settinggame();
	}

	public Grille getGrille() {
		return this.grille;
	}

	public HashMap<String, Aventurier> getAventuriers() {
		return joueurs;
	}

	public CarteTresor piocherCarteTresor() {
			throw new UnsupportedOperationException();
	}

	public void defausserCarteTresor(CarteTresor carte) {
		// TODO - implement Controleur.defausserCarteTresor
		throw new UnsupportedOperationException();
	}

	public void piocherCarteInondation() {
		// TODO - implement Controleur.piocherCarteInondation
		throw new UnsupportedOperationException();
	}

	public Tuile getTuile(int coordX, int coordY) {
		// TODO - implement Controleur.getTuile
		throw new UnsupportedOperationException();
	}

	public Aventurier getAventurier(String nom) {
		return joueurs.get(nom);
	}

	public void melangerPileInondation() {
		// TODO - implement Controleur.melangerPileInondation
		throw new UnsupportedOperationException();
	}

	public void melangerPileTresor() {
		// TODO - implement Controleur.melangerPileTresor
		throw new UnsupportedOperationException();
	}

	public void afficherDeplacementsPossibles(Tuile tuiles) {
		// TODO - implement Controleur.afficherDeplacementsPossibles
		throw new UnsupportedOperationException();
	}

	public VueGrille getVueGrille() {
		return this.vueGrille;
	}

	public VueAventurier getVueAventurier() {
		return this.vueAventurier;
	}

	public int pileTresorVide() {
		// TODO - implement Controleur.pileTresorVide
		throw new UnsupportedOperationException();
	}

	public void donnerCarte() {
		// ajouter avenurier courant en paramètres, a faire + tard
	}

	public ArrayList<CarteInondation> getDefausseInondation() {
		return defausseInondation;
	}

	public ArrayList<CarteInondation> getPileInondation() {
		return pileInondation;
	}

	public HashMap<String, Aventurier> getJoueurs() {
		return joueurs;
	}

	public ArrayList<CarteTresor> getPileTresor() {
		return pileTresor;
	}

	public ArrayList<CarteTresor> getDefausseTresor() {
		return defausseTresor;
	}

	public int getNiveauDEau() {
		return niveauDEau;
	}

	@Override
	public void traiterMessage(Message m) {
		//Delcaration

		// Messages processing
		if (m.type == TypesMessage.DEMARRER_PARTIE) {

		} else if (m.type == TypesMessage.DEPLACER) {

		} else if (m.type == TypesMessage.ASSECHER) {

		} else if (m.type == TypesMessage.ECHANGER) {

		} else if (m.type == TypesMessage.RECUPERER_TRESOR) {

		} else if (m.type == TypesMessage.UTILISER_CARTE_COURANT) {

		} else if (m.type == TypesMessage.UTILISER_CARTE_NON_COURANT) {

		} else if (m.type == TypesMessage.FIN_TOUR) {

		}
	}
}