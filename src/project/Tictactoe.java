package project;

//Java program to find the 
//next optimal move for a player 
public class Tictactoe {
	
	static class Move {
		int row, col;
	};

	static char player = 'x', opponent = 'o';

//This function returns true if there are moves 
//remaining on the board. It returns false if 
//there are no moves left to play. 
	static Boolean isMovesLeft(char board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == '_')
					return true;
		return false;
	}
	
	
	static Boolean isMovesLeft1(int board[][]) {
		for (int i = 0; i < 7; i++) 
		{
			if(board[0][i]==0){
				return true;
			}
		}
			
		return false;
	}

	

	public static int ScorePositionSimple(int board[][],int row, int column, int delta_y, int delta_x) {
		int point_human = 0;
		int point_IA = 0;

		// determines si ya gagnant

		for (int i = 0; i < 4; i++) {
			if (board[row][column] == 1) {
				point_human++;
			} else if (board[row][column] == 2) {
				point_IA++;
			}
			else{
				return 0;
			}

			// on se deplace
			row += delta_y;
			column += delta_x;

		}

		
		// ya t-il un gagnant ?
		if (point_human == 4) {
			return -10;
		} else if (point_IA == 4) {
			return 10;
		}
		else {
			// sinon on retourne le nombre de point du l'ordinateur
			//return point_IA;
			return 0;
		}
		

	}
	
	public static int calculeScore(int board[][]) {
		int totalPoint = 0;
		int vertical_points = 0;
		int horizontal_points = 0;
		int diagonal_points1 = 0;
		int diagonal_points2 = 0;

		// check vertical

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 7; col++) {
				int score = ScorePositionSimple(board,row, col, 1, 0);
				if (score == 10) {
					return 10;
				}
				if (score == -10) {
					return -10;
				}

				vertical_points += score;
			}
		}

		// check horizontal

		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 4; col++) {
				int score = ScorePositionSimple(board,row, col, 0, 1);
				if (score == 10) {
					return 10;
				}
				if (score == -10) {
					return -10;
				}

				horizontal_points += score;
			}
		}

		// check diagonal Left

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 4; col++) {
				int score = ScorePositionSimple(board,row, col, 1, 1);
				if (score == 10) {
					return 10;
				}
				if (score == -10) {
					return -10;
				}

				diagonal_points1 += score;
			}
		}

		// check diagonal right

		for (int row = 3; row < 6; row++) {
			for (int col = 0; col <= 3; col++) {
				int score = ScorePositionSimple(board,row, col, -1, +1);
				if (score == 10) {
					return 10;
				}
				if (score == -10) {
					return -10;
				}

				diagonal_points2 += score;
			}
		}

		totalPoint = horizontal_points + vertical_points + diagonal_points1 + diagonal_points2;
		//return totalPoint;
		return 0;

	}
	
	
	//si 1 human ; si 2 IA
	public static int  checkVictoire(int grille[][]) {
	    //Balayage horizontal _
	    for (int colonne = 0; colonne <= 3; colonne++) {
	        for (int ligne = 5; ligne >= 0; ligne--) {
	            if (grille[colonne][ligne] != 0
	                    && grille[colonne][ligne] == grille[colonne + 1][ligne]
	                    && grille[colonne][ligne] == grille[colonne + 2][ligne]
	                    && grille[colonne][ligne] == grille[colonne + 3][ligne]) {
	                return grille[colonne][ligne];
	            }
	        }
	    }

	    // Balayage verticale |
	    /*for (int colonne = 0; colonne <= 6; colonne++) {
	        for (int ligne = 5; ligne >= 3; ligne--) {
	            if (grille[colonne][ligne] != 0
	                    && grille[colonne][ligne] == grille[colonne][ligne - 1]
	                    && grille[colonne][ligne] == grille[colonne][ligne - 2]
	                    && grille[colonne][ligne] == grille[colonne][ligne - 3]) {
	                return grille[colonne][ligne];
	            }
	        }
	    }*/

	    // Balayage diagonale /
	    for (int colonne = 0; colonne <= 3; colonne++) {
	        for (int ligne = 5; ligne >= 3; ligne--) {
	            if (grille[colonne][ligne] != 0
	                    && grille[colonne][ligne] == grille[colonne + 1][ligne - 1]
	                    && grille[colonne][ligne] == grille[colonne + 2][ligne - 2]
	                    && grille[colonne][ligne] == grille[colonne + 3][ligne - 3]) {
	                return grille[colonne][ligne];
	            }
	        }
	    }

	    // Balayage diagonale \
	    for (int colonne = 6; colonne >= 3; colonne--) {
	        for (int ligne = 5; ligne >= 3; ligne--) {
	            if (grille[colonne][ligne] != 0
	                    && grille[colonne][ligne] == grille[colonne - 1][ligne - 1]
	                    && grille[colonne][ligne] == grille[colonne - 2][ligne - 2]
	                    && grille[colonne][ligne] == grille[colonne - 3][ligne - 3]) {
	                return grille[colonne][ligne];
	            }
	        }
	    }
	    return 0;
	}
	
	public static int evaluate(int board[][]) 
	{
		int check=calculeScore(board);
		if(check==10) 
		{
			return 10;
		}
		//IA
		if(check==-10) 
		{
			return -10;
		}
		
		//sinon
		return 0;
	}
	
	public static void undoMove(int board[][],int column) {
		for (int i = 0; i <= 5; ++i) {
			if (board[i][column] != 0) {
				board[i][column] = 0;
				break;
			}
		}
	}
	
	public static int changePlayer(int player) 
	{
		if(player==1) 
		{
			return 2;
		}
		else 
		{
			return 1;
		}
		
	}
	public static boolean placeMove(int board[][],int column, int player) {
		if (isMovesLeft1(board)==false) {
			return false;
		}
		
		for (int i = 5; i >= 0; --i) {
			if (board[i][column] == 0) {
				board[i][column] = (int)player;
				changePlayer(player);
				return true;
			}
		}
		return false;
	}
	
	
