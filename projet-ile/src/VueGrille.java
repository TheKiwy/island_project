import java.util.ArrayList;
import java.util.HashMap;

public class VueGrille extends Obseve{



	public void afficher(Grille grille)  {
			// Console View

		// Declaration

		// Grid display
		System.out.println("Voici l'Île interdite");
		System.out.println();
		System.out.println();

		System.out.println(
				"        +---+---+        "+
				"        | 1 | 2 |        "+
				"    +---+---+---+---+    "+
				"    | 3 | 4 | 5 | 6 |    "+
				"+---+---+---+---+---+---+"+
				"| 7 | 8 | 9 | 10| 11| 12|"+
				"+---+---+---+---+---+---+"+
				"| 13| 14| 15| 16| 17| 18|"+
				"+---+---+---+---+---+---+"+
				"    | 19| 20| 21| 22|    "+
				"    +---+---+---+---+    "+
				"        | 23| 24|        "+
				"        +---+---+        "
		);

		System.out.println();
		System.out.println("Voicie le nom de chaque lieux de l'île");
		int j = 0;



	}

	public void cacher() {

	}

	public void afficherDeplacementsPossibles(ArrayList<Tuile> tuiles) {

	}

}