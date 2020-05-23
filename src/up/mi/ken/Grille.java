package up.mi.ken;

import java.util.ArrayList;


public class Grille {
	private static int[][] grille = new int[6][7];

	/**
	 * Constructeur
	 */
	public Grille() {

	}

	/**
	 * met tout à zero
	 */
	public static void initialiserGrille() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				grille[i][j] = 0;
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
	public static int[][] getGrille() {
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
				if(grille[i][j]==0) {
					System.out.print(" " + " " + " " + "|");
				}
				else if(grille[i][j]==1) {
					System.out.print(" " + "O" + " " + "|");
				}else {
					System.out.print(" " + "X" + " " + "|");
				}
			}
			System.out.print("\n");
		}
		System.out.print("+---+---+---+---+---+---+---+\n");
		afficherNumColonnes();
		System.out.println("\n\n");
	}
	/**
	 * place le pion du joueur à une case précise de la grille
	 * @param ligne la ligne
	 * @param col la colonne
	 * @param numJoueur le pion correspondant numéro du joueur
	 */
	public static void setGrille(int ligne, int col, int numJoueur) {
		grille[ligne][col]=numJoueur;
	}

	/**
	 * Méthode nous disant si la case est pleine ou pas
	 * 
	 * @param numColonne le numero de la colonne
	 * @return un boolean indiquant si on peut continuer à jour ou pas
	 */
	public static boolean coupPossible(int numColonne) {
		boolean b = false;
		if (!estColonnePleine(numColonne)) {
			b = true;
		}

		return b;
	}

	/**
	 * permet de savoir si une colonne est pleine ou pas
	 * 
	 * @param col le numéro de la colonne
	 * @return vraie su la colonne est pleine
	 */
	public static boolean estColonnePleine(int col) {
		return grille[0][col] != 0;
	}


	/**
	 * vérifie si la grille est pleine
	 */
	public static boolean estGrillePleine() 
	{
		boolean b = true;
		int compteur =0;
		for (int i = 0; i < 7; i++) 
		{
			if (grille[0][i] != 0) 
			{
			    ++compteur;
			}
		}
		if(compteur<6) 
			b=false;
            return b;
	}
	/**
	 * 
	 * @param ligne la ligne dans la grille
	 * @param col la colonne dans la grille
	 * @return vrai si la colonne de cette ligne de la grille est vide, faux sinon
	 */
	public static boolean estCaseVide(int ligne, int col) {
		return grille[ligne][col]==0;
	}
	/**
	 * cherche les emplacements jouables dans la grille 
	 * @return la liste des cases jouables dans la grille
	 */
	
	public static ArrayList<Case> casesVides()
	{
		ArrayList<Case> casesDispo = new ArrayList<Case>();
		boolean trouve;
		for(int i=5;i>=0;i--)
		{
			trouve=false;
			for(int j=0;j<7 && !trouve;j++)
			{
				if(grille[i][j]==0) 
				{
					casesDispo.add(new Case(i,j));
					trouve=true;
				}
			}
		} return casesDispo;
	}
	/**
	 * Permet de retourner l'index du coup 
	 * @param coupsDispo
	 * @param indexCoup
	 * @return l'index du coup possible parmi la liste des coups possible dans la grille
	 */
	public static Case getCoup(ArrayList<Case>coupsDispo,int indexCoup) {
		return coupsDispo.get(indexCoup);
	}

	
	
	public static Case obtenirUnCoup(int joueur1,int joueur2,int profondeur) {
		  double alpha=Double.NEGATIVE_INFINITY;
		  double beta=Double.POSITIVE_INFINITY;
		
	    ArrayList<Case> coupsPossibles=casesVides();

		Case coup=getCoup(coupsPossibles,0);
		
		Joueur.jouerCoup(coup, joueur1);
		int bestScore=scoreDunCoup(coup, joueur1,profondeur,alpha,beta);
		Joueur.annulerUnCoup(coup);
	
		for(int i=1;i<coupsPossibles.size();i++) {
			
			Joueur.jouerCoup(getCoup(coupsPossibles,i), joueur1);
			int score=scoreDunCoup(getCoup(coupsPossibles,i), joueur1,profondeur,alpha,beta);
			Joueur.annulerUnCoup(getCoup(coupsPossibles,i));
			
		   if(score==bestScore) {
				int tirage=(int)(Math.random()*4)+1;
				if(tirage==3 || tirage==2)
				  coup=getCoup(coupsPossibles,i);
				
			}
		   else {
			       if(joueur1==2) {
					 if(score>bestScore) {
						bestScore=score;
						coup=getCoup(coupsPossibles,i);
					 }
			       }
			       else {
			    	     if(score<bestScore) {
							bestScore=score;
							coup=getCoup(coupsPossibles,i);
						 }
			       }
		   }
			
		}
		return coup;
	}
	
	
	/**
	 * calcul le score qu'un joueur obtient en jouent à un emplacement donné
	 * @param coup
	 * @param joueur
	 * @param profondeur
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public static int scoreDunCoup(Case coup,int joueur,int profondeur,double alpha,double beta) 
	{

	    int joueur2=1;
	    if(joueur==1) joueur2=2;
	 
	    if(estGrillePleine() || profondeur==0 || Joueur.estCoupGagnant(joueur))
	    {
	    
	    	return fonctionEvaluation(2,1);
	    }
	    else 
	    {
	    
	    	  return minMaxAlphaBeta(joueur2,profondeur-1,alpha,beta);
	     }
    }
	
	/**
	 *definition de l'heuristique du jeu
	 * @param nbPions le nombre pions alignés
	 * @return le poids associé à la feuille
	 */
	public static int heuristique(int nbPions) {
		  int score=0;
	      if(nbPions>4) 
	      { nbPions=4;
	      }
	      
		  switch(nbPions){
		  case 0: 
			     score=0;
			     break;
		  case 1: 
			     score=1;
			     break;
		  case 2:
			     score=50;
			     break;
		  case 3:
			     score=150;
			     break;
		  case 4:
			     score=5000;
			     break;
		  default: 
			       break;
		  }
		  
		return score;
	}
	
	 /**
     *<i>La fonction d'évaluation du jeu</i>
     * 
     * @param plateau
     *      <i>L'état du jeu</i>
     *      
     * @param joueur
     *      <i>Idendifiant joueur 1</i>
     *      
     * @param joueur2
     *      <i>Identifiant joueur 2</i>
     *      
     * @return
     *     <i>La fonction d'évéluation</i>
     */
    public static int fonctionEvaluation(int joueur,int joueur2) {
  
    	int j1=score(joueur);
    	int j2=score(joueur2);
    	if(j1>5000)
    		j1=5000;
    	if(j2>5000)
    		j2=5000;

    	return (j1-j2);
    }
	
	
	/**
	 *  calcule le score total d'un joueur
	 * 
	 *       
	 * @param joueur L'identifiant du numJoueur
	 *      
	 * @return
	 *    Le score total du joueur
	 */
	public static int calculScoreDunJoueur(int numJoueur) {
		 
		 
		  int score=0;
		  
		  for(int i=0;i<6;i++) {
			  for(int j=0;j<7;j++) {
				  if(grille[i][j]==numJoueur) {
					  
				    Case c=new Case(i,j);
				    score+=heuristique(verticalWinCheck(c, numJoueur))+
				    	   heuristique(horizontalWinCheck(c, numJoueur))+
				    	   heuristique(righDiagonalWinCheck(c, numJoueur))+
				    	   heuristique(leftDiagonalWinCheck(c, numJoueur));
				        
				   }
				  
			  }
		  }
		           
		 return score;
	}
	
	/**
	 * 
	 * @param cas la case de la grille
	 * @param joueur le joueur
	 * @return le nbre de pions alignés verticalement
	 */
	public static int verticalWinCheck(Case cas,int joueur) {
		boolean trouve=false;
		int nbPions=0;
		
		if(grille[cas.getLigne()][cas.getColonne()]==joueur) 
		     nbPions=1;  
		 if(cas.getLigne()>0) {
			 for(int i=cas.getLigne();i>0 & !trouve;i--) {
		    	  if(grille[i-1][cas.getColonne()]==joueur) 
		    		   nbPions++;
		    	   else trouve=true;
		    	}
		 }
		 
		 return nbPions;	
	}
	/**
	 * 
	 * @param cas la case de la grille
	 * @param joueur le joueur
	 * @return le nombre de pions aligné horizontalement
	 */
	
	public static int horizontalWinCheck(Case cas,int joueur) {
		boolean trouve=false;
		int nbPions=0;
		if(grille[cas.getLigne()][cas.getColonne()]==joueur) 
			 ++nbPions;
		if(cas.getColonne()<6) {
		 	for(int i=cas.getColonne();i<6 & !trouve;i++) {
		 		
		    	if(grille[cas.getLigne()][i+1]==joueur) 
		    		 nbPions++;
		    	else trouve=true; 
		    }
		}
		
	  return nbPions; 
	}
	/**
	 * 
	 * @param cas la case de la grille
	 * @param joueur le joueur courant
	 * @return le nbre de pions aligné en diagonale droite
	 */
