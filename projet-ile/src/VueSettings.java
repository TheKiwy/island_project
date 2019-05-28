import java.util.HashMap;
import java.util.Scanner;

public class VueSettings extends Obseve{

    public void settinggame() {
            // Console view
        System.out.println("»» ————— ★★　Bienvenu sur l'Ile interdite ★★　————— ««");
        System.out.println();
        // Declaration
        Scanner entre = new Scanner(System.in);
        Scanner entre2 = new Scanner(System.in);
        Message m = new Message();
        boolean bool = false;

        // Player number
          do {
            String recup;
            System.out.print("Veuillez indiquer le nombre joueur, de 2 à 4 joueur:");
            m.nbP = entre.nextInt();
            // Input Error
            while (m.nbP < 2 || m.nbP >4){
                System.out.println("Le nombre sélectionner n'est pas compris entre 2 et 4 joueur, vueillez réssayer.");
                System.out.print("Veuillez indiquer le nombre joueur, de 2 à 4 joueur:");
                m.nbP = entre.nextInt();
            }

            // Confirmation
            System.out.println("Vous avez renseigné " + m.nbP + " joueurs, êtes vou sûr ? oui/non");
        } while (entre2.nextLine().equals("non"));

        // Indication for the player
        System.out.println("Le nombre de joueur (" + m.nbP + ") a été enregistré.");
        System.out.println();

        // Player name
        m.joueurs = new HashMap<String, String>();
        for (int i = 0; i <= m.nbP-1; i++) {
            String nom;
            String couleur;
            do {
                System.out.println("Veuillez indiquer le nom du joueur " + (i+1) + ":");
                nom = entre2.nextLine();
                System.out.println("Veuillez indiquer un couleur de pion:");
                couleur = entre2.nextLine();
                System.out.println("Vous avez renseigné " + nom +" avec pour couleur "+ couleur +" êtes vou sûr ? oui/non");
            } while (entre2.nextLine().equals("non"));
            m.joueurs.put(nom,couleur);
        }
        System.out.println("Les noms et les couleurs des joueurs ont été enregistré.");
        System.out.println();

        // Player Settings End --> Start game confirmation
        do {
            System.out.println("Le paramétrage des joueurs est fini, voulez vous commencer la partie ? oui/non");
            if (entre2.nextLine().equals("non")) {
                System.out.println("Vous avez tapé 'non', êtes vous sûr de vous ? oui/non (Si vous taper oui, la partie ne démmarreras pas)");
                bool = entre2.nextLine().equals("oui") ? false : true;
            } else {
                m.type = TypesMessage.DEMARRER_PARTIE;
                bool = false;
            }
        } while (bool);

        notifyObservateur(m);
    }

}
