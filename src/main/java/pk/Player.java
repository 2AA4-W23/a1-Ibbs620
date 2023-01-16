package pk;

import java.util.Random;

public class Player {

    private int totalDice = 0;
    private Faces[] rolledDice;
    private int skullsRolled = 0;

    public Player(int totalDice) {
        this.totalDice = totalDice;
        this.rolledDice = new Faces[totalDice];
    }

    public void reRoll(boolean[] diceToRoll) {
        if (!canRollAgain())
            return;

        if (this.rolledDice[0] == null) { // No dice rolled yet, so roll all eight
            rolledDice = Dice.rollN(totalDice);
        } else { // reroll specific dice
            for (int i = 0; i < this.totalDice; i++) {
                if (diceToRoll[i])
                    this.rolledDice[i] = Dice.roll();
                if (rolledDice[i] == Faces.SKULL)
                    skullsRolled++;
            }
            System.out.print("Rerolling dice "); // print rerolled dice for debug
            for (int i = 0; i < diceToRoll.length; i++) {
                if (diceToRoll[i])
                    System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

    public boolean[] selectReroll() {
        boolean[] reroll = new boolean[8];
        this.skullsRolled = 0;
        for (int i = 0; i < this.totalDice; i++) {
            if (this.rolledDice[i] == Faces.SKULL) {
                reroll[i] = false;
                this.skullsRolled++;
            } else if ((new Random()).nextInt(2) == 1)
                reroll[i] = true;
            else
                reroll[i] = false;
        }
        return reroll;
    }

    public int countPoints() {
        int score = 0;
        for (Faces face : this.rolledDice) {
            if (face == Faces.DIAMOND || face == Faces.GOLD)
                score += 100;
        }
        return score;
    }

    public boolean canRollAgain() {
        return this.skullsRolled < 3;
    }

    public void printRolls() {
        System.out.print("Rolls: ");
        for (Faces face : this.rolledDice) {
            System.out.print(face + " ");
        }
        System.out.println();
    }
}
