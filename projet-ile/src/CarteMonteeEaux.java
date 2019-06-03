
import java.util.ArrayList;

public class CarteMonteeEaux extends CarteTresor {
	@Override
	public void action(int niveauEau, ArrayList<CarteInondation> pileInondation, ArrayList<CarteInondation> defausseInondation){
		niveauEau += 1;
		pileInondation.addAll(defausseInondation);
		//pileInondation.spliterator(); (j'ai trouvé ça je sais pas si ça mélange) dans tous les cas : mélanger la pile inondation
		defausseInondation.removeAll(defausseInondation); // vide la défausse inondation
	}

	public String getType() {
		return "CarteMonteeEaux";
	}

}