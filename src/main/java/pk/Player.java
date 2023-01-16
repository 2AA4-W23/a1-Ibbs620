package pk;

import java.util.Random;

public class Player {

    private int diceLeft = 0;
    private int totalDice = 0;
    private Faces[] keptDice;
    private int skullsRolled = 0;

    public Player(int totalDice) {
        this.totalDice = totalDice;
        this.diceLeft = totalDice;
        this.keptDice = new Faces[totalDice];
    }

    public void rollAndKeep() {
        Faces[] rolls = Dice.rollN(diceLeft);
        for (Faces face : rolls) {
            if ((new Random()).nextInt(2) == 1) {
                keptDice[totalDice - diceLeft] = face;
            }
        }
    }

    public int countPoints() {
        int score = 0;
        for (Faces face : this.keptDice) {
            if (face == Faces.DIAMOND || face == Faces.GOLD)
                score += 100;
        }
        return score;
    }
}
