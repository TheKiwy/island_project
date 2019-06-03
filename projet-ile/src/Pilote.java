public class Pilote extends Aventurier {

    Pilote() {
        super();
        this.setPion(Utils.Pion.BLEU);
    }

	public void deplacementSpecial(Tuile tuile) {
            if (tuile.getEtat() != Utils.EtatTuile.COULEE){
                this.deplacerVers(tuile);
            }
	}

    public String getRole() {
            return "Pilote";
    }

}