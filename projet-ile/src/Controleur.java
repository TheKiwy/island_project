import java.util.*;

public class Controleur implements Observateur {

	// Attributs
    
        // Deck
        private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteTresor> defausseTresor;
	private ArrayList<CarteInondation> defausseInondation;
	private ArrayList<CarteInondation> pileInondation;
        
        // Grid
	private Grille grille;
	private VueGrille vueGrille;
        
        // Players
        private ArrayList<Aventurier> joueurs;
	private VueAventurier vueAventurier;
        private int nbJoueursInitial;
        
         // Settings
	private VueSettings vueSettings;
        
        // Others
	private int niveauDEau;
	private int nbActions;
       

	// Constructor
	Controleur() {
		// Start by Player Settings
		vueSettings = new VueSettings();
		vueSettings.addObservateur(this);
		vueSettings.settinggame();
	}

	public boolean partieFinie(){
		if(joueurs.size() < nbJoueursInitial){
			return true;
		}else if(niveauDEau >= 10){
			return true;
		}else if(grille.getTuiles().get(16).getEtat() == Utils.EtatTuile.COULEE){
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

	public void partieEnCours(){
		// Iteration Game
		do{
			// Player turn
			for(Aventurier a : this.joueurs) {
				// 3 Actions
				while (nbActions < 3 ) {
					vueAventurier.afficher(a);
					nbActions++;
				}

				// Pick 2 treasure card
				for(int j = 0; j < 1; j++){
					piocherCarteTresor(a);
				}
			}

			// Pick 2 overflow card
			for(int k = 0; k < 1; k++){
				piocherCarteInondation();
			}



		}while(!partieFinie());
	}

	public Grille getGrille() {
		return this.grille;
	}

	public ArrayList<Aventurier> getAventuriers() {
		return joueurs;
	}

	public void piocherCarteTresor(Aventurier a) {
		CarteTresor carte;
		int test = pileTresor.size();
		carte = pileTresor.get(1);
		pileTresor.remove(carte);

		if ("CarteMonteeEaux".equals(carte.getType())) {
			melangerPileInondation();
			monterNiveauEau();
			defausseTresor.add(carte);
		} else {
			a.ajouterCarteTresor(carte);
			carte.setPropriétaire(a);
		}


	}
	public void defausserCarteTresor(CarteTresor carte, Aventurier a) {
		a.defausserCarteTresor(carte, defausseTresor);
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

	public ArrayList<Aventurier> getJoueurs() {
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
		// Messages processing
		switch (m.type) {

			case DEMARRER_PARTIE:
				// View instantiation
				vueGrille = new VueGrille();
				vueGrille.addObservateur(this);
				vueAventurier = new VueAventurier();
				vueAventurier.addObservateur(this);

				// Grid Instantiation
				this.grille = new Grille();

				// Player list initialization
				ArrayList<Aventurier> roles = new ArrayList<>();
				roles.add(new Explorateur(grille.getTuileUnique(Role.porteVerte)));
				roles.add(new Ingenieur(grille.getTuileUnique(Role.porteRouge)));
				roles.add(new Messager(grille.getTuileUnique(Role.porteViolette)));
				roles.add(new Navigateur(grille.getTuileUnique(Role.porteJaune)));
				roles.add(new Pilote(grille.getTuileUnique(Role.porteBleue)));
				roles.add(new Plongeur(grille.getTuileUnique(Role.porteNoire)));

				Utils.melangerAventuriers(roles);

				ArrayList<Integer> listeJours = new ArrayList<>();

				listeJours.addAll(m.joueurs.values());

				Collections.sort(listeJours);

				ArrayList<String> listeJoueursOrd = new ArrayList<>();
				ArrayList<String> listeJoueursNSP = new ArrayList<>();
                        
				// Day number testing
				for (Integer jours : listeJours) {
					// Player
					for (String joueur : m.joueurs.keySet()) {
						// Matching day number
						if (m.joueurs.get(joueur) == jours) {
							// Player doesn't exist
							if (!listeJoueursOrd.contains(joueur)) {
								// If he never or he forgot gone on an island
								if (jours == -1) {
									// Add him at the end of the list
									listeJoueursNSP.add(joueur);
								} else {
									// Add him at the start of the list
									listeJoueursOrd.add(joueur);
								}
							}
						}
					}
				}
				listeJoueursOrd.addAll(listeJoueursNSP);
				this.joueurs = new ArrayList<>();
				for (String nomJ : listeJoueursOrd) {
					Aventurier av = roles.get(listeJoueursOrd.indexOf(nomJ));
					av.setNom(nomJ);
					this.joueurs.add(av);
				}
				nbJoueursInitial = this.joueurs.size();

				// Water Level initialization
				this.niveauDEau = 1;

				// Treasure card initialization
				pileTresor = new ArrayList<>();
				// 3 Rising water card
				for (int i=0; i <= 2; i++) {
					pileTresor.add(new CarteMonteeEaux());
				}
				// 2 Sand bag card
				for (int i=0; i <= 1; i++) {
					pileTresor.add(new CarteSac());
				}
				// 3 Helicopter card
				for (int i=0; i <= 2; i++) {
					pileTresor.add(new CarteHelicoptere());
				}
				// 5 Piece of treasure card
				for (int i=0; i<= 4; i++) {
					pileTresor.add(new CarteMorceauTresor(TypeTresor.pierre));
					pileTresor.add(new CarteMorceauTresor(TypeTresor.calice));
					pileTresor.add(new CarteMorceauTresor(TypeTresor.cristal));
					pileTresor.add(new CarteMorceauTresor(TypeTresor.zephyr));
				}
				defausseTresor = new ArrayList<>();
				Collections.shuffle(pileTresor);

				// Overflow card
				pileInondation = new ArrayList<>();
				for (Tuile tuile : grille.getTuiles()) {
					pileInondation.add(new CarteInondation(tuile));
				}
				defausseInondation = new ArrayList<>();
				Collections.shuffle(pileInondation);

				// Game lunch
				this.partieEnCours();
				break;
			case DEPLACER:
				// Chosen action: Displacement
				vueGrille.afficherDeplacementsPossibles(m.joueurCourant.getDeplacementsPossibles(grille), grille);
				break;
			case DEPLACER_VERS:
				// Chosen displacement
				m.joueurCourant.setPosition(m.tuile);
				break;
			case ASSECHER:
				// Chosen action: Dry
				vueGrille.afficherAssechementsPossibles(m.joueurCourant.getAssechementsPossibles(grille), grille);
				break;
            case ASSECHER_VERS:
            	// Chosen dry
            	m.tuile.assecher();
            	break;
            case ECHANGER:
            	// Chosen action: Exchange
            	break;
            case RECUPERER_TRESOR:
            	// Chosen action: Recover treasure
            	break;
            case UTILISER_CARTE_COURANT:
            	// Chosen action: Using card of current player
            	break;
            case UTILISER_CARTE_NON_COURANT:
            	// Chosen action: Using card of none current player
            	break;
            case FIN_TOUR:
            	// Chosen action: End turn
            	nbActions = 4;
            	break;
            default:
            	break;
		}
    }
}
