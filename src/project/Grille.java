package project;

public class Grille {
	private static char[][] grille = new char[6][7];

	/**
	 * Constructeur qui initialise la ligne et la colonne à 7
	 */
	public Grille() {

	}

	/**
	 * met tout à zero
	 */
	public static void initialiserGrille() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				grille[i][j] = '0';
	}

	private static void afficherNumColonnes() {

		for (int i = 1; i <= 7; i++) {
			System.out.print("  " + i + " ");
		}
	}

	/**
	 * méthode retournant une réf de cette grille
	 * 
	 * @return la grille
	 */
	public static char[][] getGrille() {
		return grille;
	}

	/**
	 * cette méthode affiche l'etat courant de la grille
	 */
	public static void afficherGrille() {
		afficherNumColonnes();
		System.out.println();

		for (int i = 0; i < 6; i++) {
			System.out.print("+---+---+---+---+---+---+---+\n");
			System.out.print("|");

			for (int j = 0; j < 7; j++) {
				if (grille[i][j] == '0') {
					System.out.print("   " + "|");
				} else {
					System.out.print(" " + grille[i][j] + " " + "|");
				}
			}
			System.out.print("\n");
		}
		System.out.print("+---+---+---+---+---+---+---+\n");
		afficherNumColonnes();
		System.out.println("\n\n");
	}





	/**
	 * permet de savoir si une colonne est pleine ou pas
	 * 
	 * @param col le numéro de la colonne
	 * @return vraie si la colonne est pleine
	 */
	public static boolean estColonnePleine(int col) {
		return grille[0][col - 1] != '0';
	}

	
	/**
	 * place le pion du joueur dans la colonne donnée
	 * 
	 * @param col    le numéro de la colonne
	 * @param player le numéro du joueur
	 */
	public static void playColumn(int col, int player) {
		Joueur.setNumJoueur(player);
		Joueur.jouerCoup(col);
	}
	
	public static boolean partyNull() 
	{
		int i;
		for(i=0;i<7;i++) 
		{
			if(grille[0][i]=='0') 
			{
				continue;
			}
		}
		
		return (i==6)?true:false;
	}

	public static boolean isAlignVertical(int ligne, int column, char bonHommme) {
		int inc = 0;

		for (ligne = 0; ligne < 6; ligne++) {
			if (grille[ligne][column] == bonHommme) {
				inc++;

				if(inc==4) 
				{
					return true;
				}
			} else {
				inc = 0;
			}
		}
		return false;

	}

	public static boolean isAlignHorizontal(int ligne, char bonHommme) {
		int inc = 0;

		for (int i = 0; i < 7; i++) {
			if (grille[ligne][i] == bonHommme) {
				inc++;
				
				
				if(inc==4) 
				{
					return true;
				}
			} else {
				inc = 0;
			}
		}
		
		return false;

	}
	

	/*
	public static boolean isAlignDiagonalDroit(int ligne, char bonHommme) {
		int inc = 0;

		for (int i = 0; i < 6; i++) {
			if (grille[ligne][i] == bonHommme) {
				inc++;
			} else {
				inc = 0;
			}
		}
		return (inc == 4) ? true : false;

	}
*/
	
	
	/**
	 * 
	 * @param ligneInit 5
	 * @param maxLigne
	 * @param colInit
	 * @param bonHomme
	 * @return
	 */
	
	public static boolean checkAlignDiaDroite(int ligneInit, int minLigne, int colInit, char bonHomme) {
		int ligne;
		int column;
		int inc = 0;

		if (colInit == 6) {
			minLigne = 0;
			
		}

		for (ligne = ligneInit, column = colInit; ligne >= minLigne && column >= 0; ligne--, column--) {

			if (grille[ligne][column] == bonHomme) {
				inc++;
				System.out.println("inc "+inc);
				if(inc==4) 
				{
					return true;
				}
			} else {
				inc = 0;
			}
		}

		return false;
	}
	
	
	
	/**
	 * 
	 * @param ligne
	 * @param bonHomme
	 * @return
	 */
	public static boolean isAlignDiagonalDroite(int ligne, char bonHomme) {
		ligne = 5;
		int column;

		for (column = 3; column < 7; column++) {
			
			
			if (checkAlignDiaDroite(ligne, 5-column, column, bonHomme)) {
				return true;
			}
		}
		
		ligne=4; column=6;
		for(ligne=4;ligne>2;ligne--) 
		{
			if (checkAlignDiaDroite(ligne, 0, column, bonHomme)) {
				return true;
			}
		}
		

		return false;

	}
	
	
	/**
	 * 
	 * @param ligneInit
	 * @param maxLigne
	 * @param colInit
	 * @param bonHomme
	 * @return
	 */
	public static boolean checkAlignDiaGau(int ligneInit, int maxLigne, int colInit, char bonHomme) {
		int ligne;
		int column;
		int inc = 0;

		if (colInit == 6) {
			maxLigne = 5;
			
		}

		for (ligne = ligneInit, column = colInit; ligne <= maxLigne && column >= 0; ligne++, column--) {

			if (grille[ligne][column] == bonHomme) {
				inc++;

				if(inc==4) 
				{
					return true;
				}
			} else {
				inc = 0;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param ligne
	 * @param bonHomme
	 * @return
	 */
	public static boolean isAlignDiagonalGauche(int ligne, char bonHomme) {
		ligne = 0;
		int column;

		for (column = 3; column < 7; column++) {
			
			
			if (checkAlignDiaGau(ligne, column, column, bonHomme)) {
				return true;
			}
		}
		
		ligne=1; column=6;
		for(ligne=1;ligne<3;ligne++) 
		{
			if (checkAlignDiaGau(ligne, column, column, bonHomme)) {
				return true;
			}
		}
		

		return false;

	}
	

	/**
	 * verifie s'il ya un alignement diagonal montant de quatre pion d'un joueur
	 * 
	 * @return
	 */

	public static boolean diagonalGAlign(int ligne, int column, char bonHommeJoueur) {

		if ((column < 3) || (ligne > 3)) {

			return false;
		}
		for (int i = 0; i < 4; i++, ligne++, column--) {
			if (grille[ligne][column] != bonHommeJoueur) {

				System.out.println("in if " + grille[ligne][column]);
				System.out.println("column " + column + "ligne " + ligne);

				return false;
			}
		}
		return true;

	}

	/**
	 * verifie s'il ya un alignement diagonal descendant de quatre pion d'un joueur
	 * 
	 * @return
	 */
	public static boolean diagonalDAlign(int ligne, int column, char bonHommeJoueur) {
		if ((column > 3) || (ligne > 2)) {
			return false;
		}
		for (int i = 0; i < 4; i++, ligne++, column++) {
			if (grille[ligne][column] != bonHommeJoueur) {
				return false;
			}
		}
		return true;

	}

	/**
	 * verifie s'il ya un alignement vertical de quatre pion d'un joueur
	 * 
	 * @return
	 */
	public static boolean vAlign(int ligne, int column, char bonHommeJoueur) {
		if (ligne > 2) {
			return false;
		}

		for (int i = 0; i < 4; i++, ligne++) {
			if (grille[ligne][column] != bonHommeJoueur) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Permet de chercher 4 pions alignés dans la grille
	 * 
	 * @return vrai si on a 4 pions de même
	 * 
	 * 
	 */
	public static boolean hAlign(int ligne, int column, char bonHommeJoueur) {

		if (column > 4) {
			return false;
		}
		for (int i = 0; i < 4; i++, column++) {
			if (grille[ligne][column] != bonHommeJoueur) {
				return false;
			}
		}

		return true;

	}

	/**
	 * return le numero du joueur gagnant
	 *
	 * @param numJoueur
	 * @return 0 si il n'y a pas de joueur gagnant. Return le numero du joueur
	 *         gagnant au cas contraire
	 */

	public static int checkWinner(int ligne, int column, int numJoueur) {
		char bonhommeJoueur = Joueur.playerToChar(numJoueur);
		/*if (vAlign(ligne, column, bonhommeJoueur) || hAlign(ligne, column, bonhommeJoueur)
				|| diagonalDAlign(ligne, column, bonhommeJoueur) || diagonalGAlign(ligne, column, bonhommeJoueur)) {
			return 7;
		} */
		
		if(isAlignDiagonalGauche(ligne, bonhommeJoueur) || isAlignHorizontal(ligne, bonhommeJoueur) || isAlignVertical(ligne, column, bonhommeJoueur) || isAlignDiagonalDroite(ligne, bonhommeJoueur)) 
		{
			return 7;
		}
		return 0;

	}

}
