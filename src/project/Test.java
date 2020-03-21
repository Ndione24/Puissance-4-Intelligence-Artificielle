package project;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		System.out.println("*******************  Four in a row #ndione  ****************");
		Grille.initialiserGrille();
		Grille.afficherGrille();
		System.out.println("Tour du Joueur " + Joueur.playerToChar(Joueur.getNumJoueur()));

		Scanner sc = new Scanner(System.in);

		int i = 0;
		int coup;
		while ((coup = sc.nextInt()) != 9) {
			int ligneJouee = Joueur.jouerCoup(coup);
			Grille.afficherGrille();
			System.out.println("ligne jouée "+ligneJouee);
			if (Grille.checkWinner(ligneJouee, (coup-1), Joueur.getNumJoueur()) == 7) {
				System.out.println("********** gagnant ************" + Joueur.getNumJoueur());
				System.exit(0);
			}

			Joueur.setNumJoueur(Joueur.joueurSuivant());
			System.out.println("Tour du joueur " + Joueur.playerToChar(Joueur.getNumJoueur()));

			i++;

		}

		
	}

}