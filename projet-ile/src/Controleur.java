import java.util.*;

public class Controleur implements Observateur {

	// Attributs
	private ArrayList<CarteInondation> defausseInondation;
	private ArrayList<CarteInondation> pileInondation;
	private Grille grille;
	private ArrayList<Aventurier> joueurs;
	private VueAventurier vueAventurier;
	private VueGrille vueGrille;
	private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteTresor> defausseTresor;
	private int niveauDEau;
	private int nbJoueursInitial;
	private int nbActions;
	private VueSettings vueSettings;

	// Constructor
	Controleur() {

		vueGrille = new VueGrille();
		vueGrille.addObservateur(this);
		vueAventurier = new VueAventurier();
		vueAventurier.addObservateur(this);
		grille = new Grille();

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


		do{

			for(Aventurier a : this.joueurs) {
				/*Effectuer de 0 à 2 actions*/
					while (nbActions < 3 ) {
						vueAventurier.afficher(a);
						nbActions++;
					}
					/*Tirer 2 cartes trésor*/

					for(int j = 0; j < 1; j++){
						piocherCarteTresor(a);
					}

			}
			/*Tirer 2 cartes inondation*/

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
		if (null != m.type) //Delcaration
			// Messages processing
			switch (m.type) {
                case DEMARRER_PARTIE:

                    // Initialisation grille

                    this.grille = new Grille();

                    // Initialisation liste joueurs

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

					for (Integer jours : listeJours) { // On teste tous les nb de jours
						for (String joueur : m.joueurs.keySet()) { // On regarde pour tous les joueurs
							if (m.joueurs.get(joueur) == jours) { // si son nb jours correspond
								if (!listeJoueursOrd.contains(joueur)) { // s'il n'est pas déjà dans la liste
									if (jours == -1) { // s'il n'est jamais allé sur une île ou qu'il ne s'en souvient pas
										listeJoueursNSP.add(joueur); // on l'ajoutera à la fin
									} else { // autrement
										listeJoueursOrd.add(joueur); // on l'ajoute au début
									}
								}
							}
						}
					}

					listeJoueursOrd.addAll(listeJoueursNSP);

					System.out.print(listeJoueursOrd);

					this.joueurs = new ArrayList<>();

					for (String nomJ : listeJoueursOrd) {
						Aventurier av = roles.get(listeJoueursOrd.indexOf(nomJ));
						av.setNom(nomJ);
						this.joueurs.add(av);
					}
					nbJoueursInitial = this.joueurs.size();

					// Initialisation niveau eau

					this.niveauDEau = 1;

					// Initialisation cartes trésor

					// A FAIRE

					// Initialisation cartes inondation

					// A FAIRE

					// Lancement

					this.partieEnCours();
					break;
				case DEPLACER:
					vueGrille.afficherDeplacementsPossibles(m.joueurCourant.getDeplacementsPossibles(grille), grille);
					break;
                                case DEPLACER_VERS:
					m.joueurCourant.setPosition(m.tuile);
                                    break;
				case ASSECHER:
					vueGrille.afficherAssechementsPossibles(m.joueurCourant.getAssechementsPossibles(grille), grille);
					break;
				case ASSECHER_VERS:
					m.tuile.assecher();
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
					nbActions = 4;
					break;
				default:
					break;
			}
	}

	private void demandeAssacher() {
	}

}
