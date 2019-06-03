
import java.util.ArrayList;

public class Plongeur extends Aventurier {

	Plongeur(Tuile tuile) {
		super();
		this.setPion(Utils.Pion.ORANGE);
		setPosition(tuile);
	}

	@Override
	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
		ArrayList<Tuile> liste1 = new ArrayList<>();
		ArrayList<Tuile> liste2 = new ArrayList<>();
		ArrayList<Tuile> liste3 = new ArrayList<>();
		ArrayList<Tuile> liste4 = new ArrayList<>();
		ArrayList<Tuile> listeg = new ArrayList<>();
		liste1 = gDP(g, this.getPosition());
		for (Tuile tuile : liste1){
			if (tuile.getEtat() == Utils.EtatTuile.ASSECHEE){
				liste3.add(tuile);
				liste1.remove(tuile);
			}
		} //  getDeplacementsPossibles renvoie tout sauf assechees, gDP inclut les assechees (=> il comprends les 4 cases autour du joueur) et getDepPos renvoie juste les assechees
		if (liste1.size() != 0){
			while (liste1.size() != 0) {
				liste1.addAll(getDeplacementsPossibles(g, liste1.get(0)));
				liste2.add(liste1.get(0));
				liste1.remove(liste1.get(0));
			}
		}
		if (liste2.size() != 0) {
			for (Tuile tuile : liste2){
				liste4.addAll(getDepPos(g, tuile));
			}
		}

		boolean b; // représente l'absence de récurrence d'une tuile dans les listes (vrai => pas de récurrence)

		listeg.addAll(liste3);
		for (Tuile tuile : liste2){
			b = true;
			for (Tuile tuileg : listeg){
				if (tuileg == tuile){
					b = false;
				}
			}
			if (b){
				listeg.add(tuile);
			}
		}
		for (Tuile tuile : liste4){
			b = true;
			for (Tuile tuileg : listeg){
				if (tuileg == tuile){
					b = false;
				}
			}
			if (b){
				listeg.add(tuile);
			}
		}
		return listeg;
	}

	private ArrayList<Tuile> getDeplacementsPossibles(Grille g, Tuile tuile){
		int x = tuile.getCordX();
		int y = tuile.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() != Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() != Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;
			tuileB = g.getTuileCoord(x, y+1); // recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() != Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileB);
				}
			}
		}

		return listeTuiles;
	}

	public String getRole() {
		return "Plongeur";
	}

	private ArrayList<Tuile> gDP(Grille g, Tuile tuile){
		int x = tuile.getCordX();
		int y = tuile.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				listeTuiles.add(tuileG);
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				listeTuiles.add(tuileD);
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				listeTuiles.add(tuileH);
			}
		}

		if (y != 5) {
			Tuile tuileB = null;
			tuileB = g.getTuileCoord(x, y+1); // recup case x, y + 1
			if (tuileB != null) {
				listeTuiles.add(tuileB);
			}
		}

		return listeTuiles;
	}

	private ArrayList<Tuile> getDepPos(Grille g, Tuile tuile){
		int x = tuile.getCordX();
		int y = tuile.getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() == Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileG);
				}
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() == Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileD);
				}
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() == Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileH);
				}
			}
		}

		if (y != 5) {
			Tuile tuileB = null;
			tuileB = g.getTuileCoord(x, y+1); // recup case x, y + 1
			if (tuileB != null) {
				if (tuileB.getEtat() == Utils.EtatTuile.ASSECHEE) {
					listeTuiles.add(tuileB);
				}
			}
		}

		return listeTuiles;
	}

}