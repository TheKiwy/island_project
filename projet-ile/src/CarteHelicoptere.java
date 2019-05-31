
import java.util.ArrayList;
import java.util.Scanner;

public class CarteHelicoptere extends CarteTresor {

	public void action() {
		// passer par le controleur pour appeler la méthode ci-dessous
	}

	public void action(Tuile tuileDepart, Tuile tuileArrivee, ArrayList<CarteTresor> defausse){
		boolean actionUtilisee = false;
		Scanner entree = new Scanner(System.in);
		for (Aventurier joueur : tuileDepart.getJoueurs()){
			System.out.println("Le joueur " + joueur.getNom() + " est sur la tuile de départ sélectionnée, souhaitez-vous qu'il soit déplacé sur la tuile d'arrivée sélectionnée ? (oui/non)");
			if (entree.next() == "oui"){
				joueur.setPosition(tuileArrivee);
				actionUtilisee = true;
			}
		}
		if (!actionUtilisee){
			System.out.println("Carte considérée comme non-utilisée");
			// carte non défaussée
		} else {
			this.getProprietaire().defausserCarteTresor(this, defausse);
		}
	}

	public String getType() {
		return "CarteHelicoptere";
	}

	public TypeTresor getTypeTresor() {
		return null; // pas de type tresor pour cette carte
	}

}