public static int righDiagonalWinCheck(Case cas,int joueur) {
		
		boolean trouve=false;
		int nbPions=0; 
		
		if(grille[cas.getLigne()][cas.getColonne()]==joueur)
			++nbPions;
		if(cas.getLigne()>0 & cas.getColonne()<6) {
			  int y=1;
			  
			  for(int i=cas.getLigne();i>0 & !trouve;i--) {
				  
			    	if(cas.getColonne()+y<7) {
				      if(grille[i-1][cas.getColonne()+y]==joueur) 
				    	  nbPions++;
				       else trouve=true;
				      y++;
			    	}
			  }
		  }
		return nbPions;
	}

/**
 * 
 * @param cas la case de la grille
 * @param joueur le joueur courant
 * @return le nbre de pions aligné en diagonale gauche
 */
public static int leftDiagonalWinCheck(Case cas,int joueur) {
	
	boolean trouve=false;
	int nbPions=0; 

	
	if(grille[cas.getLigne()][cas.getColonne()]==joueur)
		nbPions=1;
	if(cas.getColonne()>0 & cas.getLigne()>0) {
		  int j=1;
		  
		  for(int i=cas.getColonne();i>0 & !trouve;i--) {
		    	if(cas.getLigne()-j>=0) {
		    		
			      if(grille[cas.getLigne()-j][i-1]==joueur) 
			    	  nbPions++;
			       else trouve=true;
			      j++;
		    	}
		  }
	  }
	return nbPions;
}
	
