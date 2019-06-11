
import java.util.ArrayList;

public class Plongeur extends Aventurier {

	Plongeur(Tuile tuile) {
		super();
		this.setPion(Utils.Pion.ORANGE);
		setPosition(tuile);
	}

	@Override
	public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
            int x = this.getPosition().getCordX();
            int y = this.getPosition().getCordY();
            ArrayList<Tuile> listeTuiles = new ArrayList<>();
            ArrayList<Tuile> listeAquaTuiles = new ArrayList<>();
            Tuile tuileTestee;
            
            for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
                for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                    if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                        tuileTestee = g.getTuileCoord(xTuile, yTuile);
                        if (tuileTestee != null) {
                            if ((tuileTestee.getEtat() != Utils.EtatTuile.COULEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y)) && (tuileTestee != this.getPosition())) {
                                listeTuiles.add(tuileTestee);
                            }
                            if ((tuileTestee.getEtat() != Utils.EtatTuile.ASSECHEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y))) {
                                listeAquaTuiles.add(tuileTestee);
                            }
                        }
                    }
                }
            }
            
            for (Tuile aquaTuile : listeAquaTuiles) {
                for (int xTuile = aquaTuile.getCordX() - 1; xTuile <= aquaTuile.getCordY() + 1; xTuile++) {
                    for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                        if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                            tuileTestee = g.getTuileCoord(xTuile, yTuile);
                            if (tuileTestee != null) {
                                if ((tuileTestee.getEtat() != Utils.EtatTuile.ASSECHEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y))) {
                                    if (!listeAquaTuiles.contains(tuileTestee)) {
                                        listeAquaTuiles.add(tuileTestee);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            for (Tuile aquaTuile : listeAquaTuiles) {
                if ((aquaTuile.getEtat() != Utils.EtatTuile.INONDEE) && (aquaTuile != this.getPosition()) && (!listeTuiles.contains(aquaTuile))) {
                    listeTuiles.add(aquaTuile);
                }
            }

            return listeTuiles;
	}

	public String getRole() {
		return "Plongeur";
	}

}