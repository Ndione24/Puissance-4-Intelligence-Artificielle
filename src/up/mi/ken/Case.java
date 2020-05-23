package up.mi.ken;

public class Case {
	private int ligne;
	private int colonne;

	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public void afficheInfosCase() {
		System.out.println("Ligne : " + getLigne());
		System.out.println("Colonne : " + getColonne());
	}
}
