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
            } else if (r.nextInt(2) == 1)
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
    public void printStrategy() {
        System.out.println("Random Strategy");
    }

}