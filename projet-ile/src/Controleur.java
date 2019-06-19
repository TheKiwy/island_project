import java.util.*;

public class Controleur implements Observateur {

	// Attributs

	// Deck
	private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteTresor> defausseTresor;
	private ArrayList<CarteInondation> defausseInondation;
	private ArrayList<CarteInondation> pileInondation;

	// Treasure
	private ArrayList<Tresor> listeTresor;
	private boolean partieGagnee;

	// Grid
	private Grille grille;
	private VueGrille vueGrille;

	// Players
	private ArrayList<Aventurier> joueurs;
	private HashMap<Aventurier, VueAventurier> vueAventurierHashMap;
	private int nbJoueursInitial;
	private int joueurCourant;

	// Settings
	private VueSettings vueSettings;

	// Others
	private int niveauDEau;
	private int nbActions;
	private boolean PilotePeutUtiliserActionSpeciale;

	// Constructor
	Controleur() {


		// Start by Player Settings
		vueSettings = new VueSettings();
		vueSettings.addObservateur(this);
		vueSettings.setVisible(true);

	}

	public boolean partieFinie(){

		if(joueurs.size() < nbJoueursInitial){
			return true;
		}else if(niveauDEau >= 10){
			return true;
		}else if(grille.getTuileUnique(Role.heliport).getEtat() == Utils.EtatTuile.COULEE){
			return true;
		}else if(grille.getTuileTresor(Role.recupCalice).get(0).getEtat() == Utils.EtatTuile.COULEE && grille.getTuileTresor(Role.recupCalice).get(1).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuileTresor(Role.recupCristal).get(0).getEtat() == Utils.EtatTuile.COULEE && grille.getTuileTresor(Role.recupCristal).get(1).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuileTresor(Role.recupPierre).get(0).getEtat() == Utils.EtatTuile.COULEE && grille.getTuileTresor(Role.recupPierre).get(1).getEtat() == Utils.EtatTuile.COULEE ||
				grille.getTuileTresor(Role.recupZephyr).get(0).getEtat() == Utils.EtatTuile.COULEE && grille.getTuileTresor(Role.recupZephyr).get(1).getEtat() == Utils.EtatTuile.COULEE){
			return true;
		}else if(partieGagnee){
			return true;
		}else{
			return false;
		}
	}

	public void actionFinie() {
		nbActions++;
		if (nbActions >= 3) {

			piocherCarteTresor(joueurs.get(joueurCourant));
			piocherCarteTresor(joueurs.get(joueurCourant));

			for (int i=1; i<= niveauDEau; i++) {
				piocherCarteInondation();
			}
			joueurCourant =  ((joueurCourant+1)%nbJoueursInitial);
			nbActions =0;
			if (partieFinie()) {
				System.out.println("Fini");
			}
		}
	}

	public Grille getGrille() {
		return this.grille;
	}

	public ArrayList<Aventurier> getAventuriers() {
		return joueurs;
	}

