import java.util.Scanner;

public class VueAventurier extends Obseve{


	public void afficher(Aventurier a) {
			// Console View

		// Declaration
		Scanner entre = new Scanner(System.in);
		int action;
		Message m = new Message();

		// Player Action
		System.out.println(a.getNom() + ", que voulez vous faire ?");
		System.out.println();
		do {
			do {
				System.out.println(
								"	#======================#" +
								"	| 1: Se déplacer       |" +
								"	| 2: Assécher          |" +
								"	| 3: Echanger          |" +
								"	| 4: Récupérer trésor  |" +
								"	| 5: Utiliser carte    |" +
								"	| 6: Fin du tour       |" +
								"	#======================#" +
								"Taper le numéros des actions:"
				);
				action = entre.nextInt();
			} while (action == 1 || action == 2 || action == 3 || action == 4 || action == 5 || action == 6);
			System.out.println("Vous avez renseigné l'action" + action + ", êtes vous sûr ? oui/non");
		} while (entre.nextLine() == "non");


		if (action == 1) {
			m.type = TypesMessage.DEPLACER;
		} else if (action == 2) {
			m.type = TypesMessage.ASSECHER;
		} else if (action == 3) {
			m.type = TypesMessage.ECHANGER;
		} else if (action == 4) {
			m.type = TypesMessage.RECUPERER_TRESOR;
		} else if (action == 5) {
			System.out.println("L'avanturier qui veut joué la carte, est-il joueur ? oui/non");
			if (entre.nextLine() == "oui") {
				m.type = TypesMessage.UTILISER_CARTE_COURANT;
				m.joueur = a.getNom();
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