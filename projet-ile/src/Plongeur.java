
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
        ArrayList<Tuile> listeAquaTuilesTemp = new ArrayList<>();
        ArrayList<Tuile> listeAquaTuilesTemp2 = new ArrayList<>();
        Tuile tuileTestee;

        for (int xTuile = x - 1; xTuile <= x + 1; xTuile++) {
            for (int yTuile = y - 1; yTuile <= y + 1; yTuile++) {
                if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                    tuileTestee = g.getTuileCoord(xTuile, yTuile);
                    if (tuileTestee != null) {
                        if ((tuileTestee.getEtat() != Utils.EtatTuile.COULEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y)) && (tuileTestee != this.getPosition())) {
                            listeTuiles.add(tuileTestee);
                        }
                        if ((tuileTestee.getEtat() != Utils.EtatTuile.ASSECHEE) && (Math.abs(xTuile - x) != Math.abs(yTuile - y)) && (tuileTestee != this.getPosition())) {
                            listeAquaTuilesTemp.add(tuileTestee);
                            listeAquaTuiles.add(tuileTestee);
                        }
                    }
                }
            }
        }

        while (!listeAquaTuilesTemp.isEmpty()) {
            for (Tuile aquaTuile : listeAquaTuilesTemp) {
                for (int xTuile = aquaTuile.getCordX() - 1; xTuile <= aquaTuile.getCordX() + 1; xTuile++) {
                    for (int yTuile = aquaTuile.getCordY() - 1; yTuile <= aquaTuile.getCordY() + 1; yTuile++) {
                        if ((xTuile >= 0 && xTuile <= 5) && (yTuile >= 0 && yTuile <= 5)) {
                            tuileTestee = g.getTuileCoord(xTuile, yTuile);
                            if (tuileTestee != null) {
                                if ((tuileTestee.getEtat() != Utils.EtatTuile.ASSECHEE)
                                        && (Math.abs(xTuile - aquaTuile.getCordX()) != Math.abs(yTuile - aquaTuile.getCordY()))
                                        && (tuileTestee != aquaTuile)
                                        && (tuileTestee != this.getPosition())
                                        && (!listeAquaTuilesTemp.contains(tuileTestee))
                                        && (!listeAquaTuiles.contains(tuileTestee))) {
                                    listeAquaTuilesTemp2.add(tuileTestee);
                                    listeAquaTuiles.add(tuileTestee);
                                }
                            }
                        }
                    }
                }
            }
            listeAquaTuilesTemp.clear();
            listeAquaTuilesTemp.addAll(listeAquaTuilesTemp2);
            listeAquaTuilesTemp2.clear();
        }

        for (Tuile aquaTuile : listeAquaTuiles) {
            if ((aquaTuile.getEtat() != Utils.EtatTuile.COULEE) && (!listeTuiles.contains(aquaTuile))) {
                listeTuiles.add(aquaTuile);
            }
        }

        return listeTuiles;
    }

	public String getRole() {
		return "Plongeur";
	}

}