package project;

import java.util.Scanner;

public class Game {
	
	private int rows;
	private int column;
	private int score;
	private int depth;
	private int player; //1 Human 2 IA
	private BoardA board;
	
	public Game() 
	{
		this.rows=6;
		this.column=7;
		this.score=100000;
		this.depth=4;
		this.player=1;
		
		initBoard();
	}
	
	
	//initialisation de la grille
	public void initBoard() 
	{
		int[][] boardTemplate=new int[rows][column];
		
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				boardTemplate[i][j] = 0;
		
		 this.board = new BoardA(this, boardTemplate, 1);
		 
	} 
	
	public int getScore() 
	{
		return this.score;
	}
	
	public void setScore(int newScore) 
	{
		this.score=newScore;
	}
	public int getDepth() 
	{
		return this.depth;
	}
	
	public void setDepth(int newDepth) 
	{
		this.depth=newDepth;
	}
	
	public int changePlayer(int player) 
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
	
	
	public void ComputerDecision() 
	{
		
			int []moveIA=maximizer(this.board,depth);
			//and play to position giving bay maximixer
			//System.out.println("move to "+moveIA[0]);
			this.board.playColumn(moveIA[0]);
		
	}
	
	
	public int [] maximizer(BoardA boardm, int depth) 
	{
	
		//on vérifie le score de la grille
		int score=boardm.calculeScore();
		
		if(score==100000) 
		{
			
		}
		
		//System.out.println("depth :"+depth);
		if(board.isGameFinish(depth,score)) 
		{
			int [] result= {0,score};
			return result;
		}
		
		 // Column, Score
	    int []max = {0, -99999};
	    
	    // For all possible moves
	    for (int col = 0; col< 7; col++) {
	        BoardA new_board = boardm.copy(); // Create new board
	        
	        if (new_board.playColumn(col)) {

	           

	            int[] next_move = minimizer(new_board,depth-1); // Recursive calling

	            // Evaluate new move
	            if (max[0] == 0 || next_move[1] > max[1]) {
	                max[0] = col;
	                max[1] = next_move[1];
	               // System.out.println("max depth "+depth);
	            }
	        }
	    }
	    
	    
		return max;
		
	}
	
	
	public int [] minimizer(BoardA boardd,int depth) 
	{
	
		//on vérifie le score de la grille
		int score=boardd.calculeScore();
		
		if(board.isGameFinish(depth,score)) 
		{
			int [] result= {0,score};
			return result;
		}
		
		 // Column, Score
	    int []min = {0, 99999};
	    
	    for (int col = 0; col <7; col++) {
	        BoardA new_board = boardd.copy();

	        if (new_board.playColumn(col)){
	            int [] next_move = maximizer(new_board,depth-1);

	            if (min[0] == 0 || next_move[1] < min[1]) {
	                min[0] = col;
	                min[1] = next_move[1];
	                //System.out.println("min depth "+depth);
	            }

	        }
	    }
	    
		return min;
		
	}
	
	
	public void playAgainstAIConsole()
	{
		System.out.println();
		System.out.println("       NDIONE SISSOKHO IA PROJECT         ");
		System.out.println();
		System.out.println();
		
		Scanner scan= new Scanner(System.in);
		//System.out.println("Would you like to play first ? Type (yes/no) ");
		//String answer = scan.next().trim();
		
		this.board.displayBoard();
		while (true) {
			System.out.println("jouez entre 1 et 7");
			int col=scan.nextInt();
			this.board.playColumn(col-1);
			this.board.displayBoard();
		
			
		if(this.board.calculeScore()==this.score) 
		{
			System.out.println("AI wins ");
			break;
		}
		if(this.board.calculeScore()==-this.score) 
		{
			System.out.println("You wins ");
			break;
		}
		
		//utiliser les static afin de manpulier les la grille, 
		//mettre en place alpha-beta et comparer
		//Q learning 
		ComputerDecision();
		this.board.displayBoard();
		
		if(this.board.calculeScore()==this.score) 
		{
			System.out.println("AI wins ");
			break;
		}
		if(this.board.calculeScore()==-this.score) 
		{
			System.out.println("You wins ");
			break;
		}
		
		}
	
	}
}
