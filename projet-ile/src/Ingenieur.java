public class Ingenieur extends Aventurier {

	Ingenieur(Tuile tuile) {
		super();
		super.setPion(Utils.Pion.ROUGE);
		setPosition(tuile);
	}
	public String getRole() {
		return "Ingenieur";
	}

}

