import java.util.ArrayList;

public class Explorateur extends Aventurier {

	Explorateur(Tuile tuile) {
		super();
		this.setPion(Utils.Pion.VERT);
		setPosition(tuile);
	}

	@Override
	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
		int x = this.getPosition().getCordX();
		int y = this.getPosition().getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();
                Tuile tuileTestee;
            
            for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
                for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                    if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                        tuileTestee = g.getTuileCoord(xTuile, yTuile);
                        if (tuileTestee != null) {
                            if ((tuileTestee.getEtat() != Utils.EtatTuile.COULEE) && (tuileTestee != this.getPosition())) {
                                listeTuiles.add(tuileTestee);
                            }
                        }
                    }
                }
            }
		return listeTuiles;
	}

	@Override
	public ArrayList<Tuile> getAssechementsPossibles(Grille g) {
		int x = this.getPosition().getCordX();
		int y = this.getPosition().getCordY();
		ArrayList<Tuile> listeTuiles = new ArrayList<>();
                Tuile tuileTestee;
            
            for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
                for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                    if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                        tuileTestee = g.getTuileCoord(xTuile, yTuile);
                        if (tuileTestee != null) {
                            if (tuileTestee.getEtat() == Utils.EtatTuile.INONDEE) {
                                listeTuiles.add(tuileTestee);
                            }
                        }
                    }
                }
            }
                
		return listeTuiles;
        }

	public String getRole() {
		return "Explorateur";
	}

}