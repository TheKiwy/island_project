import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueGrille extends Obseve{



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

	public void cacher() {

	}

	public void afficherDeplacementsPossibles(ArrayList<Tuile> tuiles) {
		Scanner entre = new Scanner(System.in);


		System.out.println("Veuillez indiquez le numeros de la vers le quelle vous voulez vous deplacer: ");

	}

}