
import java.util.ArrayList;
import java.util.Scanner;

public class CarteSac extends CarteTresor {

	public void action() {
		// passer par le controleur pour appeler la méthode ci-dessous
	}

	public void action(Tuile tuile, ArrayList<CarteTresor> defausse){
		boolean actionUtilisee = false;
		Scanner entree = new Scanner(System.in);
		System.out.println("Souhaitez-vous vraiment utiliser votre carte ? (oui/non)");
		if (entree.next() == "oui" && tuile.getEtat() == Utils.EtatTuile.INONDEE){
			tuile.assecher();
			this.getProprietaire().defausserCarteTresor(this, defausse);
		} else {
			System.out.println("Carte considérée comme non-utilisée");
			// carte non défaussée
		}
	}

	public String getType() {
		return "CarteSac";
	}

	public TypeTresor getTypeTresor() {
		return null; // pas de type tresor pour cette carte
	}

}