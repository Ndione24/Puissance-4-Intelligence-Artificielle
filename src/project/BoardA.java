package project;

public class BoardA {

	// 1->IA and 2->human
	private int player;
	private int[][] board;
	private Game game;

	public BoardA(Game game, int[][] board, int player) {
		this.game = game;
		this.board = board;
		this.player = player;

	}

	/**
	 * Verifie si y'a un gagnant ou pas
	 * 
	 * @param depth
	 * @param score
	 * @return
	 */
	public boolean isGameFinish(int depth, int score) {
		if (depth == 0 || game.getScore() == score || game.getScore() == (-score) || isBoardFull()) {
			return true;
		}
		return false;
	}
	

	/**
	 * Verifie si le board est plein
	 * @return bool
	 */
	public boolean isBoardFull() {
		for (int i = 0; i < 7; i++) {
			if (this.board[0][i] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Joue à une colonne donnée
	 * @param column : colonne où on souhaite joué
	 * @return true si on a pu jouer
	 */
	public boolean playColumn(int column) {
		if (this.board[0][column] == 0 && column >=0 && column <7) {
			for (int i = 5; i >= 0; i--) {
				if (this.board[i][column] == 0) {
					this.board[i][column] = this.player;
					this.player = this.game.changePlayer(this.player);
					break;
				}
			}
			// on change de joueur
			//this.player = this.game.changePlayer(this.player);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Le score par rapport à une position donnée
	 * @param row
	 * @param column
	 * @param delta_y
	 * @param delta_x
	 * @return
	 */
	public int ScorePosition(int row, int column, int delta_y, int delta_x) {
		int point_human = 0;
		int point_IA = 0;

		// determines si ya gagnant

		for (int i = 0; i < 4; i++) {
			if (this.board[row][column] == 1) {
				point_human++;
			} else if (this.board[row][column] == 2) {
				point_IA++;
			}
			

			// on se deplace
			row += delta_y;
			column += delta_x;

		}

		// ya t-il un gagnant ?
		if (point_human == 4) {
			return -this.game.getScore();
		} else if (point_IA == 4) {
			return this.game.getScore();
		}
		else {
			// sinon on retourne le nombre de point du l'ordinateur
			return point_IA;
		}
		

	}
	
	
	
/**
 * calcul le score et verifie s'il ya quatre pions alignés
 * @return
 */
	public int calculeScore() {
		int totalPoint = 0;
		int vertical_points = 0;
		int horizontal_points = 0;
		int diagonal_points1 = 0;
		int diagonal_points2 = 0;

		// check vertical

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 7; col++) {
				int score = ScorePosition(row, col, 1, 0);
				if (score == this.game.getScore()) {
					return this.game.getScore();
				}
				if (score == (-this.game.getScore())) {
					return (-this.game.getScore());
				}

				vertical_points += score;
			}
		}

		// check horizontal

		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 4; col++) {
				int score = ScorePosition(row, col, 0, 1);
				if (score == this.game.getScore()) {
					return this.game.getScore();
				}
				if (score == (-this.game.getScore())) {
					return (-this.game.getScore());
				}

				horizontal_points += score;
			}
		}

		// check diagonal Left

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 4; col++) {
				int score = ScorePosition(row, col, 1, 1);
				if (score == this.game.getScore()) {
					return this.game.getScore();
				}
				if (score == (-this.game.getScore())) {
					return (-this.game.getScore());
				}

				diagonal_points1 += score;
			}
		}

		// check diagonal right

		for (int row = 3; row < 6; row++) {
			for (int col = 0; col <= 3; col++) {
				int score = ScorePosition(row, col, -1, +1);
				if (score == this.game.getScore()) {
					return this.game.getScore();
				}
				if (score == (-this.game.getScore())) {
					return (-this.game.getScore());
				}

				diagonal_points2 += score;
			}
		}

		totalPoint = horizontal_points + vertical_points + diagonal_points1 + diagonal_points2;
		return totalPoint;
		//return 0;

	}
	
	/**
	 * copy du tableau
	 */

	public BoardA copy() 
	{
		int [][]newBoard= new int [6][7];
		
		for(int i=0;i<6;i++) 
		{
			
				newBoard[i]=(int[]) this.board[i].clone();
			
		}
		
		return new BoardA(this.game,newBoard,this.player);
	}
	
	
	public void undoMove(int column) {
		for (int i = 0; i <= 5; ++i) {
			if (this.board[i][column] != 0) {
				this.board[i][column] = 0;
				break;
			}
		}
	}
	
	
	/**
	 * affiche le numero des colonnes
	 */
	private void afficherNumColonnes() {

		for (int i = 1; i <= 7; i++) {
			System.out.print("  " + i + " ");
		}
	}

	/**
	 * permet de savoir si une colonne est pleine ou pas
	 * 
	 * @param col le numéro de la colonne
	 * @return vraie si la colonne est pleine
	 */
	public boolean isColumnFull(int col) {
		return this.board[0][col - 1] != 0;
	}

	
	/**
	 * Affiche l'interface de jeu
	 */
	public void displayBoard() {
		afficherNumColonnes();
		System.out.println();

		for (int i = 0; i < 6; i++) {
			System.out.print("+---+---+---+---+---+---+---+\n");
			System.out.print("|");

			for (int j = 0; j < 7; j++) {
				if (board[i][j] == 0) {

					System.out.print(" " + " " + " " + "|");
					
				} else if (board[i][j] == 1) {
					System.out.print(" " + "&" + " " + "|");
				} else {
					//X ia
					System.out.print(" " + "X" + " " + "|");
				}

			}
			System.out.print("\n");
		}
		System.out.print("+---+---+---+---+---+---+---+\n");
		afficherNumColonnes();
		System.out.println("\n\n");
	}

}
