import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueGrille extends Obseve{

	VueGrille() {
	}


	public void afficher(Grille grille)  {
			// Console View

		// Declaration

		// Grid display
		System.out.println("Voici l'Île interdite");
		System.out.println();
		System.out.println();

		System.out.println(
				"        +---+---+         \n"+
				"        | 1 | 2 |         \n"+
				"    +---+---+---+---+     \n"+
				"    | 3 | 4 | 5 | 6 |     \n"+
				"+---+---+---+---+---+---+ \n"+
				"| 7 | 8 | 9 | 10| 11| 12| \n"+
				"+---+---+---+---+---+---+ \n"+
				"| 13| 14| 15| 16| 17| 18| \n"+
				"+---+---+---+---+---+---+ \n"+
				"    | 19| 20| 21| 22|     \n"+
				"    +---+---+---+---+     \n"+
				"        | 23| 24|         \n"+
				"        +---+---+         \n"
		);

		System.out.println();
		System.out.println("Voici le nom de chaque lieux de l'île:");
		for (int i=0; i<grille.getTuiles().size();i++) {
			System.out.println(i+1 + ": " + grille.getTuiles().get(i).getNom());
		}
	}

	public void afficherDeplacementsPossibles(ArrayList<Tuile> tuiles, Grille grille) {
			// Declaration
		Scanner entre = new Scanner(System.in);
		Message m = new Message();
		int j = 0;
		int choice;

			// Instruction
		// Display possible displacement
		System.out.println("Voicie les numéros de case disponible pour le deplacement:");
		if (tuiles.size() != 0) {
			for (int i = 0; i < grille.getTuiles().size(); i++) {
				if (grille.getTuiles().get(i) == tuiles.get(j)) {
					System.out.println(j + 1 + ": Vers la case" + i + grille.getTuiles().get(i));
					j++;
				}
			}

		// User choice
		do {
			do {
				System.out.println("Veuillez indiquez le numeros de la vers le quelle vous voulez vous deplacer: ");
				choice = entre.nextInt();
			} while (choice < 0 && choice >= tuiles.size());
			System.out.println();
			System.out.println("Votre choix est de ce déplacer vers :" + tuiles.get(choice).getNom());
			System.out.println("Êtes vous sûr(e) ? oui/non");
		} while (entre.nextLine() != "oui");

		m.type = TypesMessage.DEPLACER_VERS;
		m.tuile = tuiles.get(choice);
		} else {
			System.out.println("Il n'y pas de case possible pour le déplacement autour de vous.");
		}
		notifyObservateur(m);

	}

	public void afficherAssechementsPossibles(ArrayList<Tuile> tuiles, Grille grille) {
		// Declaration
		Scanner entre = new Scanner(System.in);
		Message m = new Message();
		int j = 0;
		int choice;

		// Instruction
		// Display possible displacement
		System.out.println("Voicie les numéros de case disponible pour l'asséchemment:");
		if (tuiles.size()!= 0) {
			for (int i = 0; i < grille.getTuiles().size(); i++) {
				if (grille.getTuiles().get(i) == tuiles.get(j)) {
					System.out.println(j + 1 + ": Vers la case" + i + grille.getTuiles().get(i));
					j++;
				}
			}

			// User choice
			do {
				do {
					System.out.println("Veuillez indiquez le numeros de la vers le quelle vous voulez vous assécher: ");
					choice = entre.nextInt();
				} while (choice < 0 && choice >= tuiles.size());
				System.out.println();
				System.out.println("Votre choix est de ce déplacer vers :" + tuiles.get(choice).getNom());
				System.out.println("Êtes vous sûr(e) ? oui/non");
			} while (entre.nextLine() != "oui");

			m.type = TypesMessage.ASSECHER_VERS;
			m.tuile = tuiles.get(choice);
		}
		notifyObservateur(m);

	}

	public void cacher() {
		// For graphic view
	}
}