/**
 *  Calcule le score d'un joueur (aide pour minimax)
 * @param joueur le joueur
 * @return
 */
private static int score(int joueur) {
	  
	  int score=0;
	  
	  for(int i=0;i<6;i++) {
		  for(int j=0;j<7;j++) {
			  if(grille[i][j]==joueur) {
				  
			    Case cas=new Case(i,j);
			    score+=heuristique(verticalWinCheck( cas, joueur))+
			    	   heuristique(horizontalWinCheck(cas, joueur))+
			    	   heuristique(righDiagonalWinCheck(cas, joueur))+
			    	   heuristique(leftDiagonalWinCheck(cas, joueur));
			        
			   }
			  
		  }
	  }
	           
	 return score;
}


/**
 * implémentation de l'algorithme minimax
 * @param joueur le joueur
 * @param profondeur la profondeur
 * @param alpha
 * @param beta
 * @return
 */
	public static int minMaxAlphaBeta(int joueur, int profondeur,double alpha,double beta) {
    ArrayList<Case> coupsPossibles=casesVides();
    
	Joueur.jouerCoup(getCoup(coupsPossibles, 0), joueur);
    int resultat=scoreDunCoup(getCoup(coupsPossibles, 0), joueur, profondeur,alpha,beta);
    Joueur.annulerUnCoup(getCoup(coupsPossibles, 0));
    
    for(int i=1;i<coupsPossibles.size();i++) {
    	
    	Joueur.jouerCoup(getCoup(coupsPossibles, i), joueur);
    	int score=scoreDunCoup(getCoup(coupsPossibles, i), joueur, profondeur,alpha,beta);
    	Joueur.annulerUnCoup(getCoup(coupsPossibles, i));
    	
    	if(joueur==2) {
    	   resultat=Math.max(resultat,score);
    	   if(resultat>alpha)
    		   alpha=resultat;
    	    if(alpha>=beta)
    		      return resultat;
    	 }
    	 else {
    		    resultat=Math.min(resultat,score);
    		    if(resultat<beta)
		    		   beta=resultat;
		    	 if(beta<=alpha)
		    		  return resultat;   
    	 }	      
    }
    
return resultat;
}

}
