package versionp;
import java.util.*;

public class Puissance4 {
	
	private Board b;
	private Scanner scan;
	private int nextMoveLocation = -1;
	private int maxDepth = 10;

	public Puissance4(Board b) {
		this.b = b;
		scan = new Scanner(System.in);
	}

	/**
	 * <i>let the opponent play</i>
	 */
	public void letOpponentMove() {
		System.out.println("Joues entre 1-7 : ");
		int move = scan.nextInt();
		while (move < 1 || move > 7 || !b.isPossibleMove(move - 1)) {
			System.out.println("Invalid move.\n\nYour move (1-7): ");
			move = scan.nextInt();
		}

		// 1 IA et 2 human
		b.placeMove(move - 1, (int) 2);
	}
	/**
	 * <i>checks if a player has won </i>
	 * @param b the board
	 * @return 1 if the ai player has won ,2 else
	 */
	public int gameResult(Board b) {
		int aiScore = 0, humanScore = 0;
		for (int i = 5; i >= 0; --i)//iterating over lines
		{
			for (int j = 0; j <= 6; ++j) //iterate over columns
			{
				if (b.board[i][j] == 0){
					continue;
				}
				// Checking cells to the right
				if (j <= 3) {
					for (int k = 0; k < 4; ++k)
					{
						if (b.board[i][j + k] == 1)
							aiScore++;
						else if (b.board[i][j + k] == 2)
							humanScore++;
						else
							break;
					}
					//4 pions de L'iA sont ils alignés ? si oui return 1 pour dire que L'IA a gagné
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// Checking cells up
				if (i >= 3) 
				{
					for (int k = 0; k < 4; ++k)
					{
						if (b.board[i - k][j] == 1)
							aiScore++;
						else if (b.board[i - k][j] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// Checking diagonal up-right
				if (j <= 3 && i >= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.board[i - k][j + k] == 1)
							aiScore++;
						else if (b.board[i - k][j + k] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// Checking diagonal up-left
				if (j >= 3 && i >= 3)
				{
					for (int k = 0; k < 4; ++k) 
					{
						if (b.board[i - k][j - k] == 1)
							aiScore++;
						else if (b.board[i - k][j - k] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}
			}
		}

		for (int j = 0; j < 7; ++j)
		{
			// if the game hasn't ended yet
			if (b.board[0][j] == 0)
				return -1;
		}
		// if it's a tie
		return 0;
	}
	/**
	 * this method calculates the score of a player
	 * @param aiScore the score of the ai
	 * @param moreMoves available moves
	 * @return the score of the  ai
	 */

	int calculateScore(int aiScore, int moreMoves) {
		System.out.println("moreMoves "+moreMoves);
		int moveScore = 4 - moreMoves;
		if (aiScore == 0)
			return 0;
		else if (aiScore == 1)
			return 1 * moveScore;
		else if (aiScore == 2)
			return 10 * moveScore;
		else if (aiScore == 3)
			return 100 * moveScore;
		else	
			return 1000;
	}

	
	/**
	 * <i>evaluate the favorableness of the board
	 * for the AI player</i>
	 * @param b the board
	 * @return
	 */
	public int evaluateBoard(Board b) {

		int aiScore = 1;
		int score = 0;
		int blanks = 0;
		int k = 0, moreMoves = 0;
		for (int i = 5; i >= 0; --i) {
			for (int j = 0; j <= 6; ++j) {

				if (b.board[i][j] == 0 || b.board[i][j] == 2)
					continue;

				if (j <= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.board[i][j + k] == 1)
							aiScore++;
						else if (b.board[i][j + k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}

					moreMoves = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j + c;
							for (int m = i; m <= 5; m++) {
								if (b.board[m][column] == 0)
									moreMoves++;
								else
									break;
							}
						}

					if (moreMoves != 0)
						score += calculateScore(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (i >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.board[i - k][j] == 1)
							aiScore++;
						else if (b.board[i - k][j] == 2) {
							aiScore = 0;
							break;
						}
					}
					moreMoves = 0;

					if (aiScore > 0) {
						int column = j;
						for (int m = i - k + 1; m <= i - 1; m++) {
							if (b.board[m][column] == 0)
								moreMoves++;
							else
								break;
						}
					}
					if (moreMoves != 0)
						score += calculateScore(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (j >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.board[i][j - k] == 1)
							aiScore++;
						else if (b.board[i][j - k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j - c;
							for (int m = i; m <= 5; m++) {
								if (b.board[m][column] == 0)
									moreMoves++;
								else
									break;
							}
						}

					if (moreMoves != 0)
						score += calculateScore(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (j <= 3 && i >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.board[i - k][j + k] == 1)
							aiScore++;
						else if (b.board[i - k][j + k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j + c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.board[m][column] == 0)
									moreMoves++;
								else if (b.board[m][column] == 1)
									;
								else
									break;
							}
						}
						if (moreMoves != 0)
							score += calculateScore(aiScore, moreMoves);
						aiScore = 1;
						blanks = 0;
					}
				}

				if (i >= 3 && j >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.board[i - k][j - k] == 1)
							aiScore++;
						else if (b.board[i - k][j - k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j - c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.board[m][column] == 0)
									moreMoves++;
								else if (b.board[m][column] == 1)
									;
								else
									break;
							}
						}
						if (moreMoves != 0)
							score += calculateScore(aiScore, moreMoves);
						aiScore = 1;
						blanks = 0;
					}
				}
			}
		}
		return score;
	}
	
	
	
	static class Move {
		int row, col;
	};
	
	/*
	static int minimax(char board[][], int depth, Boolean isMax) {
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
		if (isMovesLeft(board) == false)
			return 0;

		// If this maximizer's move
		if (isMax) {
			int best = -1000;

			// Traverse all cells
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == '_') {
						// Make the move
						board[i][j] = player;

						// Call minimax recursively and choose
						// the maximum value
						best = Math.max(best, minimax(board, depth + 1, !isMax));

						// Undo the move
						board[i][j] = '_';
					}
				}
			}
			return best;
		}

		// If this minimizer's move
		else {
			int best = 1000;

			// Traverse all cells
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == '_') {
						// Make the move
						board[i][j] = opponent;

						// Call minimax recursively and choose
						// the minimum value
						best = Math.min(best, minimax(board, depth + 1, !isMax));

						// Undo the move
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}


*/
	
/**
 * the minimax algorithm
 * @param depth the depth of the tree
 * @param turn who's turn
 * @param alpha
 * @param beta
 * @return
 */
	public int minimax(int depth, int turn, int alpha, int beta) {

		if (beta <= alpha) {
			if (turn == 1)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}
		int gameResult = gameResult(b);

		if (gameResult == 1)
			return Integer.MAX_VALUE / 2;
		else if (gameResult == 2)
			return Integer.MIN_VALUE / 2;
		else if (gameResult == 0)
			return 0;

		if (depth == maxDepth)
			return evaluateBoard(b);

		int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

		for (int j = 0; j <= 6; ++j) {

			int currentScore = 0;

			if (!b.isPossibleMove(j))
				continue;

			if (turn == 1) {
				b.placeMove(j, 1);
				currentScore = minimax(depth + 1, 2, alpha, beta);

				if (depth == 0) {
					System.out.println("Score for location " + j + " = " + currentScore);
					if (currentScore > maxScore)
						nextMoveLocation = j;
					if (currentScore == Integer.MAX_VALUE / 2) {
						b.undoMove(j);
						break;
					}
				}

				maxScore = Math.max(currentScore, maxScore);

				alpha = Math.max(currentScore, alpha);
			} else if (turn == 2) {
				b.placeMove(j, 2);
				currentScore = minimax(depth + 1, 1, alpha, beta);
				minScore = Math.min(currentScore, minScore);

				beta = Math.min(currentScore, beta);
			}
			b.undoMove(j);
			if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE)
				break;
		}
		return turn == 1 ? maxScore : minScore;
	}

	public int getAIMove() {
		nextMoveLocation = -1;
		minimax(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return nextMoveLocation;
	}

	public void playAgainstAIConsole()
	{
		int humanMove = -1;
		Scanner scan = new Scanner(System.in);
		b.displayBoard();
		System.out.println("Would you like to play first ? Type (yes/no) ");
		String answer = scan.next().trim();

		if (answer.equalsIgnoreCase("yes"))
			letOpponentMove();
		b.displayBoard();
		b.placeMove(3, 1);
		b.displayBoard();

		while (true) {
			letOpponentMove();
			b.displayBoard();

			int gameResult = gameResult(b);
			if (gameResult == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (gameResult == 2) {
				System.out.println("You Win!");
				break;
			} else if (gameResult == 0) {
				System.out.println("Draw!");
				break;
			}

			b.placeMove(getAIMove(), 1);
			b.displayBoard();
			gameResult = gameResult(b);
			if (gameResult == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (gameResult == 2) {
				System.out.println("You Win!");
				break;
			} else if (gameResult == 0) {
				System.out.println("Draw!");
				break;
			}
		}
		

	}

}
