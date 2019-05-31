public class Pilote extends Aventurier {

    Pilote(String nom, Tuile position, Utils.Pion pion) {
            super(nom, position, pion);
    }

    /**
     *
     * @param tuile
     */
	public void deplacementSpecial(Tuile tuile) {
            if (tuile.getEtat() != Utils.EtatTuile.COULEE){
                this.deplacerVers(tuile);
            }
	}

    public String getRole() {
            return "Pilote";
    }

}