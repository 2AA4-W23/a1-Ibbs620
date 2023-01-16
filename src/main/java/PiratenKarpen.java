import javax.swing.text.PlainDocument;

import pk.Dice;
import pk.Faces;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        int P1Wins = 0;
        int P2Wins = 0;
        int ties = 0;
        for (int i = 1; i <= 42; i++) {
            System.out.println("-----------------------------------------------");
            Player P1 = new Player(8);
            Player P2 = new Player(8);
            System.out.println("Game " + i + " start!");
            System.out.println("Player 1 rolling...");
            while (P1.canRollAgain()) {
                P1.reRoll(P1.selectReroll());
                P1.printRolls();
            }
            System.out.println("Player 1 scored " + P1.countPoints());
            System.out.println("Player 2 rolling...");
            while (P2.canRollAgain()) {
                P2.reRoll(P2.selectReroll());
                P2.printRolls();
            }
            System.out.println("Player 2 scored " + P2.countPoints());
            System.out.print("Player ");

            if (P1.countPoints() > P2.countPoints()) {
                P1Wins++;
                System.out.println("1 wins the game");
            } else if (P1.countPoints() < P2.countPoints()) {
                P2Wins++;
                System.out.println("2 wins the game");
            } else {
                System.out.println("Game is a tie");
                ties++;
            }
        }
        System.out.println("-----------------------------------------------");
        double P1WinPercentage = (double) P1Wins / 42 * 100;
        double P2WinPercentage = (double) P2Wins / 42 * 100;
        double tiePercentage = (double) ties / 42 * 100;
        System.out.printf("P1 wins: %.2f%%\n", P1WinPercentage);
        System.out.printf("P2 wins: %.2f%%\n", P2WinPercentage);
        System.out.printf("Tied: %.2f%%\n", tiePercentage);
        System.out.println("-----------------------------------------------");
    }
}