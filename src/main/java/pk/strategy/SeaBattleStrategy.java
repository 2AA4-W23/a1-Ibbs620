package pk.strategy;

import pk.cards.Card;
import pk.cards.SeaBattleCard;
import pk.dice.Faces;

public class SeaBattleStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        boolean reroll[] = new boolean[rolls.length];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == Faces.SABER) { // dont reroll sabers
                reroll[i] = false;
            } else if (rolls[i] == Faces.SKULL) { // cant reroll skulls
                reroll[i] = false;
            } else {
                reroll[i] = true; // reroll anything else
            }
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int sabers = 0;
        int skulls = 0;
        for (int i = 0; i < rolls.length; i++) { // count all sabers and skulls rolled
            if (rolls[i] == Faces.SABER)
                sabers++;
            else if (rolls[i] == Faces.SKULL)
                skulls++;
        }
        if (skulls >= 3) // cant reroll if 3 or more skulls
            return false;
        if (((SeaBattleCard) card).swords > sabers) // keep rolling if bonus not hit
            return true;
        return false;
    }

    @Override
    public int countPoints(Faces[] rolls, Card card) {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };
        // n of a kind scoring where index is number of occurences of a given face
        int score = 0;
        int sabers = 0;

        for (Faces face : rolls) { // count all faces
            if (face != null)
                count[face.ordinal()]++;
            if (face == Faces.SABER) // count sabers
                sabers++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return 0;
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue; // dont score skulls
            score += nOfAKindScores[count[face.ordinal()]]; // add n of a kind score
        }
        score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100; // gold and diamond scoring
        if (((SeaBattleCard) card).swords <= sabers)
            score += ((SeaBattleCard) card).bonus; // add bonus if number of swords hit
        return score;
    }

}