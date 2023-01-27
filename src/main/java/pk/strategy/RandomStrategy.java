package pk.strategy;

import java.util.Random;

import pk.cards.Card;
import pk.dice.Faces;

public class RandomStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        Random r = new Random();
        boolean[] reroll = new boolean[rolls.length];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == Faces.SKULL) {
                reroll[i] = false;
            } else if (r.nextInt(4) == 1) // give each dice a 1/4 chance of a reroll
                reroll[i] = true;
            else
                reroll[i] = false;
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int[] count = new int[6];
        for (Faces face : rolls) { // count all diamonds and gold rolled for 100 points each
            if (face != null)
                count[face.ordinal()]++;
        }
        return count[Faces.SKULL.ordinal()] < 3;
    }

    @Override
    public int countPoints(Faces[] rolls, Card card) {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };
        int score = 0;

        for (Faces face : rolls) { // count all faces rolled
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return 0;
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue;
            score += nOfAKindScores[count[face.ordinal()]];
        }
        score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100;
        return score;
    }

}