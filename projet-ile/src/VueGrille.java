import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VueGrille extends Observe{

    private ArrayList<JButton> boutons = new ArrayList<>();
    private JButton boutonPasserTour = new JButton("Passer son tour");
    private JButton boutonRecupTresor = new JButton("Récuperer un trésor");
    private JButton boutonDonnerCarte = new JButton("Donner une carte");
    private JButton boutonAssecher = new JButton("Assecher une case");
    private JButton boutonDeplacer = new JButton("Se Déplacer");
    private JButton boutonActionSpe = new JButton("Utiliser l'action spéciale de votre aventurier");
    private int typeAction = 0;
    JLabel label = new JLabel("");

    private JFrame windowGrille;

    VueGrille(Grille grille) {

        windowGrille = new JFrame("Grille");
        windowGrille.setSize(1500, 1000);



        Tuile s = new Tuile(null, null);
        String nomTuile;
        // crÃ©ation des panels et layouts
        JPanel panelFenetre = new JPanel();
        BorderLayout borderFenetre = new BorderLayout();
        JPanel panelGrille = new JPanel();
        GridLayout layoutGrille = new GridLayout(6,6);
        JPanel panelActions = new JPanel();
        BorderLayout borderActions = new BorderLayout();
        JPanel panelLabel = new JPanel();






        //ajouts des panels et layouts
        panelLabel.add(label);

        windowGrille.add(panelFenetre);
        panelFenetre.add(panelLabel, BorderLayout.NORTH);
        panelFenetre.setLayout(borderFenetre);
        panelGrille.setPreferredSize(new Dimension(windowGrille.getWidth()*2/3, panelGrille.getHeight()));
        panelFenetre.add(panelGrille, BorderLayout.CENTER);
        panelGrille.setLayout(layoutGrille);

        panelActions.setPreferredSize(new Dimension(windowGrille.getWidth()*2/3, (windowGrille.getHeight())*1/5));
        panelFenetre.add(panelActions, BorderLayout.SOUTH);
        panelActions.setLayout(borderActions);



        //creation des boutons et ajout Ã  la grille
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

        //crÃ©ation des actions
        JPanel panelActionsPrincipales = new JPanel();

        panelActions.add(panelActionsPrincipales, BorderLayout.CENTER);

        GridLayout gridActionsP = new GridLayout(1,5);
        panelActionsPrincipales.setLayout(gridActionsP);

        boutonDeplacer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                typeAction = 1;
                Message m = new Message();
                m.type = TypesMessage.DEPLACER;
                notifyObservateur(m);

            }

        });

        boutonAssecher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                typeAction = 2;
                Message m = new Message();
                m.type = TypesMessage.ASSECHER;
                notifyObservateur(m);

            }

        });

        boutonDonnerCarte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.DONNER_CARTE;
                notifyObservateur(m);

            }

        });


        boutonRecupTresor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessage.RECUPERER_TRESOR;
                notifyObservateur(m);

            }

        });



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

    public void afficherDeplacer()  {
        // Graphic View

        // Declaration

        // Label display
        label.setText("Choisissez une case pour vous déplacer");
    }

    public void afficherAssecher()  {
        // Graphic View

        // Declaration

        // Label display
        label.setText("Choisissez une case à assechcer");
    }

    public void afficherDeplacementsPossibles(ArrayList<Tuile> tuiles, Grille grille, Aventurier avCourant) {
        // Declaration

        Message m = new Message();



        // Instruction
        // Display possible displacement

        if (tuiles.size() != 0) {
            for (Tuile tuile : tuiles) {
                for (JButton bouton : boutons) {
                    if (tuile.getNom() == bouton.getText()) {
                        bouton.setEnabled(true);
                    }
                }
            }
        } else {
            label.setText("aucune case n'est disponible pour vous déplacer, effectuez une autre action");
            changerEtatActions(1);
        }
    }

    public void changerEtatActions(int i) {
        if (i == 0) {
            boutonDeplacer.setEnabled(false);
            boutonAssecher.setEnabled(false);
            boutonDonnerCarte.setEnabled(false);
            boutonRecupTresor.setEnabled(false);
            boutonPasserTour.setEnabled(false);
            boutonActionSpe.setEnabled(false);
        } else if (i == 1) {
            boutonDeplacer.setEnabled(true);
            boutonAssecher.setEnabled(true);
            boutonDonnerCarte.setEnabled(true);
            boutonRecupTresor.setEnabled(true);
            boutonPasserTour.setEnabled(true);
            boutonActionSpe.setEnabled(true);
        }
    }

    public void desactiverGrille() {
        for (JButton j : boutons) {
            j.setEnabled(false);
        }
    }
    public void afficherAssechementsPossibles(ArrayList<Tuile> tuiles, Grille grille, Aventurier avCourant) {
        // Declaration

        Message m = new Message();


        // Instruction
        // Display possible displacement

        if (tuiles.size() != 0) {
            for (Tuile tuile : tuiles) {
                for (JButton bouton : boutons) {
                    if (tuile.getNom() == bouton.getText()) {
                        bouton.setEnabled(true);
                    }
                }
            }
        } else {
            label.setText("aucune case ne peut être assechée, effectuez une autre action");
            changerEtatActions(1);
        }

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



}