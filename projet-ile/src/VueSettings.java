import com.sun.jdi.IntegerValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

public class VueSettings extends Observe {

    private JFrame window;

    VueSettings() {
        // Window initialization
        window = new JFrame("Settings");

        // Main Panel
        JPanel main = new JPanel(new BorderLayout());

        // Title
        JPanel Title = new JPanel(new GridLayout(2,1));
        Title.add(new JLabel("Bienvenu sur le vole en direction de l'Île Interdite"));
        Title.add(new JLabel("Veuillez renseigner les information"));
        main.add(Title, BorderLayout.NORTH);

        // Player Setting;
        JPanel pSettings = new JPanel(new GridLayout(4,1));

        // Player 1
        JPanel player1 = new JPanel(new GridLayout(4,1));
        player1.add(new JLabel("Joueur 1"));
        // Name
        JPanel name1 = new JPanel(new GridLayout(1,2));
        name1.add(new JLabel("Nom: "));
        JTextField pName1 = new JTextField();
        name1.add(pName1);
        player1.add(name1);
        // Day
        JPanel day1 = new JPanel(new GridLayout(1,2));
        player1.add(new JLabel("Nb jours depuis la visite d'une île: "));
        JTextField pDay1 = new JTextField(pName1.getColumns());
        JRadioButton unknown1 = new JRadioButton("Je ne sais pas");
        unknown1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unknown1.isSelected()) {
                    pDay1.setText("");
                    pDay1.setEnabled(false);
                } else {
                    pDay1.setEnabled(true);
                }
            }
        });
        day1.add(pDay1);
        day1.add(unknown1);
        player1.add(day1);

        pSettings.add(player1);

        // Player 2
        JPanel player2 = new JPanel(new GridLayout(4,1));
        player2.add(new JLabel("Joueur 2"));
        // Name
        JPanel name2 = new JPanel(new GridLayout(1,2));
        name2.add(new JLabel("Nom: "));
        JTextField pName2 = new JTextField();
        name2.add(pName2);
        player2.add(name2);
        // Day
        JPanel day2 = new JPanel(new GridLayout(1,2));
        player2.add(new JLabel("Nb jours depuis la visite d'une île: "));
        JTextField pDay2 = new JTextField(10);
        JRadioButton unknown2 = new JRadioButton("Je ne sais pas");
        unknown2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unknown2.isSelected()) {
                    pDay2.setText("");
                    pDay2.setEnabled(false);
                } else {
                    pDay2.setEnabled(true);
                }
            }
        });
        day2.add(pDay2);
        day2.add(unknown2);
        player2.add(day2);

        pSettings.add(player2);

        // Player 3
        JPanel player3 = new JPanel(new GridLayout(4,1));
        player3.add(new JLabel("Joueur 3:"));
        // Name
        JPanel name3 = new JPanel(new GridLayout(1,2));
        name3.add(new JLabel("Nom: "));
        JTextField pName3 = new JTextField();
        name3.add(pName3);
        player3.add(name3);
        // Day
        JPanel day3 = new JPanel(new GridLayout(1,2));
        player3.add(new JLabel("Nb jours depuis la visite d'une île: "));
        JTextField pDay3 = new JTextField();
        JRadioButton unknown3 = new JRadioButton("Je ne sais pas");
        unknown3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unknown3.isSelected()) {
                    pDay3.setText("");
                    pDay3.setEnabled(false);
                } else {
                    pDay3.setEnabled(true);
                }
            }
        });
        day3.add(pDay3);
        day3.add(unknown3);
        player3.add(day3);
        player3.setVisible(false);

        pSettings.add(player3);

        // Player 4
        JPanel player4 = new JPanel(new GridLayout(4,1));
        player4.add(new JLabel("Joueur 4:"));
        // Name
        JPanel name4 = new JPanel(new GridLayout(1,2));
        name4.add(new JLabel("Nom: "));
        JTextField pName4 = new JTextField();
        name4.add(pName4);
        player4.add(name4);
        // Day
        JPanel day4 = new JPanel(new GridLayout());
        player4.add(new JLabel("Nb jours depuis la visite d'une île: "));
        JTextField pDay4 = new JTextField(8);
        JRadioButton unknown4 = new JRadioButton("Je ne sais pas");
        unknown4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unknown4.isSelected()) {
                    pDay4.setText("");
                    pDay4.setEnabled(false);
                } else {
                    pDay4.setEnabled(true);
                }
            }
        });
        day4.add(pDay4);
        day4.add(unknown4);
        player4.add(day4);
        player4.setVisible(false);

        pSettings.add(player4);

        main.add(pSettings,BorderLayout.CENTER);

        // Bottom button
        JPanel footer = new JPanel(new GridLayout(1,3));
        JButton addPlayer = new JButton("Ajouter joueur");

        JButton removePlayer = new JButton("Supprimer un joueur");
        removePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu confirm = new JPopupMenu("Confirmation");

                JPanel mainPop = new JPanel(new BorderLayout());

                if (player4.isVisible()) {
                    mainPop.add(new JLabel("Vous allez supprimer le Joueur 4 êtes vous sûr ?"),BorderLayout.NORTH);
                } else if (player3.isVisible()) {
                    mainPop.add(new JLabel("Vous allez supprimer le Joueur 3 êtes vous sûr ?"),BorderLayout.NORTH);
                }
                JButton valider = new JButton("Valider");
                valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player4.isVisible()) {
                            pName4.setText("");
                            pDay4.setText("");
                            player4.setVisible(false);

                        } else if (player3.isVisible()) {
                            pName3.setText("");
                            pDay3.setText("");
                            player3.setVisible(false);
                            removePlayer.setEnabled(false);

                        }
                        addPlayer.setEnabled(true);
                        confirm.setVisible(false);
                    }
                });
                mainPop.add(valider, BorderLayout.WEST);
                JButton cancel = new JButton("Annuler");
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirm.setVisible(false);
                    }
                });
                mainPop.add(cancel,BorderLayout.EAST);
                confirm.add(mainPop);
                confirm.setSize(150,150);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                confirm.setLocation(dim.width/2-confirm.getSize().width/2, dim.height/2-confirm.getSize().height/2);
                confirm.setVisible(true);
            }
        });
        removePlayer.setEnabled(false);

        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu ask = new JPopupMenu();
                JPanel mainPop = new JPanel(new BorderLayout());

                mainPop.add(new JLabel("Combien de joueur voulez vous rajouter ?"),BorderLayout.NORTH);
                ButtonGroup choix = new ButtonGroup();
                JRadioButton nb1 = new JRadioButton("1 Joueur");
                choix.add(nb1);

                JRadioButton nb2 = new JRadioButton("2 Joueurs");
                choix.add(nb2);
                if (player3.isVisible()) {
                    nb2.setEnabled(false);
                }
                mainPop.add(nb1,BorderLayout.WEST);
                mainPop.add(nb2,BorderLayout.EAST);

                JButton valider = new JButton("Valider");
                valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (nb1.isSelected()) {
                            if (!player3.isVisible()){
                                player3.setVisible(true);
                            } else if (!player4.isVisible()) {
                                player4.setVisible(true);
                                addPlayer.setEnabled(false);
                            }
                        } else if (nb2.isSelected()) {
                            if (!player3.isVisible() && !player4.isVisible()) {
                                player3.setVisible(true);
                                player4.setVisible(true);
                                addPlayer.setEnabled(false);
                            }
                        }
                        removePlayer.setEnabled(true);
                        ask.setVisible(false);
                    }
                });
                mainPop.add(valider,BorderLayout.SOUTH);

                ask.add(mainPop);
                ask.setSize(150,150);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                ask.setLocation(dim.width/2-ask.getSize().width/2, dim.height/2-ask.getSize().height/2);
                ask.setVisible(true);

            }
        });
        footer.add(addPlayer);
        footer.add(removePlayer);

        JButton validate = new JButton("Valider");
        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.joueurs = new HashMap<>();
                m.type = TypesMessage.DEMARRER_PARTIE;

                m.joueurs.put(pName1.getText(), unknown1.isSelected()?-1:Integer.valueOf(pDay1.getText()));
                m.joueurs.put(pName2.getText(), unknown2.isSelected()?-1:Integer.valueOf(pDay2.getText()));

                if (player3.isVisible() && !player4.isVisible()) {
                    m.joueurs.put(pName3.getText(),unknown3.isSelected()?-1: Integer.valueOf(pDay3.getText()));
                } else if (player3.isVisible() && player4.isVisible()) {
                    m.joueurs.put(pName3.getText(),unknown3.isSelected()?-1: Integer.valueOf(pDay3.getText()));
                    m.joueurs.put(pName4.getText(),unknown4.isSelected()?-1: Integer.valueOf(pDay4.getText()));
                }
                window.setVisible(false);
                notifyObservateur(m);
            }
        });
        footer.add(validate);



        main.add(footer,BorderLayout.SOUTH);
        window.add(main);
    }

    public void setVisible(boolean b) {
        window.setSize(500,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setVisible(b);
    }

}
