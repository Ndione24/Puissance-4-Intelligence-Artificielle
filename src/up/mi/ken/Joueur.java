package up.mi.ken;

public class Joueur {
	private static int numJoueur = 1;

	public Joueur() {

	}

	public static int getNumJoueur() {
		return numJoueur;
	}

	public static void setNumJoueur(int numJoueur) {
		Joueur.numJoueur = numJoueur;
	}
/**
 * place le pion du joueur courant dans une case donnée dans la grille
 * @param c la case dans laquelle on veut jouer
 * @param numJoueur le pion correspondant au numéro du joueur
 */
	public static void jouerCoup(Case c, int numJoueur) {
	Grille.setGrille(c.getLigne(), c.getColonne(), numJoueur);
	}

	/**
	 * 
	 * @return le numéro du joueur suivant
	 */
	public static int joueurSuivant() {
		if (Joueur.getNumJoueur() == 1)
			return 2;
		else
			return 1;
	}
	/**
	 * annule le coup du joueur à cet emplacement de la grille
	 * @param c la case de la grille
	 */
	public static void annulerUnCoup(Case c) {
		Grille.setGrille(c.getLigne(), c.getColonne(),0);
	}
	
	
	/**
	 *  retourne vrai si le joueur remporte la partie
	 * @param joueur
	 * @return
	 */
	public static boolean estCoupGagnant(int joueur) {
		
		   int lignes=0;
		  
			  for(int i=0;i<6;i++) {
				  for(int y=0;y<7;y++) {
					  Case cas=new Case(i,y);
					  int max=Math.max(Math.max(Grille.verticalWinCheck( cas, joueur),Grille.horizontalWinCheck(cas, joueur)), 
					         Math.max(Grille.leftDiagonalWinCheck(cas, joueur),Grille.righDiagonalWinCheck(cas, joueur)));
					  lignes=(lignes>max?lignes:max);
				  }
			  }
		 
		       if(Grille.heuristique(lignes)==5000) 
		       {
		    	   return true;
		       }
		       else return false;
		
	}
	/**
	 * redefinition de la méthode jouerUnCoup
	 * @param colonne
	 * @return
	 */
	public static void jouerCoup2(int colonne,int numJoueur) 
	{
		
		if(colonne>7) 
		{
			System.out.println("pas possible de jour à la colonne "+colonne);
		}
		int a=rechercheLigne(colonne);
		Grille.setGrille(a, colonne-1,numJoueur);
		
		
	}
	
	public static int rechercheLigne(int col) {
		int i=5;
		while(i>=0) 
		{
			if(Grille.getGrille()[i][col]==0) 
			{
				return i;
			}
			else i--;
		}
		return i;
	}
}