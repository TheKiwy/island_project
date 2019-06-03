public class Pilote extends Aventurier {

    Pilote() {
        super();
        super.setPion(Utils.Pion.BLEU);
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