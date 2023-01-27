package pk.strategy;

import pk.cards.Card;
import pk.dice.Faces;

public class MonkeyBusinessStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        boolean reroll[] = new boolean[rolls.length];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == Faces.MONKEY || rolls[i] == Faces.PARROT) {
                reroll[i] = false; // dont reroll monkets and parrots for max combos
            } else if (rolls[i] == Faces.SKULL) {
                reroll[i] = false; // cant reroll skulls
            } else {
                reroll[i] = true;
            }
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int monkeysAndParrots = 0;
        int skulls = 0;
        if (rolls[0] == null)
            return true;
        for (Faces face : rolls) {
            if (face == Faces.MONKEY || face == Faces.PARROT)
                monkeysAndParrots++;
            if (face == Faces.SKULL)
                skulls++;

        }
        if (skulls >= 2) // keep rerolling until 2 skulls obtained to be safe
            return false;
        if (monkeysAndParrots + skulls == 8) // stop rolling if max combo hit
            return false;
        return true;
    }

    @Override
    public int countPoints(Faces[] rolls, Card card) {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };
        int monkeysAndParrots;
        int score = 0;

        for (Faces face : rolls) { // count all faces rolled
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return 0;
        monkeysAndParrots = count[Faces.MONKEY.ordinal()] + count[Faces.PARROT.ordinal()];
        score += nOfAKindScores[monkeysAndParrots];
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue;
            if (face == Faces.MONKEY || face == Faces.PARROT)
                continue;
            score += nOfAKindScores[count[face.ordinal()]];
        }
        score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100;
        return score;
    }

}