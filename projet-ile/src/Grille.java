
import java.util.*;

public class Grille {

	private HashMap<Integer, Tuile> tuiles;

	private Tuile[][] tableau;

	public Grille() {
		tuiles.put(1, new Tuile(Role.recupCristal, "La Caverne des Ombres"));
		tuiles.put(2, new Tuile(Role.aucun, "Le Val du Crépuscule"));
		tuiles.put(3, new Tuile(Role.porteVerte, "La Porte de Cuivre"));
		tuiles.put(4, new Tuile(Role.porteNoire, "La Porte de Fer"));
		tuiles.put(5, new Tuile(Role.aucun, "Le Marais Brumeux"));
		tuiles.put(6, new Tuile(Role.recupZephyr, "Le Val du Crépuscule"));
		tuiles.put(7, new Tuile(Role.recupCristal, "La Caverne du Brasier"));
		tuiles.put(8, new Tuile(Role.porteJaune, "La Porte d'Or"));
		tuiles.put(9, new Tuile(Role.recupPierre, "Le Temple du Soleil"));
		tuiles.put(10, new Tuile(Role.aucun, "Le Pont des Abîmes"));
		tuiles.put(11, new Tuile(Role.porteGrise, "La Porte d'Argent"));
		tuiles.put(12, new Tuile(Role.recupZephyr, "Jardin des Hurlements"));
		tuiles.put(13, new Tuile(Role.aucun, "Les Dunes de l'Illusion"));
		tuiles.put(14, new Tuile(Role.aucun, "Les Falaises de l'Oubli"));
		tuiles.put(15, new Tuile(Role.aucun, "L'Observatoire"));
		tuiles.put(16, new Tuile(Role.recupCalice, "Le Palais de Marées"));
		tuiles.put(17, new Tuile(Role.heliport, "L'Heliport"));
		tuiles.put(18, new Tuile(Role.aucun, "Le Rocher Fantôme"));
		tuiles.put(19, new Tuile(Role.recupPierre, "Le Temple de la Lune"));
		tuiles.put(20, new Tuile(Role.aucun, "La Forêt Pourpre"));
		tuiles.put(21, new Tuile(Role.aucun, "Le Lagon Perdu"));
		tuiles.put(22, new Tuile(Role.porteRouge, "La Porte de Bronze"));
		tuiles.put(23, new Tuile(Role.recupCalice, "Le Palais de Corails"));
		tuiles.put(24, new Tuile(Role.aucun, "La Tour de Guet"));

		Random rn1 = new Random();
		ArrayList<Integer> verif = new ArrayList<>();
		boolean fait = false;
		tableau = new Tuile[6][6];

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				fait = false;
				if ((i == 0 && j == 2) || (i == 0 && j == 3) || (i == 1 && j == 1) || (i == 1 && j == 2) || (i == 1 && j == 3) || (i == 1 && j == 4) || (i == 2 && j == 0)
						|| (i == 2 && j == 1) || (i == 2 && j == 2) || (i == 2 && j == 3) || (i == 2 && j == 4) || (i == 2 && j == 5) || (i == 3 && j == 0) || (i == 3 && j == 1)
						|| (i == 3 && j == 2) || (i == 3 && j == 3) || (i == 3 && j == 4) || (i == 3 && j == 5) || (i == 4 && j == 1) || (i == 4 && j == 2) || (i == 4 && j == 3)
						|| (i == 4 && j == 4) || (i == 5 && j == 2) || (i == 5 && j == 2) || (i == 5 && j == 3)) {
					while (!fait) {
						int k = 1 + rn1.nextInt(24 - 1 + 1);
						if (!verif.contains(k)) {
							verif.add(k);
							fait = true;
							tableau[i][j] = tuiles.get(k);
						}
					}
				}
			}
		}

	}

}
