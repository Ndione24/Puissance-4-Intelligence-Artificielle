package project;
/*
 * . La "qualité" d'une position pour le joueur X est évaluée en soustrayant le score de 
 * l'adversaire du score du joueur X pour cette position. Chaque mouvement est représenté par un
 * entier 
 * (c.-à-d. Que le premier mouvement est effectué en saisissant 1, le deuxième en entrée 2, etc.).
 */
	
	/**
	 * Je comprends que miniMax devrait être implémenté en utilisant la récursivité. Pour le moment j'ai:

Un evaluate() méthode, qui prend comme paramètres un objet représentant l’état du tableau
 (objet "BoardState" et un booléen appelé "max" (la signature serait évaluer 
 (BoardState myBoard, boolean max)).

max est vrai quand c’est le tour du joueur X. Avec une position de tableau, il évaluera tous
 les coups possibles et retournera ce qui est le plus bénéfique pour le joueur X. Si c’est le tour
  de l’adversaire, max sera faux et la méthode retournera le coup le moins bénéfique pour le joueur X 
  ( ie: plus bénéfique pour le joueur y)

Cependant, j’ai des difficultés à écrire le texte miniMax méthode. Ma structure générale serait 
quelque chose comme:
	 */
import java.io.*; 

public class Minimax {
	
	// A simple java program to find maximum score that 
	// maximizing player can get. 

	
	
		

	// Returns the optimal value a maximizer can obtain. 
	// depth is current depth in game tree. 
	// nodeIndex is index of current node in scores[]. 
	// isMax is true if current move is of maximizer, else false 
	// scores[] stores leaves of Game tree. 
	// h is maximum height of Game tree 
	static int minimax(int depth, int nodeIndex, boolean isMax, 
				int scores[], int h) 
	{ 
		// Terminating condition. i.e leaf node is reached 
		//scores[0] sera le maximiser
		if (depth == h) 
			return scores[nodeIndex]; 

		// If current move is maximizer, find the maximum attainable 
		// value 
		if (isMax) 
		return Math.max(minimax(depth+1, nodeIndex*2, false, scores, h), 
				minimax(depth+1, nodeIndex*2 + 1, false, scores, h)); 

		// Else (If current move is Minimizer), find the minimum 
		// attainable value 
		else
			return Math.min(minimax(depth+1, nodeIndex*2, true, scores, h), 
				minimax(depth+1, nodeIndex*2 + 1, true, scores, h)); 
	} 

	// A utility function to find Log n in base 2 
	static int log2(int n) 
	{ 
	return (n==1)? 0 : 1 + log2(n/2); 
	} 

	// Driver code 

		public static void main (String[] args) { 
				// The number of elements in scores must be 
		// a power of 2. 
		int scores[] = {3, 5, 2, 9, 12, 5, 23, 23}; 
		int n = scores.length; 
		int h = log2(n); 
		System.out.println("h= :"+h);
		int res = minimax(0, 0, true, scores, 3); 
		System.out.println( "The optimal value is : " +res); 
			
		} 
	} 

	// This code is contributed by vt_m 


