
import java.util.ArrayList;

public class Messager extends Aventurier {

	Messager() {
		super();
		super.setPion(Utils.Pion.VIOLET);
	}


	// dans contoleur faire un getRole() si c messager alors receveursPossibles = tous sauf joueur courant

	public String getRole() {
		return "Messager";
	}

}