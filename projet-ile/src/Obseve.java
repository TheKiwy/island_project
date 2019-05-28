public class Obseve {

    // Attributs
    private Observateur observateur;

    // Methode
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }

    public void notifyObservateur(Message m) {
        if (observateur != null) {
            this.observateur.traiterMessage(m);
        }
    }
}
