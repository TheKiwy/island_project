public class Pilote extends Aventurier {

	Pilote(String nom, Tuile position, Utils.Pion pion) {
		super(nom, position, pion);
	}

	/**
	 *
	 * @param tuile
	 */
//	public void deplacementSpecial(Tuile tuile) {       ==>> Méthode du controleur ??
//
//	}

	public String getRole() {
		return "Pilote";
	}

}