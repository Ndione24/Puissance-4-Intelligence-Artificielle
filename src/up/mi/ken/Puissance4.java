package up.mi.ken;

import java.util.*;

public class Puissance4 {
	private Board b;
	private Scanner scan;
	private int nextMoveLocation = -1;
	private int maxDepth = 11;

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public Puissance4(Board b) {
		this.b = b;
		scan = new Scanner(System.in);
	}

	/**
	 * Assuming human is the opponent <i>let the opponent play</i>
	 */

	public void letOpponentMove() {
		System.out.println("Joues entre 1-7 : ");
		int move = scan.nextInt();
		while (move < 1 || move > 7 || !b.isPossibleMove(move - 1)) {
			System.out.println("Invalid move.\n\nYour move (1-7): ");
			move = scan.nextInt();
		}

		// on suppose que 1 est le pion de l'IA
		b.placeMove(move - 1, (int) 2);
	}

	/**
	 * <i>checks if a player has won </i>
	 * 
	 * @param b the board
	 * @return 1 if the ai player has won ,2 else
	 */
	public int checkWinner(Board b) {
		int aiScore = 0, humanScore = 0;
		for (int i = 5; i >= 0; --i)// iterating over lines
		{
			for (int j = 0; j <= 6; ++j) // iterate over columns
			{
				if (b.board[i][j] == 0)
					continue;

				// Checking cells to the right
				if (j <= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.board[i][j + k] == 1)
							aiScore++;
						else if (b.board[i][j + k] == 2)
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

				// Checking cells up
				if (i >= 3) {
					for (int k = 0; k < 4; ++k) {
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
				if (j >= 3 && i >= 3) {
					for (int k = 0; k < 4; ++k) {
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

		for (int j = 0; j < 7; ++j) {
			// if the game hasn't ended yet
			if (b.board[0][j] == 0)
				return -1;
		}
		// if it's a tie
		return 0;
	}

	/**
	 * this method calculates the score of a player
	 * 
	 * @param aiScore      the score of the ai
	 * @param playableSpot available moves
	 * @return the score of the ai
	 */

	int calculateScore(int aiScore, int playableSpot) {

		if (aiScore == 0)
			return 0;
		else if (aiScore == 1)
			return 1 * playableSpot;
		else if (aiScore == 2)
			return 10 * playableSpot;
		else if (aiScore == 3)
			return 150 * playableSpot;
		else
			return 2000;
	}

	/**
	 * <i>evaluate the favorableness of the board for the AI player</i>
	 * 
	 * @param b the board
	 * @return
	 */
	public int evaluateBoard(Board b) {

		int aiScore = 1;
		int score = 0;
		int blanks = 0;
		int k = 0, playableSpot = 0;
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

					playableSpot = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j + c;
							for (int m = i; m <= 5; m++) {
								if (b.board[m][column] == 0)
									playableSpot++;
								else
									break;
							}
						}

					if (playableSpot != 0)
						score += calculateScore(aiScore, playableSpot);
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
					playableSpot = 0;

					if (aiScore > 0) {
						int column = j;
						for (int m = i - k + 1; m <= i - 1; m++) {
							if (b.board[m][column] == 0)
								playableSpot++;
							else
								break;
						}
					}
					if (playableSpot != 0)
						score += calculateScore(aiScore, playableSpot);
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
					playableSpot = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j - c;
							for (int m = i; m <= 5; m++) {
								if (b.board[m][column] == 0)
									playableSpot++;
								else
									break;
							}
						}

					if (playableSpot != 0)
						score += calculateScore(aiScore, playableSpot);
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
					playableSpot = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j + c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.board[m][column] == 0)
									playableSpot++;
								else if (b.board[m][column] == 1)
									;
								else
									break;
							}
						}
						if (playableSpot != 0)
							score += calculateScore(aiScore, playableSpot);
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
					playableSpot = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j - c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.board[m][column] == 0)
									playableSpot++;
								else if (b.board[m][column] == 1)
									;
								else
									break;
							}
						}
						if (playableSpot != 0)
							score += calculateScore(aiScore, playableSpot);
						aiScore = 1;
						blanks = 0;
					}
				}
			}
		}
		return score;
	}
	
	/**
	 * 
	 * @param depth the depth of the explorartion tree
	 * @param turn who's turn?
	 * @return the best score for the player
	 */
	public int minimax(int depth, int turn) {

		// we'll evaluate the board when we reach the maxDepth
				if (depth == getMaxDepth())
					return evaluateBoard(b);
		int winner = checkWinner(b);

		if (winner == 1)
			return Integer.MAX_VALUE / 2;
		else if (winner == 2)
			return Integer.MIN_VALUE / 2;
		else if (winner == 0)
			return 0;
		

		int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

		for (int j = 0; j <= 6; ++j) {

			int currentScore = 0;

			if (!b.isPossibleMove(j))
				continue;

			if (turn == 1) {
				b.placeMove(j, 1);
				currentScore = minimax(depth + 1, 2);

				if (depth == 0) {

					if (currentScore > maxScore)
						nextMoveLocation = j;
					if (currentScore == Integer.MAX_VALUE / 2) {
						b.undoMove(j);
						break;
					}
				}

				maxScore = Math.max(currentScore, maxScore);

				//alpha = Math.max(currentScore, alpha);
			} else if (turn == 2) {
				b.placeMove(j, 2);
				currentScore = minimax(depth + 1, 1);
				minScore = Math.min(currentScore, minScore);

				//beta = Math.min(currentScore, beta);
			}
			b.undoMove(j);
			if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE)
				break;
		}
		return turn == 1 ? maxScore : minScore;
	}
	
	

	/**
	 * the minimaxAlphaBeta algorithm
	 * 
	 * @param depth the depth of the tree
	 * @param turn  who's turn
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public int minimaxAlphaBeta(int depth, int turn, int alpha, int beta) {

		if (beta <= alpha) {
			if (turn == 1)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}
		int winner = checkWinner(b);

		if (winner == 1)
			return Integer.MAX_VALUE / 2;
		else if (winner == 2)
			return Integer.MIN_VALUE / 2;
		else if (winner == 0)
			return 0;
		// we'll evaluate the board when we reach the maxDepth
		if (depth == getMaxDepth())
			return evaluateBoard(b);

		int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

		for (int j = 0; j <= 6; ++j) {

			int currentScore = 0;

			if (!b.isPossibleMove(j))
				continue;

			if (turn == 1) {
				b.placeMove(j, 1);
				currentScore = minimaxAlphaBeta(depth + 1, 2, alpha, beta);

				if (depth == 0) {

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
				currentScore = minimaxAlphaBeta(depth + 1, 1, alpha, beta);
				minScore = Math.min(currentScore, minScore);

				beta = Math.min(currentScore, beta);
			}
			b.undoMove(j);
			if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE)
				break;
		}
		return turn == 1 ? maxScore : minScore;
	}

	/**
	 * <i>calculates the favorbleness for an AI player using the MINIMAX
	 * algorithm</i>
	 * 
	 * @return the favorable spot to play at on the board
	 */
	public int getAIMove() {
		nextMoveLocation = -1;
		minimaxAlphaBeta(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return nextMoveLocation;
	}

	/**
	 * human plays against ai
	 */
	public void playAgainstAIConsole() {
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

			int winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}

			b.placeMove(getAIMove(), 1);
			b.displayBoard();
			winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}
		}

	}

	/**
	 * human vs human
	 */
	public void playAgainstHuman() {
		int humanMove = -1;
		Scanner scan = new Scanner(System.in);
		b.displayBoard();
		System.out.println("Game started!!!");

		while (true) {

			b.displayBoard();

			System.out.println("Play between 1-7");
			int move = scan.nextInt();

			b.placeMove(move - 1, 1);
			b.displayBoard();

			int winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("Player 1 has won!");
				break;
			} else if (winner == 2) {
				System.out.println("Player 2 has won!");
				break;
			} else if (winner == 0) {
				System.out.println("It's a Draw!");
				break;
			}
			System.out.println("Player 2, play between 1-7");
			int move2 = scan.nextInt();
			b.placeMove(move2 - 1, 2);

		}
	}

	/**
	 * Makes AI play against AI
	 */
	public void playAIAgainstAI() {
		setMaxDepth(11);

		while (true) {
			b.placeMove(getAIMove(), 1);
			b.displayBoard();

			int winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI 1 Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("AI 2 Wins!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}

			b.placeMove(getAIMove(), 2);
			b.displayBoard();
			winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}
		}

	}
	
	
	/**
	 * <i>calculates the favorbleness for an AI player using the MINIMAX
	 * algorithm</i>
	 * it fills the nextMoveLocation using minimax algorithm
	 * @return the favorable spot to play at on the board
	 */
	public int getAIMoveMinimax() {
		nextMoveLocation = -1;
		minimax(0, 1);
		return nextMoveLocation;
	}
	
	/**
	 * human plays against ai
	 */
	public void playAgainstAIConsoleUsingMinimax() {
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

			int winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}

			b.placeMove(getAIMoveMinimax(), 1);
			b.displayBoard();
			winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}
		}

	}
	
	
	/**
	 * Makes AI play against AI using simple minimax algorithm
	 */
	public void playAIAgainstAIUsingMinimax() {
		setMaxDepth(11);

		while (true) {
			//the AI 1 Plays
			b.placeMove(getAIMoveMinimax(), 1);
			
			b.displayBoard();

			int winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI 1 Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("AI 2 Wins!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}
            //the AI 2 Plays
			b.placeMove(getAIMoveMinimax(), 2);
			b.displayBoard();
			winner = checkWinner(b);
			if (winner == 1) {
				System.out.println("AI Wins!");
				break;
			} else if (winner == 2) {
				System.out.println("You Win!");
				break;
			} else if (winner == 0) {
				System.out.println("Draw!");
				break;
			}
		}

	}
	
	

}