//This is the evaluation function as discussed 
//in the previous article ( http://goo.gl/sJgv68 ) 
	static int evaluate(char b[][]) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == player)
					return +10;
				else if (b[row][0] == opponent)
					return -10;
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == player)
					return +10;

				else if (b[0][col] == opponent)
					return -10;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return +10;
			else if (b[0][0] == opponent)
				return -10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return +10;
			else if (b[0][2] == opponent)
				return -10;
		}

		// Else if none of them have won then return 0
		return 0;
	}

//This is the minimax function. It considers all 
//the possible ways the game can go and returns 
//the value of the board 
	static int minimax(int board[][], int depth, Boolean isMax) {
		int score = evaluate(board);

		// If Maximizer has won the game
		// return his/her evaluated score
		if (score == 10)
			return score;

		// If Minimizer has won the game
		// return his/her evaluated score
		if (score == -10)
			return score;

		// If there are no more moves and
		// no winner then it is a tie
		if (isMovesLeft1(board) == false)
			return 0;

		// If this maximizer's move
		if (isMax) {
			int best = -1000;

			// Traverse all cells
			
				for (int j = 0; j < 7; j++) {
					// Check if cell is empty
					placeMove(board, j, 2);
						// Make the move
						

						// Call minimax recursively and choose
						// the maximum value
						best = Math.max(best, minimax(board, depth + 1, !isMax));

						// Undo the move
						undoMove(board, j);
					}
							
			return best;
		}

		// If this minimizer's move
		else {
			int best = 1000;

			// Traverse all cells
			
				for (int j = 0; j < 7; j++) {
					// Check if cell is empty
					placeMove(board, j, 1);

						// Call minimax recursively and choose
						// the minimum value
						best = Math.min(best, minimax(board, depth + 1, !isMax));

						// Undo the move
						undoMove(board, j);
				
			}
			return best;
		}
	}

//This will return the best possible 
//move for the player 
	static int findBestMove(int board[][]) {
		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;

		// Traverse all cells, evaluate minimax function
		// for all empty cells. And return the cell
		// with optimal value.
		
			for (int j = 0; j < 7; j++) {
				// Check if cell is empty
				
					// Make the move
					placeMove(board,j, 2);

					// compute evaluation function for this
					// move.
					int moveVal = minimax(board, 0, false);

					// Undo the move
					undoMove(board, j);

					// If the value of the current move is
					// more than the best value, then update
					// best/
					if (moveVal > bestVal) {
						
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
		
		System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

		return bestMove.col;
	}

//Driver code 
	public static void main(String[] args) {
		//char board[][] = { { 'x', 'o', 'x' }, { 'o', 'o', 'x' }, { '_', '_', 'o' } };

		//int board[][]=new int [6][7]
		int [][]board ={ { 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, }, };
		
		int bestMove = findBestMove(board);

		System.out.printf("The Optimal Move is :\n");
		System.out.printf("ROW: %d COL: %d\n\n", bestMove);
	}

}

//This code is contributed by PrinciRaj1992 
