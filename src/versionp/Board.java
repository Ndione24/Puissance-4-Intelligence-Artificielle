package versionp;
public class Board {
	int[][] board = new int[6][7];

	public Board() {
		board = new int[][] { { 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 0, 0, 0, 0, 0, }, };
	}
	
/**
 * <i>checks if the column is playable</i>
 * @param column the column we want to play
 * @return true if the column isn't full
 */
	public boolean isPossibleMove(int column) {
		return board[0][column] == 0;
	}

	/**
	 * 
	 * @param column  the colum we want to play at
	 * @param player the current player
	 * @return true if we've played
	 */
	public boolean placeMove(int column, int player) {
		if (!isPossibleMove(column)) {
			System.out.println("Illegal move!");
			return false;
		}
		for (int i = 5; i >= 0; --i) {
			if (board[i][column] == 0) {
				board[i][column] = (int)player;
				return true;
			}
		}
		return false;
	}
	
	
/**
 * annuler un deplacement
 * <i> initialize a whole column</i>
 * @param column
 */
	public void undoMove(int column) {
		for (int i = 0; i <= 5; ++i) {
			if (board[i][column] != 0) {
				board[i][column] = 0;
				break;
			}
		}
	}
	
  /**
   * <i>show the number of all columns</i>
   */
	private static void afficherNumColonnes() {

		for (int i = 1; i <= 7; i++) {
			System.out.print("  " + i + " ");
		}
	}

	/**
	 * <i>Print the board</i>
	 */
	public void displayBoard() {
		afficherNumColonnes();
		System.out.println();

		for (int i = 0; i < 6; i++) {
			System.out.print("+---+---+---+---+---+---+---+\n");
			System.out.print("|");

			for (int j = 0; j < 7; j++) {
				if(board[i][j]==0) {
				
					System.out.print(" " + " " + " " + "|");
				}else if(board[i][j]==1)
				{
					System.out.print(" " + "&" + " " + "|");
				}
				else {
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
