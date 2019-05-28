import java.util.*;

public class Tuile {

	private Role role;
	private ArrayList<Aventurier> listeAventuriers;
	private EtatTuile etat;
	private String nom;
	private int coordX;
	private int coordY;

	public Tuile(Role role, String nom){
		this.etat = EtatTuile.surface;
		this.role = role;
		this.nom = nom;

	}

	public Role getRole() {
		return this.role;
	}

	public String getNom() {
		return this.nom;
	}

	public int getCordX() {
		return this.coordX;
	}

	public int getCordY() {
		return this.coordY;
	}

	public void inonder() {
		this.etat = (this.etat == Utils.EtatTuile.ASSECHEE)?Utils.EtatTuile.INONDEE:Utils.EtatTuile.COULEE; // Si la case est asséchée elle devient inondée, autrement elle devient coulée
	}

	public void assecher() {
		if (this.etat == Utils.EtatTuile.INONDEE) {
			this.etat = Utils.EtatTuile.ASSECHEE; // Si la case est inondée, elle devient asséchée.
		}
	}

	public ArrayList<Aventurier> getListeAventuriers() {
		return listeAventuriers;
	}

	public void addAventurier(Aventurier a) {
		this.listeAventuriers.add(a);
	}

	public void delAventurier(Aventurier a) {
		this.listeAventuriers.remove(a);
	}

	public ArrayList<Aventurier> getJoueurs() {
		return this.listeAventuriers;
	}

	public Utils.EtatTuile getEtat() {
		return this.etat;
	}
}

}