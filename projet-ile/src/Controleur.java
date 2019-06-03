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

	public void partieEnCours(){

		boolean effectuerAction;
		Scanner scan = new Scanner (System.in);
		int nbActions = 0;

		do{
			effectuerAction = true;

			for(int i = 0; i < nbJoueursInitial-1; i++){

				Aventurier a = joueurs.get(i);

				System.out.println("Voulez-vous effectuer une action? oui/non ");
				scan.nextLine();

				/*Effectuer de 0 à 2 actions*/

				if("oui".equals(scan.toString())){
					nbActions++;

					do{

						vueAventurier.afficher(a);
						System.out.println("Souhaitez-vous effectuer une autre action? oui/non");

						if("non".equals(scan.toString())){
							effectuerAction=false;
						}else if("oui".equals(scan.toString())){
							if(nbActions < 3){
								nbActions++;
							}else{
								effectuerAction=false;
								System.out.println("Vous avez atteint le nombre maximal d'actions dans le meme tour");
							}
						};

					}while(effectuerAction);

				}else if("non".equals(scan.toString())){
					i++;
					break;
				}

				/*Tirer 2 cartes trésor*/

				for(int j = 0; j < 1; j++){
					piocherCarteTresor(a);
				}

				/*Tirer 2 cartes inondation*/

				for(int k = 0; k < 1; k++){
					piocherCarteInondation();
				}

			}

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

					nbJoueursInitial = m.joueurs.size();

					// Initialisation grille

					this.grille = new Grille();

					// Initialisation liste joueurs

					ArrayList<Aventurier> roles = new ArrayList<>();
					roles.add(new Explorateur());
					roles.add(new Ingenieur());
					roles.add(new Messager());
					roles.add(new Navigateur());
					roles.add(new Pilote());
					roles.add(new Plongeur());

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

					this.joueurs = new HashMap<>();

					for (Aventurier av : roles) {
						String nom = listeJoueursOrd.get(roles.indexOf(av));
						av.setNom(nom);
						this.joueurs.put(nom, av);
					}

					// Initialisation niveau eau

					this.niveauDEau = 1;

					// Initialisation cartes trésor

					// A FAIRE

					// Initialisation cartes inondation

					// A FAIRE
					break;
				case DEPLACER:
					break;
                case DEPLACER_VERS:
					m.joueurCourant.setPosition(m.tuile);
                    break;
				case ASSECHER_VERS:
					m.tuile.assecher();
					break;
				case ASSECHER:
					demandeAssacher();
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

	private void demandeAssacher() {
	}

}
