package pk.strategy;

import pk.cards.Card;
import pk.cards.SeaBattleCard;
import pk.dice.Faces;

public class SeaBattleStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        boolean reroll[] = new boolean[rolls.length];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == Faces.SABER) {
                reroll[i] = false;
            } else if (rolls[i] == Faces.SKULL) {
                reroll[i] = false;
            } else {
                reroll[i] = true;
            }
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int sabers = 0;
        int skulls = 0;
        for (int i = 0; i < rolls.length; i++) { // count all faces rolled
            if (rolls[i] == Faces.SABER)
                sabers++;
            else if (rolls[i] == Faces.SKULL)
                skulls++;
        }
        if (skulls >= 3) // cant reroll if 3 or more skulls
            return false;
        if (((SeaBattleCard) card).swords > sabers)
            return true;
        return false;
    }

    @Override
    public int countPoints(Faces[] rolls, Card card) {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };
        int score = 0;
        int sabers = 0;

        for (Faces face : rolls) { // count all faces rolled
            if (face != null)
                count[face.ordinal()]++;
            if (face == Faces.SABER)
                sabers++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return 0;
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue;
            score += nOfAKindScores[count[face.ordinal()]];
        }
        score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100;
        if (((SeaBattleCard) card).swords <= sabers)
            score += ((SeaBattleCard) card).bonus;
        return score;
    }

}