package pk;

import java.util.Random;

public class RandomStrategy implements Strategy {

    @Override
    public boolean[] selectReroll(Faces[] rolls) {
        Random r = new Random();
        boolean[] reroll = new boolean[8];
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

}