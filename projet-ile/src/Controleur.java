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
	private int nbJoueursInitial;

	// Constructor
	Controleur() {

		// Start by Player Settings
		VueSettings vueSettings = new VueSettings();
		vueSettings.addObservateur(this);
		vueSettings.settinggame();
	}

	public boolean partieFinie(){
		if(joueurs.size() < nbJoueursInitial){
			System.out.println("Un joueur est mort");
			return true;
		}else if(niveauDEau >= 10){
			System.out.println("Le niveau d'eau est trop élevé, tous les joueurs sont morts");
			return true;
		}else if(grille.getTuiles().get(16).getEtat() == Utils.EtatTuile.COULEE){
			System.out.println("Il n'y a pas d'héliport pour s'échapper, tous les joueurs restent coincés");
			return true;
		}else if(grille.getTuiles().get(15).getEtat() == Utils.EtatTuile.COULEE && grille.getTuiles().get(22).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuiles().get(0).getEtat() == Utils.EtatTuile.COULEE && grille.getTuiles().get(6).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuiles().get(5).getEtat() == Utils.EtatTuile.COULEE &&grille.getTuiles().get(11).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuiles().get(8).getEtat() == Utils.EtatTuile.COULEE && grille.getTuiles().get(18).getEtat() == Utils.EtatTuile.COULEE)
		{
			System.out.println("Un des trésors a coulé ");
			return true;
		}else{
			return false;
		}
	}

	public void tourDuJeu(){
		do{

		}while(!partieFinie());
	}

	public Grille getGrille() {
		return this.grille;
	}

	public HashMap<String, Aventurier> getAventuriers() {
		return joueurs;
	}

	public void piocherCarteTresor(Aventurier a) {
		CarteTresor carte;
		carte = pileTresor.get(1);
		pileTresor.remove(carte);

		if ("CarteMonteeEaux".equals(carte.getType())) {
			melangerPileInondation();
			monterNiveauEau();
			defausseTresor.add(carte);
		} else {
			a.ajouterCarteTresor(carte);
		}

	}
	public void defausserCarteTresor(CarteTresor carte, Aventurier a) {
		a.defausserCarteTresor(carte, defausseTresor);
		defausseTresor.add(carte);

	}

	public void piocherCarteInondation() {

		CarteInondation carte;
		carte = pileInondation.get(1);
		pileInondation.remove(carte);
		defausseInondation.add(carte);
		carte.inonder();
	}

	public Tuile getTuile(int coordX, int coordY) {
		return grille.getGrille()[coordX][coordY];
	}


	public Aventurier getAventurier(String nom) {
		return joueurs.get(nom);
	}

	public void melangerPileInondation() {
		ArrayList<CarteInondation> pileMelange = new ArrayList<>();
		pileMelange.addAll(defausseInondation);
		pileMelange.addAll(pileInondation);
		Collections.shuffle(pileMelange);
		pileInondation = pileMelange;
	}

	public void melangerPileTresor() {
		ArrayList<CarteTresor> pileMelange = new ArrayList<>();
		pileMelange.addAll(defausseTresor);
		pileMelange.addAll(pileTresor);
		Collections.shuffle(pileMelange);
		pileTresor = pileMelange;
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

	public void monterNiveauEau(){
		this.niveauDEau++;
	}


	public void traiterMessage(Message m) {
		if (null != m.type) //Delcaration
			// Messages processing
			switch (m.type) {
				case DEMARRER_PARTIE:
					Grille grille = new Grille();
					VueGrille vueGrille = new VueGrille();
					vueGrille.afficher(grille);
					break;
				case DEPLACER:

					break;
				case ASSECHER:
					break;
				case ECHANGER:
					break;
				case RECUPERER_TRESOR:
					break;
				case UTILISER_CARTE_COURANT:
					break;
				case UTILISER_CARTE_NON_COURANT:
					break;
				case FIN_TOUR:
					break;
				default:
					break;
			}
	}
}
/*
criteres

13 points dossier
7 pts demo

1 pt qualité rapport
1 pt lisibilité diagramme
1 pt diag séq trou av
4 pts diag se déplacer
2 pts diag seq assecher
4 pts diag classe

4 pts code conforme conception
1 pt preparation demo
2 pt demo pt de vue deplacement / assechement
*/