	public void piocherCarteTresor(Aventurier a) {
		CarteTresor carte;
		if (pileTresor.isEmpty()) {
			melangerPileTresor();
		}
		carte = pileTresor.get(0);
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

	public boolean pileTresorVide() {
		return pileTresor.isEmpty();
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
				// Grid Instantiation
				this.grille = new Grille();

				// View instantiation
				vueAventurierHashMap = new HashMap<>();

				// Add treasure
				listeTresor = new ArrayList<>();
				this.partieGagnee = false;
				listeTresor.add(new Tresor(TypeTresor.calice));
				listeTresor.add(new Tresor(TypeTresor.cristal));
				listeTresor.add(new Tresor(TypeTresor.pierre));
				listeTresor.add(new Tresor(TypeTresor.zephyr));


				// Player list initialization
				ArrayList<Aventurier> roles = new ArrayList<>();
				roles.add(new Explorateur(grille.getTuileUnique(Role.porteVerte)));
				roles.add(new Ingenieur(grille.getTuileUnique(Role.porteRouge)));
				roles.add(new Messager(grille.getTuileUnique(Role.porteViolette)));
				roles.add(new Navigateur(grille.getTuileUnique(Role.porteJaune)));
				roles.add(new Pilote(grille.getTuileUnique(Role.heliport)));
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

				// Player View instantiation
				for (Aventurier aPlayer : joueurs) {
					for (int i = 0; i<=1; i++) {
						piocherCarteTresor(aPlayer);
					}
					vueAventurierHashMap.put(aPlayer, new VueAventurier(aPlayer));
					vueAventurierHashMap.get(aPlayer).addObservateur(this);

				}

				vueGrille = new VueGrille(grille);
				vueGrille.addObservateur(this);

				// Game lunch
				joueurCourant = 0;
				vueGrille.visible();
				break;
			case DEPLACER:
				// Chosen action: Displacement
				
				vueGrille.changerEtatActions(0);
				vueGrille.afficherDeplacer();
				System.out.println(joueurs.get(joueurCourant).getRole());
				vueGrille.afficherDeplacementsPossibles(joueurs.get(joueurCourant).getDeplacementsPossibles(grille), grille, joueurs.get(joueurCourant));

				break;
			case DEPLACER_VERS:
				// Chosen displacement
				vueGrille.desactiverGrille();
				joueurs.get(joueurCourant).setPosition(m.tuile);
				actionFinie();
				vueGrille.changerEtatActions(1);
				break;
			case ASSECHER:
				// Chosen action: Dry
				vueGrille.changerEtatActions(0);
				vueGrille.afficherAssecher();
				vueGrille.afficherAssechementsPossibles(joueurs.get(joueurCourant).getAssechementsPossibles(grille), grille, joueurs.get(joueurCourant), true);
				break;
			case ASSECHER_VERS:
				// Chosen dry
				vueGrille.desactiverGrille();
				m.tuile.assecher();
				if (m.actionPlus) {
					actionFinie();
				}
				vueGrille.changerEtatActions(1);
				break;
			case ASSECHER_SPECIAL:

				vueGrille.afficherAssechementsPossibles(joueurs.get(joueurCourant).getAssechementsPossibles(grille),grille, joueurs.get(joueurCourant), false);
				vueGrille.afficherAssechementsPossibles(joueurs.get(joueurCourant).getAssechementsPossibles(grille),grille, joueurs.get(joueurCourant), true);

				break;
			case DON_DE_CARTE:
				// Chosen action: Gift of card
				if (joueurs.get(joueurCourant).getRole() == "Messager"){
					ArrayList<Aventurier> receveurspossibles = joueurs;
					receveurspossibles.remove(joueurs.get(joueurCourant));
					vueAventurierHashMap.get(joueurs.get(joueurCourant)).afficherDonCarte(receveurspossibles, joueurs.get(joueurCourant));
				} else {
					vueGrille.afficherDonCarte(joueurs.get(joueurCourant).getReceveursPossibles(), joueurs.get(joueurCourant));
				}
				break;
			case ACTION_SPECIALE :
				if (joueurs.get(joueurCourant).getRole() == "Pilote"){
					if (this.PilotePeutUtiliserActionSpeciale){
						vueGrille.afficherDeplacementsPossibles(joueurs.get(joueurCourant).getDeplacementSpecial(grille), grille, joueurs.get(joueurCourant));
						this.PilotePeutUtiliserActionSpeciale = false;
					}
				} else if (joueurs.get(joueurCourant).getRole() == "Navigateur"){
					vueAventurierHashMap.get(joueurs.get(joueurCourant)).afficherAutresJoueurs(joueurs.get(joueurCourant), grille.getDeplacementsAutres(joueurs.get(joueurCourant), this.getJoueurs()), grille);
				}
			case DONNER_CARTE:
				// Chosen action : give card
				m.receveur.ajouterCarteTresor(joueurs.get(joueurCourant).getCartes().get(m.numCarteTresor));
				joueurs.get(joueurCourant).getCartes().remove(m.numCarteTresor);
				actionFinie();
				break;
			case RECUPERER_TRESOR:
				// Chosen action: Recover treasure
				if(joueurs.get(joueurCourant).verifierTresor(TypeTresor.calice)){
					joueurs.get(joueurCourant).ajouterTresor(this.listeTresor.get(0));
					this.listeTresor.remove(0);
					actionFinie();
				}else if(joueurs.get(joueurCourant).verifierTresor(TypeTresor.cristal)){
					joueurs.get(joueurCourant).ajouterTresor(this.listeTresor.get(1));
					this.listeTresor.remove(1);
					actionFinie();
				}else if(joueurs.get(joueurCourant).verifierTresor(TypeTresor.pierre)){
					joueurs.get(joueurCourant).ajouterTresor(this.listeTresor.get(2));
					this.listeTresor.remove(2);
					actionFinie();
				}else if(joueurs.get(joueurCourant).verifierTresor(TypeTresor.zephyr)){
					joueurs.get(joueurCourant).ajouterTresor(this.listeTresor.get(3));
					this.listeTresor.remove(3);
					actionFinie();
				}

				break;
			case UTILISER_CARTE_COURANT:
				// Chosen action: Using card of current player
				break;
			case UTILISER_CARTE_NON_COURANT:
				// Chosen action: Using card of none current player
				break;
			case CARTE_HELICOPTER:
				boolean tousHeliport = false;
				boolean possedeCarteHelicopter = false;

				for(CarteTresor c : joueurs.get(joueurCourant).getCartes()){
					if(c.getType() == "CarteHelicopter"){
						possedeCarteHelicopter = true;
					}
				}

				for(Aventurier a : this.getAventuriers()){
					if(a.getPosition().getRole() == Role.heliport){
						tousHeliport = true;
					}else{
						tousHeliport =  false;
					}
				}

				if(this.listeTresor.size() == 0 && tousHeliport && possedeCarteHelicopter){
					this.partieGagnee = true;
				}
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
