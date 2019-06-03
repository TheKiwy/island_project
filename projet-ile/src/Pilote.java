public class Pilote extends Aventurier {

    Pilote(Tuile tuile) {
        super();
        this.setPion(Utils.Pion.BLEU);
        setPosition(tuile);
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