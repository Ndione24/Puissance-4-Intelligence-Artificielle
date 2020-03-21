package versionp;

public class Test {

	public static void main(String[] args) {
		Board b = new Board();
		Puissance4 ai = new Puissance4(b);
		ai.playAgainstAIConsole();

	}
}
