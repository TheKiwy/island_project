import java.util.*;

public abstract class Aventurier {

	private ArrayList<CarteTresor> cartes;
	private Tuile position;
	private ArrayList<Tresor> tresorsPosseder;
	private String nom;
	private Utils.Pion pion;

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
						if ((tuileTestee.getEtat() == Utils.EtatTuile.INONDEE) && !((Math.abs(xTuile - x) == 1) && (Math.abs(yTuile - y) == 1))) {
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
		ArrayList<Aventurier> memCase = this.getPosition().getJoueurs();
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

	public boolean verifierTresor(TypeTresor tresor) {

		ArrayList<CarteTresor> morceauxTresors = new ArrayList<>();
		int i=0;
		for (CarteTresor carte: cartes) {
			if (carte.getType() == "CarteMorceauTresor") {
				morceauxTresors.add(carte);
			}
		}

		for (CarteTresor carte : morceauxTresors) {
			if (carte.getTypeTresor() == tresor){
				i += 1;
			}
		}
		return i >= 4;
	}

	public void ajouterTresor(Tresor tresor){
		this.tresorsPosseder.add(tresor);
	}


	public boolean inventairePlein () {
		return cartes.size() >=5;
	}

    public ArrayList<Tuile> getDeplacementSpecial(Grille grille) {
		return new ArrayList<>();
	}
}