import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueGrille extends Observe{

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
		System.out.println("Voici le nom de chaque lieu de l'île:");
		for (int i=0; i<grille.getTuiles().size();i++) {
			System.out.println(i+1 + ": " + grille.getTuiles().get(i).getNom());
		}
	}

	public void afficherDeplacementsPossibles(ArrayList<Tuile> tuiles, Grille grille, Aventurier avCourant) {
			// Declaration
		Scanner entre = new Scanner(System.in);
                Scanner entre2 = new Scanner(System.in);
		Message m = new Message();
		int j = 1;
		int choice;

			// Instruction
		// Display possible displacement
		System.out.println("Voici les numéros de case disponibles pour le déplacement : ");
		if (tuiles.size() != 0) {
                        for (Tuile tuile : tuiles) {
                            for (int i = 0; i < grille.getTuiles().size(); i++)  {
                                if (grille.getTuiles().get(i) == tuile) {
                                    System.out.println(j + " : Vers la case " + (i + 1) + " : " + grille.getTuiles().get(i).getNom());
                                    j++;
                                }
                            }
                        }
                        
                        for (int i = 0; i < grille.getTuiles().size(); i++)  {
                            if (grille.getTuiles().get(i) == avCourant.getPosition()) {
                                System.out.println("\nVous êtes en case " + (i + 1) + " :  " + grille.getTuiles().get(i).getNom());
                            }
                        }

                    // User choice
                        do {
                                do {
                                        System.out.println("Veuillez indiquer le numéro de la case vers laquelle vous voulez vous déplacer : ");
                                        choice = entre.nextInt();
                                } while (choice < 1 || choice > tuiles.size());
                                System.out.println();
                                System.out.println("Votre choix est de vous déplacer vers : " + tuiles.get(choice - 1).getNom());
                                System.out.println("Êtes vous sûr(e) ? (oui/non)");
                        } while (!entre2.nextLine().equals("oui"));

                        m.type = TypesMessage.DEPLACER_VERS;
                        m.tuile = tuiles.get(choice - 1);
                        m.joueurCourant = avCourant;
                        notifyObservateur(m);
		} else {
			System.out.println("Il n'y pas de case possible pour le déplacement autour de vous.");
		}
	}

	public void afficherAssechementsPossibles(ArrayList<Tuile> tuiles, Grille grille, Aventurier avCourant) {
		// Declaration
		Scanner entre = new Scanner(System.in);
                Scanner entre2 = new Scanner(System.in);
		Message m = new Message();
		int j = 1;
		int choice;

			// Instruction
		// Display possible displacement
		System.out.println("Voici les numéros de case disponibles pour l'assèhement : ");
		if (tuiles.size() != 0) {
                        for (Tuile tuile : tuiles) {
                            for (int i = 0; i < grille.getTuiles().size(); i++)  {
                                if (grille.getTuiles().get(i) == tuile) {
                                    System.out.println(j + " : Assècher la case " + (i + 1) + " : " + grille.getTuiles().get(i).getNom());
                                    j++;
                                }
                            }
                        }
                        
                        for (int i = 0; i < grille.getTuiles().size(); i++)  {
                            if (grille.getTuiles().get(i) == avCourant.getPosition()) {
                                System.out.println("\nVous êtes en case " + (i + 1) + " :  " + grille.getTuiles().get(i).getNom());
                            }
                        }

                    // User choice
                        do {
                                do {
                                        System.out.println("Veuillez indiquer le numéro de la case que vous voulez assècher : ");
                                        choice = entre.nextInt();
                                } while (choice < 1 || choice > tuiles.size());
                                System.out.println();
                                System.out.println("Votre choix est d'assècher : " + tuiles.get(choice - 1).getNom());
                                System.out.println("Êtes vous sûr(e) ? (oui/non)");
                        } while (!entre2.nextLine().equals("oui"));

                        m.type = TypesMessage.ASSECHER_VERS;
                        m.tuile = tuiles.get(choice - 1);
                        m.joueurCourant = avCourant;
                        notifyObservateur(m);
		} else {
			System.out.println("Il n'y pas de case possible pour l'assèchement autour de vous.");
		}

	}

	public void cacher() {
		// For graphic view
	}
}