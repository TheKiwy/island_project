public class CarteMorceauTresor extends CarteTresor {

	private TypeTresor type;

	@Override
	public TypeTresor getTypeTresor() {
		return type;
	}

	public String getType() {
		return "CarteMorceauTresor";
	}

}