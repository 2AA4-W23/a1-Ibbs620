import pk.*;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        int[] wins = new int[3]; // wins[0] reserved for counting ties. otherwise index equals player number
        Game game = ArgumentHandler.getGame(args); // initialize a game based on provided arguments

        for (int i = 1; i <= 42; i++) {
            Player winner = game.playGame(); // get winner from playing a game
            wins[winner.playerNumber]++; // increment wins for player that won
            game.resetGame();
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