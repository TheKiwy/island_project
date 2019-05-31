public class Ingenieur extends Aventurier {

	Ingenieur(String nom, Tuile position, Utils.Pion pion) {
		super(nom, position, pion);
	}

	public String getRole() {
		return "Ingenieur";
	}

}