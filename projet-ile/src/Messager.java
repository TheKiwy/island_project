
import java.util.ArrayList;

public class Messager extends Aventurier {
	Messager(String nom, Tuile position, Utils.Pion pion) {
		super(nom, position, pion);
	}


	// dans contoleur faire un getRole() si c messager alors receveursPossibles = tous sauf joueur courant

	public String getRole() {
		return "Messager";
	}

}