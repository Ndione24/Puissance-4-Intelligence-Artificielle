
//enlever les static et revoir le jeu


package project;
public class Joueur {
	
	//??? static ???
	private static int numJoueur=1;

	private int nbFoisJouer;
	
	public Joueur(){
		
		this.nbFoisJouer=0;
	}

	public static int getNumJoueur() {
		return numJoueur;
	}

	public static void setNumJoueur(int numJoueur) {
		Joueur.numJoueur = numJoueur;
	}

	/**
	 * 
	 * @param numColonne
	 * @return
	 */
	public static int jouerCoup(int numColonne) 
	{
		
		if((numColonne)>7) 
		{
			System.out.println("pas possible de jour à la colonne "+numColonne);
			
		}
		
		int i=5;
											 //numColonne ou numColonne -1
		while(i>=0 && !Grille.estColonnePleine(numColonne)) 
		{
			if(Grille.getGrille()[i][numColonne-1]=='0') 
			{
				Grille.getGrille()[i][numColonne-1]=playerToChar(Joueur.getNumJoueur());
				return i;
			}
			else i--;
		}
		
		return i;
	}
		
	public static char playerToChar(int player) 
	{
		if(player==1) 
		{
			return 'O';
		}
		//player==2 ?
		else 
		{
			return 'X';
		}
		
		
	}
	/**
	 * 
	 * @param player le numéro couraant du joueur
	 * @return le numéro du joueur suivant
	 */
	public static int joueurSuivant() {
		if (Joueur.getNumJoueur() == 1)
			return 2;
		else
			return 1;
	}
}