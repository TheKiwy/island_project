import java.util.*;

public abstract class Aventurier {

	private ArrayList<CarteTresor> cartes;
	private Tuile position;
	private ArrayList<Tresor> tresorsPosseder;
	private String nom;
	private Utils.Pion pion;

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

	public void assecher(Tuile tuile) {
		tuile.assecher();	}

	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
		int x = this.position.getCordX();
		int y = this.position.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;
			tuileB = g.getTuileCoord(x, y+1); // recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileB);
				}
			}
		}

		return listeTuiles;
	}

	public ArrayList<Tuile> getAssechementsPossibles(Grille g) {
		int x = this.position.getCordX();
		int y = this.position.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() != Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() != Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;
			tuileB = g.getTuileCoord(x, y+1); // recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() == Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileB);
				}
			}
		}
		listeTuiles.add(getPosition());
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
		carte.action();
	}

	public ArrayList<CarteTresor> getCartes() {
		return this.cartes;
	}

	public String getNom() {
		return this.nom;
	}

	public abstract String getRole();

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

}