import java.util.ArrayList;

public class Pilote extends Aventurier {

    Pilote(Tuile tuile) {
        super();
        this.setPion(Utils.Pion.BLEU);
        setPosition(tuile);
    }

    public ArrayList<Tuile> getDeplacementSpecial(Grille g) {
        ArrayList<Tuile> deplacement = new ArrayList<>();
        deplacement.addAll(g.getTuiles());
        deplacement.remove(g.getTuiles().indexOf(super.getPosition()));
        return deplacement;
    }

    public String getRole() {
            return "Pilote";
    }

}