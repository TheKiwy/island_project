public class Tresor {

	private Aventurier possedePar;
	private TypeTresor type;
	Tresor (TypeTresor type) {
		this.type = type;
	}
	public TypeTresor getType() {
		return this.type;
	}

}