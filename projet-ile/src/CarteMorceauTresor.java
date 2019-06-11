public class CarteMorceauTresor extends CarteTresor {

	private TypeTresor type;
        
        CarteMorceauTresor(TypeTresor t) {
            type = t;
        }

	@Override
	public TypeTresor getTypeTresor() {
		return type;
	}

	public String getType() {
		return "CarteMorceauTresor";
	}

}