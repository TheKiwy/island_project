import java.util.HashMap;

public class Message {

    // Attributs
    public TypesMessage type;

    // Player Settings
    public HashMap<String, Integer> joueurs;

    // Player Actions
    public String joueur;

    // Player deplacement
    public Tuile tuile;
    public Aventurier joueurCourant;
}
