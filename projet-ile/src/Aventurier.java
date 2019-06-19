import java.util.*;

public abstract class Aventurier implements Comparable<Aventurier> {

	private ArrayList<CarteTresor> cartes;
	private Tuile position;
	private ArrayList<Tresor> tresorsPosseder;
	private String nom;
	private Utils.Pion pion;
	private int jours;

	public abstract String getRole();

	Aventurier() {
		cartes = new ArrayList<>();
		tresorsPosseder = new ArrayList<>();
	}
	public void setNom(String nom) { this.nom = nom;
	}

	public void setPosition(Tuile position) {this.position = position;
	}

	public void setPion(Utils.Pion pion) {this.pion = pion;

	}


	public void deplacerVers(Tuile tuile) {
		this.position = tuile;
	}



	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
            int x = this.position.getCordX();
            int y = this.position.getCordY();
            ArrayList<Tuile> listeTuiles = new ArrayList<>();            
            Tuile tuileTestee;
            
            for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
                for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                    if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                        tuileTestee = g.getTuileCoord(xTuile, yTuile);
                        if (tuileTestee != null) {
                            if ((tuileTestee.getEtat() != Utils.EtatTuile.COULEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y)) && (tuileTestee != this.getPosition())) {
                                listeTuiles.add(tuileTestee);
                            }
                        }
                    }
                }
            }

            return listeTuiles;
	}

	public ArrayList<Tuile> getAssechementsPossibles(Grille g) {
            int x = this.position.getCordX();
            int y = this.position.getCordY();
            ArrayList<Tuile> listeTuiles = new ArrayList<>();
            Tuile tuileTestee;
            
            for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
                for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                    if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                        tuileTestee = g.getTuileCoord(xTuile, yTuile);
                        if (tuileTestee != null) {
                            if ((tuileTestee.getEtat() == Utils.EtatTuile.INONDEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y))) {
                                listeTuiles.add(tuileTestee);
                            }
                        }
                    }
                }
            }
            return listeTuiles;
        }

	public Tuile getPosition(){
		return position;
	}


	public ArrayList<Aventurier> getReceveursPossibles() {
		ArrayList<Aventurier> memCase = this.position.getJoueurs();
		memCase.remove(this);
		return memCase;
	}

	public void utiliserCarte(CarteTresor carte) {
	}

	public ArrayList<CarteTresor> getCartes() {
		return this.cartes;
	}

	public String getNom() {
		return this.nom;
	}



	public void ajouterCarteTresor(CarteTresor carte) {
		this.cartes.add(carte);
	}

	public void defausserCarteTresor(CarteTresor carte, ArrayList<CarteTresor> defausse) {
		this.cartes.remove(carte);
		defausse.add(carte);
	}

	public void recupererTresor(Tresor tresor) {

		ArrayList<CarteTresor> morceauxTresors = new ArrayList<>();
		int i=0;
		for (CarteTresor carte: cartes) {
			if (carte.getType() == "CarteMorceauTresor") {
				morceauxTresors.add(carte);
			}
		}
		for (CarteTresor carte : morceauxTresors) {
			if (carte.getTypeTresor() == tresor.getType()){
				i += 1;
			}
		}
		if (i >= 4){
			this.tresorsPosseder.add(tresor);
		}

	}


	public boolean inventairePlein () {
		return cartes.size() >=5;
	}

	public static final Comparator<Aventurier> Aventurier_Comparator = new Comparator<Aventurier>() {
		@Override
		public int compare(Aventurier o1, Aventurier o2) {
				if (o1.jours == o2.jours) {
					return 0;
				} else if (o1.jours < o2.jours || o1.jours == -1) {
					return -1;
				} else if (o1.jours > o2.jours) {
					return 1;
				} else {
					return -1;
				}
		}
	};

}