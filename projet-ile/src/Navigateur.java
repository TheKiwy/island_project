public class Navigateur extends Aventurier {

	Navigateur(String nom, Tuile position, Utils.Pion pion) {
		super(nom, position, pion);
	}

//	public void deplacerAutreVers(Aventurier joueur, Tuile tuile) {     ==>> Méthode du controleur ??
//
//	}

	public String getRole() {
		return "Navigateur";
	}

}