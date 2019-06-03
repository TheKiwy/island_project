import java.util.ArrayList;

public class Explorateur extends Aventurier {

	Explorateur() {
		super();
		this.setPion(Utils.Pion.VERT);
	}

	@Override
	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
		int x = this.getPosition().getCordX();
		int y = this.getPosition().getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() != Utils.EtatTuile.COULEE) {
					listeTuiles.add(tuileG);
				}
			}
			Tuile tuileBG = null;
			tuileBG = g.getTuileCoord(x-1, y+1); // recup case x-1, y+1
			if (tuileBG != null && tuileBG.getEtat() != Utils.EtatTuile.COULEE && y!=5 && ((x==1 && y!=3) && (x==1 && y!= 4)) || (x==2 && y!=4) || (x > 2 && x < 6)) {
				listeTuiles.add(tuileBG);
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
			Tuile tuileHD = null;
			tuileHD = g.getTuileCoord(x+1, y-1); // recup case x+1, y-1
			if (tuileHD != null && tuileHD.getEtat() != Utils.EtatTuile.COULEE && y!=0 && ((y==1 && x!=3) && (y==1 && x!= 4)) || (y==2 && x!=4) || (y > 2 && y < 6)) {
				listeTuiles.add(tuileHD);
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
			Tuile tuileHG = null;
			tuileHG = g.getTuileCoord(x-1, y-1); // recup case x - 1, y - 1
			if (tuileHG != null && tuileHG.getEtat() != Utils.EtatTuile.COULEE && x!=0 && ((y==1 && x!=1) && (y==1 && x!= 2)) || (y==2 && x!=1) || (y > 2 && y < 6)) {
				listeTuiles.add(tuileHG);
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
			Tuile tuileBD = null;
			tuileBD = g.getTuileCoord(x+1, y+1); // recup case x+1, y+1
			if (tuileBD != null && tuileBD.getEtat() != Utils.EtatTuile.COULEE && x!=5 && ((x==4 && y!=3) && (x==4 && y!= 4)) || (x==3 && y!=4) || (x > -1 && x < 3)) {
				listeTuiles.add(tuileBD);
			}
		}
		return listeTuiles;
	}

	@Override
	public ArrayList<Tuile> getAssechementsPossibles(Grille g) {
		int x = this.getPosition().getCordX();
		int y = this.getPosition().getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();

		if (x != 0) {
			Tuile tuileG = null;
			tuileG = g.getTuileCoord(x-1, y); // recup case x - 1, y
			if (tuileG != null) {
				if (tuileG.getEtat() == Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileG);
				}
			}
			Tuile tuileBG = null;
			tuileBG = g.getTuileCoord(x-1, y+1); // recup case x-1, y+1
			if (tuileBG != null && tuileBG.getEtat() == Utils.EtatTuile.INONDEE && y!=5 && ((x==1 && y!=3) && (x==1 && y!= 4)) || (x==2 && y!=4) || (x > 2 && x < 6)) {
				listeTuiles.add(tuileBG);
			}
		}

		if (x != 5) {
			Tuile tuileD = null;
			tuileD = g.getTuileCoord(x+1, y); // recup case x + 1, y
			if (tuileD != null) {
				if (tuileD.getEtat() == Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileD);
				}
			}
			Tuile tuileHD = null;
			tuileHD = g.getTuileCoord(x+1, y-1); // recup case x+1, y-1
			if (tuileHD != null && tuileHD.getEtat() == Utils.EtatTuile.INONDEE && y!=0 && ((y==1 && x!=3) && (y==1 && x!= 4)) || (y==2 && x!=4) || (y > 2 && y < 6)) {
				listeTuiles.add(tuileHD);
			}
		}

		if (y != 0) {
			Tuile tuileH = null;
			tuileH = g.getTuileCoord(x, y-1); // recup case x, y - 1
			if (tuileH != null) {
				if (tuileH.getEtat() == Utils.EtatTuile.INONDEE) {
					listeTuiles.add(tuileH);
				}
			}
			Tuile tuileHG = null;
			tuileHG = g.getTuileCoord(x-1, y-1); // recup case x - 1, y - 1
			if (tuileHG != null && tuileHG.getEtat() == Utils.EtatTuile.INONDEE && x!=0 && ((y==1 && x!=1) && (y==1 && x!= 2)) || (y==2 && x!=1) || (y > 2 && y < 6)) {
				listeTuiles.add(tuileHG);
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
			Tuile tuileBD = null;
			tuileBD = g.getTuileCoord(x+1, y+1); // recup case x+1, y+1
			if (tuileBD != null && tuileBD.getEtat() == Utils.EtatTuile.INONDEE && x!=5 && ((x==4 && y!=3) && (x==4 && y!= 4)) || (x==3 && y!=4) || (x > -1 && x < 3)) {
				listeTuiles.add(tuileBD);
			}
		}
		listeTuiles.add(getPosition());
		return listeTuiles;
	}

	public String getRole() {
		return "Explorateur";
	}

}