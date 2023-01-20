import pk.Game;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        int[] wins = new int[3]; // wins[0] reserved for counting ties
        for (int i = 1; i <= 42; i++) {
            System.out.println("-----------------------------------------------");
            Game game = new Game(2, 8);
            Player winner = game.playGame();
            wins[winner.playerNumber]++;
        }

        System.out.println("-----------------------------------------------");
        double P1WinPercentage = (double) wins[1] / 42 * 100;
        double P2WinPercentage = (double) wins[2] / 42 * 100;
        double tiePercentage = (double) wins[0] / 42 * 100;
        System.out.printf("P1 wins: %.2f%%\n", P1WinPercentage);
        System.out.printf("P2 wins: %.2f%%\n", P2WinPercentage);
        System.out.printf("Tied: %.2f%%\n", tiePercentage);
        System.out.println("-----------------------------------------------");
    }
}