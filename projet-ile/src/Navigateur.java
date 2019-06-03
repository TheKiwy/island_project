public class Navigateur extends Aventurier {

	Navigateur(Tuile tuile) {
		super();
		this.setPion(Utils.Pion.JAUNE);
		setPosition(tuile);
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