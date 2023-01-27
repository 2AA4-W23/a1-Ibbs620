package pk.strategy;

import pk.cards.Card;
import pk.dice.Faces;

public class ComboStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        boolean[] reroll = new boolean[rolls.length];
        int[] count = new int[6];
        for (Faces face : rolls) { // count all diamonds and gold rolled for 100 points each
            if (face != null)
                count[face.ordinal()]++;
        }
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == null) {
                reroll[i] = true;
                continue;
            } else if (rolls[i] == Faces.SKULL || rolls[i] == Faces.DIAMOND || rolls[i] == Faces.GOLD)
                reroll[i] = false; // dont reroll skulls, diamonds, and gold
            else if (count[rolls[i].ordinal()] == 1)
                reroll[i] = true;
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int[] count = new int[6];
        if (rolls[0] == null)
            return true;
        for (Faces face : rolls) {
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 2)
            return false;
        for (Faces face : rolls) {
            if (face == Faces.SKULL || face == Faces.DIAMOND || face == Faces.GOLD)
                continue;
            if (count[face.ordinal()] == 1)
                return true;
        }
        return false;
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