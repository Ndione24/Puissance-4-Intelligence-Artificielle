package project;

public class Board {

	private int depth;
	private int score;
	// 1->IA and 2->human
	private int player;
	private int[][] board = new int[6][7];
	
	/**
	 * met tout à zero
	 */
	
	
	public Board() 
	{
		
		this.depth=4;
		this.score=1000;
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				this.board[i][j] = 0;
	}
	

	public void initBoard()
	{
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				this.board[i][j] = 0;
	}
	
	
	public int getDepth() 
	{
		return this.depth;
	}
	public void setDepth(int newDepth) 
	{
		this.depth=newDepth;
	}
	
	public int getScore() 
	{
		return this.score;
	}
	
	public void setScore(int newScore) 
	{
		this.score=newScore;
	}
	private void afficherNumColonnes() {

		for (int i = 1; i <= 7; i++) {
			System.out.print("  " + i + " ");
		}
	}
	
	
	public boolean isGameFinish(int depth, int score) 
	{
		if(depth==0 || this.score==score || this.score==(-score) || isBoardFull()) 
		{
			return true;
		}
		return false;
	}
	
	public boolean isBoardFull() 
	{
		for(int i=0;i<7;i++) 
		{
			if(this.board[0][i]==0) 
			{
				return false;
			}
		}
		return true;
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
	
	
	public int jouerCoup(int numColonne) 
	{
		
		if((numColonne)>7 || numColonne<1) 
		{
			System.out.println("pas possible de jour à la colonne "+numColonne);
			
		}
		
		int i=5;
											
		while(i>=0 && ! isColumnFull(numColonne)) 
		{
			if(this.board[i][numColonne-1]==0)
			{
				//this.board[i][numColonne-1]=playerToChar(Joueur.getNumJoueur());
				return i;
			}
			else i--;
		}
		
		return i;
	}
	
	public static void playColumn(int col, int player) {
		Joueur.setNumJoueur(player);
		Joueur.jouerCoup(col);
	}
	
	
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

