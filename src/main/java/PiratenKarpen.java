import javax.swing.text.PlainDocument;

import pk.Dice;
import pk.Faces;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        Player P1 = new Player(8);
        Player P2 = new Player(8);
        System.out.println("Player 1 rolling...");
        while (P1.canRollAgain()) {
            P1.reRoll(P1.selectReroll());
            P1.printRolls();
        }
        System.out.println("Player 1 scored " + P1.countPoints());
    }
}