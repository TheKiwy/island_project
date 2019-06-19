import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueGrille extends Observe{

    private ArrayList<JButton> boutons = new ArrayList<>();
    private int typeAction = 0;

    private JFrame windowGrille;

    VueGrille(Grille grille) {
        windowGrille = new JFrame("Grille");
        windowGrille.setSize(1500, 1000);



        Tuile s = new Tuile(null, null);
        String nomTuile;
        // création des panels et layouts
        JPanel panelFenetre = new JPanel();
        BorderLayout borderFenetre = new BorderLayout();
        JPanel panelGrille = new JPanel();
        GridLayout layoutGrille = new GridLayout(6,6);
        JPanel panelActions = new JPanel();
        BorderLayout borderActions = new BorderLayout();




        //ajouts des panels et layouts
        windowGrille.add(panelFenetre);
        panelFenetre.setLayout(borderFenetre);
        panelGrille.setPreferredSize(new Dimension(windowGrille.getWidth()*2/3, panelGrille.getHeight()));
        panelFenetre.add(panelGrille, BorderLayout.CENTER);
        panelGrille.setLayout(layoutGrille);

        panelActions.setPreferredSize(new Dimension(windowGrille.getWidth()*2/3, (windowGrille.getHeight())*1/5));
        panelFenetre.add(panelActions, BorderLayout.SOUTH);
        panelActions.setLayout(borderActions);



        //creation des boutons et ajout à la grille
        JButton tuileij;
        for (int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {

                s = grille.getGrille()[i][j];
                if (grille.getGrille()[i][j] != null) {
                    nomTuile = s.getNom();
                    tuileij = new JButton(nomTuile);
                    boutons.add(tuileij);
                    Tuile t = grille.getTuileCoord(i, j);
                    tuileij.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Message m = new Message();

                            if (typeAction == 1) {
                                m.type = TypesMessage.DEPLACER_VERS;

                            } else if (typeAction == 2){
                                m.type = TypesMessage.ASSECHER_VERS;
                            }

                            m.tuile = t;
                            notifyObservateur(m);

                        }

                    });
                } else {
                    tuileij = new JButton();
                    tuileij.setVisible(false);
                }
                panelGrille.add(tuileij);
                tuileij.setEnabled(false);
            }
        }

        //création des actions
        JPanel panelActionsPrincipales = new JPanel();

        panelActions.add(panelActionsPrincipales, BorderLayout.CENTER);

        GridLayout gridActionsP = new GridLayout(1,5);
        panelActionsPrincipales.setLayout(gridActionsP);
        JButton boutonDeplacer = new JButton("Se Déplacer");
        boutonDeplacer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                typeAction = 1;
                Message m = new Message();
                m.type = TypesMessage.DEPLACER;
                notifyObservateur(m);

            }

        });
        JButton boutonAssecher = new JButton("Assecher une case");
        boutonAssecher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                typeAction = 2;
                Message m = new Message();
                m.type = TypesMessage.ASSECHER;
                notifyObservateur(m);

            }

        });
        JButton boutonDonnerCarte = new JButton("Donner une carte");
        boutonDonnerCarte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.DONNER_CARTE;
                notifyObservateur(m);

            }

        });

        JButton boutonRecupTresor = new JButton("Donner une carte");
        boutonRecupTresor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.RECUPERER_TRESOR;
                notifyObservateur(m);

            }

        });

        JButton boutonPasserTour = new JButton("Passer son tour");

        boutonPasserTour.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.FIN_TOUR;
                notifyObservateur(m);

            }

        });
        panelActionsPrincipales.add(boutonDeplacer);
        panelActionsPrincipales.add(boutonAssecher);
        panelActionsPrincipales.add(boutonDonnerCarte);
        panelActionsPrincipales.add(boutonRecupTresor);
        panelActionsPrincipales.add(boutonPasserTour);

        JButton boutonActionSpe = new JButton("Utiliser l'action spéciale de votre aventurier");
        boutonActionSpe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.ACTION_SPECIALE;
                notifyObservateur(m);

            }

        });
        boutonActionSpe.setPreferredSize(new Dimension(panelActions.getWidth(), 50));
        panelActions.add(boutonActionSpe, BorderLayout.SOUTH);

    }
    public void visible() {
        windowGrille.setVisible(true);
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