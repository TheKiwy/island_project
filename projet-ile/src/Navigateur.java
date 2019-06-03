public class Navigateur extends Aventurier {

	Navigateur() {
		super();
		this.setPion(Utils.Pion.JAUNE);
	}

	public void deplacerAutreVers(Aventurier joueur, Tuile tuile) {
		if (tuile.getEtat() != Utils.EtatTuile.COULEE){
			joueur.deplacerVers(tuile);
		}
	}

	public String getRole() {
		return "Navigateur";
	}

}