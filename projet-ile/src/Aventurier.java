import java.util.*;

public abstract class Aventurier {

	private ArrayList<CarteTresor> cartes;
	private Tuile position;
	private ArrayList<Tresor> possede;
	private String nom;

	public void deplacerVers(Tuile tuile) {
		this.position = tuile;
	}

	public void assecher(int ligne, int colonne) {
		// a faire apres
	}

	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
		int x = this.position.getCordX();
		int y = this.position.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;// recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;// recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;// recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;// recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileB);
				}
			}
		}

		return listeTuiles;
	}

	public ArrayList<Tuile> getAssechementsPossibles() {
		int x = this.position.getCordX();
		int y = this.position.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;// recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;// recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;// recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;// recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileB);
				}
			}
		}

		return listeTuiles;
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

	public void recupererTresor() {

		int nbMorceaux = 0;

		if (this.position.getRole() == Role.recupPierre) {
			for (carte : cartes) {
				if (carte.getType() == "MorceauTresor")
					public boolean inventairePlein() {

				}

			}