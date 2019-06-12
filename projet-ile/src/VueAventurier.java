import java.util.Scanner;

public class VueAventurier extends Observe{

	VueAventurier() {

	}

	public void afficher(Aventurier a) {
			// Console View

		// Declaration
		Scanner entre = new Scanner(System.in);
		Scanner entre2 = new Scanner(System.in);
		int action;
		Message m = new Message();

		// Player Action
		System.out.println(a.getNom() + ", vous êtes " + a.getRole() + ", que voulez vous faire ?");
		System.out.println();
		do {
			do {
				System.out.println(
								"	#======================#\n" +
								"	| 1: Se déplacer       |\n" +
								"	| 2: Assécher          |\n" +
								"	| 3: Echanger          |\n" +
								"	| 4: Récupérer trésor  |\n" +
								"	| 5: Utiliser carte    |\n" +
								"	| 6: Fin du tour       |\n" +
								"	#======================#\n" +
								"Taper le numéro de l'action : "
				);
				action = entre.nextInt();
			} while (action != 1 && action != 2 && action != 3 && action != 4 && action != 5 && action != 6);
			System.out.println("Vous avez renseigné l'action " + action + ", êtes vous sûr ? (oui/non)");
		} while (!entre2.nextLine().equals("oui"));


		if (action == 1) {
			m.type = TypesMessage.DEPLACER;
			m.joueurCourant = a;
		} else if (action == 2) {
			m.type = TypesMessage.ASSECHER;
                        m.joueurCourant = a;
		} else if (action == 3) {
			do {
				System.out.println("Veuillez indiquer le nom du joueur avec qui vous voulez échanger:");
				m.echangeJ = entre.nextLine();
				System.out.println("Vous avez renseigné " + m.echangeJ + ", êtes vous sûr(e) ? (oui/non)");
			} while (entre.nextLine() !="oui");
			m.type = TypesMessage.ECHANGER;
			m.joueurCourant = a;
		} else if (action == 4) {
			m.type = TypesMessage.RECUPERER_TRESOR;
			m.joueurCourant = a;
		} else if (action == 5) {
			System.out.println("L'aventurier qui veut jouer la carte est-il le joueur actuel ? (oui/non)");
			if (entre.nextLine() == "oui") {
				m.type = TypesMessage.UTILISER_CARTE_COURANT;
				m.joueurCourant = a;
			} else {
				String nom = null;
				System.out.print("Veuillez indiquer le nom du joueur:");
				m.joueur = nom;
				m.type = TypesMessage.UTILISER_CARTE_NON_COURANT;
			}
		} else if (action == 6) {
			m.type = TypesMessage.FIN_TOUR;
		}

		notifyObservateur(m);
	}

	public void cacher() {
		// TODO - implement VueAventurier.cacher
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 */
	public void mettreEnAvant(Aventurier aventurier) {
		// TODO - implement VueAventurier.mettreEnAvant
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 */
	public void mettreEnArrière(Aventurier aventurier) {
		// TODO - implement VueAventurier.mettreEnArri�re
		throw new UnsupportedOperationException();
	}

}