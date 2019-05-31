
import java.util.ArrayList;

public class CarteMonteeEaux extends CarteTresor {

	public void action() {
		// passer par le controleur
	}

	public void action(int niveauEau, ArrayList<CarteInondation> pileInondation, ArrayList<CarteInondation> defausseInondation){
		niveauEau += 1;
		pileInondation.addAll(defausseInondation);
		//pileInondation.spliterator(); (j'ai trouvé ça je sais pas si ça mélange) dans tous les cas : mélanger la pile inondation
		defausseInondation.removeAll(defausseInondation); // vide la défausse inondation
	}

	public String getType() {
		return "CarteMonteeEaux";
	}

	public TypeTresor getTypeTresor() {
		return null; // pas de type tresor pour cette carte
	}

}