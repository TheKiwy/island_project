import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueAventurier extends Observe{

	private JFrame window;
	private ArrayList<JButton> buttons;

	VueAventurier(Aventurier a) {
		window = new JFrame(a.getNom());
		buttons = new ArrayList<>();

		JPanel main = new JPanel(new GridLayout(1,5));
			for (CarteTresor aCard : a.getCartes()) {
				JButton card = new JButton(aCard.getType());
				main.add(card);
				buttons.add(card);
				card.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Message m = new Message();
						m.numCarteTresor = buttons.indexOf(card);
					}
				});
			}

		window.add(main);
	}
	public void setVisible(Boolean bool) {
		window.setSize(200,200);
		window.setVisible(bool);
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
		if (a.getRole() == "Pilote" || a.getRole() == "Navigateur") {
			do {
				do {
					System.out.println(
							"		#======================#\n" +
									"	| 1: Se déplacer       |\n" +
									"	| 2: Assécher          |\n" +
									"	| 3: Echanger          |\n" +
									"	| 4: Action Spéciale   |\n" +
									"	| 5: Récupérer trésor  |\n" +
									"	| 6: Utiliser carte    |\n" +
									"	| 7: Fin du tour       |\n" +
									"	#======================#\n" +
									"Taper le numéro de l'action : "
					);
					action = entre.nextInt();
				} while (action != 1 && action != 2 && action != 3 && action != 4 && action != 5 && action != 6 && action != 7);
				System.out.println("Vous avez renseigné l'action " + action + ", êtes vous sûr ? (oui/non)");
			} while (!entre2.nextLine().equals("oui"));
			if (action == 1) {
				m.type = TypesMessage.DEPLACER;
				m.joueurCourant = a;
			} else if (action == 2) {
				m.type = TypesMessage.ASSECHER;
				m.joueurCourant = a;
			} else if (action == 4) {
				m.type = TypesMessage.ACTION_SPECIALE;
				m.joueurCourant = a;
			} else if (action == 3) {
				m.type = TypesMessage.DON_DE_CARTE;
				m.joueurCourant = a;
			} else if (action == 5) {
				m.type = TypesMessage.RECUPERER_TRESOR;
				m.joueurCourant = a;
			} else if (action == 6) {
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
			} else if (action == 7) {
				m.type = TypesMessage.FIN_TOUR;
			}

		} else if (a.getRole() == "Ingenieur") {
			do {
				do {
					System.out.println(
							"	#======================#\n" +
									"	| 1: Se déplacer       |\n" +
									"	| 2: Assécher          |\n" +
									"       | 3: Assécher special  |\n" +
									"	| 4: Echanger          |\n" +
									"	| 5: Récupérer trésor  |\n" +
									"	| 6: Utiliser carte    |\n" +
									"	| 7: Fin du tour       |\n" +
									"	#======================#\n" +
									"Taper le numéro de l'action : "
					);
					action = entre.nextInt();
				} while (action != 1 && action != 2 && action != 3 && action != 4 && action != 5 && action != 6 && action != 7);
				System.out.println("Vous avez renseigné l'action " + action + ", êtes vous sûr ? (oui/non)");
			} while (!entre2.nextLine().equals("oui"));
			if (action == 1) {
				m.type = TypesMessage.DEPLACER;
				m.joueurCourant = a;
			} else if (action == 2) {
				m.type = TypesMessage.ASSECHER;
				m.joueurCourant = a;
			} else if (action == 3) {
				m.type = TypesMessage.ASSECHER_SPECIAL;
				m.joueurCourant = a;
			} else if (action == 4) {
				m.type = TypesMessage.DON_DE_CARTE;
				m.joueurCourant = a;
			} else if (action == 5) {
				m.type = TypesMessage.RECUPERER_TRESOR;
				m.joueurCourant = a;
			} else if (action == 6) {
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
			} else if (action == 7) {
				m.type = TypesMessage.FIN_TOUR;
			}
		} else {
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
					m.joueur = entre.nextLine();
					System.out.println("Vous avez renseigné " + m.joueur + ", êtes vous sûr(e) ? (oui/non)");
				} while (entre.nextLine() !="oui");
				m.type = TypesMessage.DON_DE_CARTE;
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
		}

		notifyObservateur(m);
	}

	public void afficherDonCarte(ArrayList<Aventurier> receveurs, Aventurier avCourant) {
		Scanner entre = new Scanner(System.in);
		Scanner entre2 = new Scanner(System.in);
		Message m = new Message();
		int j = 1;
		int choice;

		// Afficher si pas de joueur ou carte disponible !!!!!!!!

		if (receveurs.isEmpty() || avCourant.getCartes().isEmpty()){
			if (avCourant.getCartes().isEmpty()){
				System.out.println("Vous n'avez aucune carte à donner, action annulée.");
			} else {
				System.out.println("Aucun joueur n'est disponible pour recevoir une carte de votre part, action annulée.");
			}
		} else {

			System.out.println("Voici les joueurs à qui vous pouvez donner des cartes : ");
			for (Aventurier av : receveurs) {
				System.out.println(j + ": " + receveurs.get(j - 1).getNom());
				j++;
			}
			do {
				do {
					System.out.println("Entrez le numéro du joueur à qui vous souhaitez donner la carte : ");
					choice = entre.nextInt();
				} while (choice < 1 || choice > receveurs.size());
				Aventurier receveur = receveurs.get(choice - 1);
				System.out.println();
				System.out.println("Vous avez choisi de donner votre carte à : " + receveur.getNom());
				System.out.println("Êtes vous sûr(e) ? (oui/non)");
			} while (!entre2.nextLine().equals("oui"));
			m.receveur = receveurs.get(choice - 1);
			j = 1;
			System.out.println("Voici les cartes dont vous disposez : ");
			ArrayList<CarteTresor> cartesDuDonneur = avCourant.getCartes();
			for (CarteTresor carte : cartesDuDonneur){
				if (cartesDuDonneur.get(j - 1).getType() == "CarteMorceauTresor"){
					System.out.println(j + ": " + cartesDuDonneur.get(j - 1).getTypeTresor());
				} else {
					System.out.println(j + ": " + cartesDuDonneur.get(j - 1).getType());
				}
				j++;
			}
			do {
				do {
					System.out.println("Entrez le numéro de la carte que vous souhaitez donner : ");
					choice = entre.nextInt();
				} while (choice < 1 || choice > cartesDuDonneur.size());
				CarteTresor carteChoisie = cartesDuDonneur.get(choice - 1);
				System.out.println();
				System.out.println("Vous avez choisi de donner la carte : " + carteChoisie.getType());
				System.out.println("Êtes vous sûr(e) ? (oui/non)");
			} while (!entre2.nextLine().equals("oui"));
			m.numCarteTresor = choice - 1;
			m.joueurCourant = avCourant;
			m.type = TypesMessage.DONNER_CARTE;
			notifyObservateur(m);
		}
	}
	public void afficherAutresJoueurs(Aventurier joueurActuel, HashMap<Aventurier, ArrayList<Tuile>> deplacements, Grille g) {
		// Declaration
		Scanner entre = new Scanner(System.in);
		Scanner entre2 = new Scanner(System.in);
		Message m = new Message();
		int j = 1;
		int k = 1;
		int choice1;
		int choice2;
		ArrayList<Aventurier> joueursDeplacables = new ArrayList<>();
		joueursDeplacables.addAll(deplacements.keySet());
		ArrayList<ArrayList<Tuile>> deplacementsPossiblesJoueur = new ArrayList<>();
		deplacementsPossiblesJoueur.addAll(deplacements.values());

		// Affichage des possibilités
		System.out.println("Voici les joueurs déplaçables :");
		if (!joueursDeplacables.isEmpty()) {
			for (Aventurier joueur : joueursDeplacables) {
				System.out.println(j + ": " + joueur.getNom());
				j++;
			}

			do {
				do {
					System.out.println("Entrez le numéro du joueur à déplacer :");
					choice1 = entre2.nextInt();
				} while (choice1 < 1 || choice1 > joueursDeplacables.size());
				System.out.println();
				System.out.println("Votre choix est de déplacer : " + joueursDeplacables.get(choice1 - 1).getNom());
				System.out.println("Êtes vous sûr(e) ? (oui/non)");
			} while (!entre.nextLine().equals("oui"));
			m.joueurCourant = joueursDeplacables.get(choice1 - 1);


			System.out.println("Voici les tuiles que le joueur peut atteindre :");
			for (Tuile tuile : deplacementsPossiblesJoueur.get(choice1 - 1)) {
				System.out.println(k + ": " + tuile.getNom());
				k++;
			}

			do {
				do {
					System.out.println("Entrez le numéro de la tuile choisie :");
					choice2 = entre2.nextInt();
				} while (choice2 < 1 || choice2 > deplacementsPossiblesJoueur.get(choice1 - 1).size());
				System.out.println();
				System.out.println("Votre choix est de déplacer : " + joueursDeplacables.get(choice1 - 1).getNom() + " vers " + deplacementsPossiblesJoueur.get(choice1 - 1).get(choice2 - 1).getNom());
				System.out.println("Êtes vous sûr(e) ? (oui/non)");
			} while (!entre.nextLine().equals("oui"));
			m.tuile = deplacementsPossiblesJoueur.get(choice1 - 1).get(choice2 - 1);
			m.type = TypesMessage.DEPLACER_VERS;
			notifyObservateur(m);
		} else {
			System.out.println("Aucun joueur n'est déplaçable");
		}
	}
}