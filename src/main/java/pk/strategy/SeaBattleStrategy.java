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
    public void printStrategy() {
        // TODO Auto-generated method stub

    }

}
