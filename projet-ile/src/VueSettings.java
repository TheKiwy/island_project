import java.util.HashMap;
import java.util.Scanner;

public class VueSettings extends Observe {

    public void settinggame() {
            // Console view
        // Declaration
        Scanner entre = new Scanner(System.in);
        Scanner entre2 = new Scanner(System.in);
        Message m = new Message();
        boolean bool = false;
        int nbP;

        // Player number
        do {
            String recup;
            System.out.print("Veuillez indiquer le nombre de joueurs, de 2 à 4 joueurs : ");
            nbP = entre.nextInt();
            // Input Error
            while (nbP < 2 || nbP > 4){
                System.out.println("Le nombre sélectionné n'est pas compris entre 2 et 4 joueur, veuillez réessayer.");
                System.out.print("Veuillez indiquer le nombre de joueurs, de 2 à 4 joueurs : ");
                nbP = entre.nextInt();
            }

            // Confirmation
            System.out.println("Vous avez renseigné " + nbP + " joueurs, êtes-vous sûr(e) ? (oui/non) : ");
        } while (!entre2.nextLine().equals("oui"));

        // Indication for the player
        System.out.println();

        // Player name
        m.joueurs = new HashMap<>();

        for (int i = 0; i <= nbP-1; i++) {
            String nom;
            Integer lastVisite;
            do {
                System.out.println("Veuillez indiquer le nom du joueur " + (i+1) + " : ");
                nom = entre2.nextLine();
                System.out.println("Combien de temps s'est écoulé depuis la dernière fois que vous avez visité une île ? (jours) : ");
                lastVisite = entre.nextInt();
                System.out.println("Vous avez renseigné le nom " + nom + " avec dernière visite d'une île il y a " + lastVisite + " jours, êtes-vous sûr(e) ? (oui/non) : ");
            } while (!entre2.nextLine().equals("oui"));
            m.joueurs.put(nom, lastVisite);
        }
        System.out.println("Les noms et les dates de visite des joueurs ont été enregistrés.");
        System.out.println();

        // Player Settings End --> Start game confirmation
        do {
            System.out.println("Le paramétrage des joueurs est fini, voulez-vous commencer la partie ? (oui/non) : ");
            if (entre2.nextLine().equals("non")) {
                System.out.println("Vous avez tapé 'non', êtes vous sûr de vous ? (oui/non) (Si vous tapez oui, la partie ne démmarrera pas) : ");
                bool = entre2.nextLine().equals("oui") ? false : true;
            } else {
                m.type = TypesMessage.DEMARRER_PARTIE;
                bool = false;
            }
        } while (bool);

        notifyObservateur(m);
    }

}
