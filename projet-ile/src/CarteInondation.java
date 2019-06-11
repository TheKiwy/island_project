public class CarteInondation {

	private Tuile tuileConcernee;
        
        CarteInondation(Tuile tuile) {
            this.tuileConcernee = tuile;
        }

	public void inonder() {

		tuileConcernee.inonder();
	}

}