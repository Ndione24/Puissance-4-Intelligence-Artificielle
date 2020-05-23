package up.mi.ken;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Board b = new Board();
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous jouer contre un humain ou l'IA ???");
		System.out.println("Taper humain pour jouer entre humains!!!");
		System.out.println("Taper IA pour qu'un humain joue contre l'IA");
		System.out.println("Taper IA VS IA pour que 2 IAs jouent l'un contre l'autre");
		String rep = sc.nextLine();
		switch (rep) {
		case "humain":
			Puissance4 humVsHum = new Puissance4(b);
			humVsHum.playAgainstHuman();
			break;
		case "IA":
			Puissance4 ai = new Puissance4(b);
			System.out.println("Taper 1-----> Niveau facile");
			System.out.println("Taper 2-----> Niveau Moyen");
			System.out.println("Taper 3-----> Niveau Difficile");
			sc.hasNextLine();
			int choix = sc.nextInt();
			if (choix == 1) {
				ai.setMaxDepth(5);
				ai.playAgainstAIConsole();
			} else if (choix == 2) {
				ai.setMaxDepth(9);
				ai.playAgainstAIConsole();
			} else if (choix == 3) {
				ai.setMaxDepth(11);
				ai.playAgainstAIConsole();
			}
			break;
		case "IA VS IA":
			Puissance4 aivsai = new Puissance4(b);
			aivsai.playAIAgainstAI();
			break;

		}

	}

}
