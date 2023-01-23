import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pk.Game;
import pk.Player;

public class PiratenKarpen {

    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        int[] wins = new int[3]; // wins[0] reserved for counting ties
        for (int i = 1; i <= 42; i++) {
            boolean trace;
            if (args.length == 0) // handles command line arguments. If none provided, default to no tracing
                trace = false;
            else
                trace = args[0].equals("trace") || args[0].equals("t");

            Game game = new Game(2, 8, trace);
            Player winner = game.playGame();
            wins[winner.playerNumber]++; // increment wins for player that won
        }

        System.out.println("-----------------------------------------------"); // output percentages
        double P1WinPercentage = (double) wins[1] / 42 * 100;
        double P2WinPercentage = (double) wins[2] / 42 * 100;
        double tiePercentage = (double) wins[0] / 42 * 100;
        System.out.printf("P1 wins: %.2f%%\n", P1WinPercentage);
        System.out.printf("P2 wins: %.2f%%\n", P2WinPercentage);
        System.out.printf("Tied: %.2f%%\n", tiePercentage);
        System.out.println("-----------------------------------------------");
    }
}