package pk.strategy;

import pk.cards.Card;
import pk.dice.Faces;

public class ComboStrategy implements Strategy { // implement the strategy interface

    @Override
    public boolean[] selectReroll(Faces[] rolls) {// returns a list of which dice to reroll
        boolean[] reroll = new boolean[rolls.length];
        int[] count = new int[6];
        for (Faces face : rolls) { // count all occurences of dice faces
            if (face != null)
                count[face.ordinal()]++;
        }
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == null) { // if rolls[i] is null, dice hasnt been rolled yet so always reroll
                reroll[i] = true;
                continue;
            } else if (rolls[i] == Faces.SKULL || rolls[i] == Faces.DIAMOND || rolls[i] == Faces.GOLD)
                reroll[i] = false; // dont reroll skulls, diamonds, and gold
            else if (count[rolls[i].ordinal()] == 1) // reroll anything with 1 occurence (doesnt contribute to a combo)
                reroll[i] = true;
        }
        return reroll;
    }

    @Override
    public boolean canRollAgain(Faces[] rolls, Card card) {
        int[] count = new int[6];
        if (rolls[0] == null) // dice havent been rolled yet, so reroll.
            return true;
        for (Faces face : rolls) { // count all face occurences
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 2) // dont reroll if 2 or more skulls
            return false; // even though you can reroll with 2 skulls in the rules, avoid it
        for (Faces face : rolls) {
            if (face == Faces.SKULL || face == Faces.DIAMOND || face == Faces.GOLD)
                continue; // dont process these faces
            if (count[face.ordinal()] == 1)
                return true; // reroll if dice that dont contribute to a combo exist
        }
        return false; // dont reroll by default
    }

    @Override
    public int countPoints(Faces[] rolls, Card card) {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };
        // scores for n of a kind combos where the index is how many of that face was
        // rolled
        int score = 0;

        for (Faces face : rolls) { // count all faces rolled
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return 0;
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue; // cant score with skulls
            score += nOfAKindScores[count[face.ordinal()]]; // add n of a kind score
        }
        score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100; // add diamonds + gold score
        return score;
    }
}