
import java.util.ArrayList;

public class Messager extends Aventurier {

	Messager(Tuile tuile) {
		super();
		this.setPion(Utils.Pion.VIOLET);
		setPosition(tuile);
	}


	// dans contoleur faire un getRole() si c messager alors receveursPossibles = tous sauf joueur courant

	public String getRole() {
		return "Messager";
	}

}