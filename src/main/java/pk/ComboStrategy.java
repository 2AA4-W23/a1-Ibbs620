package pk;

public class ComboStrategy implements Strategy {
    public boolean[] selectReroll(Faces[] rolls) {
        boolean[] reroll = new boolean[8];
        int[] count = new int[6];
        for (Faces face : rolls) { // count all diamonds and gold rolled for 100 points each
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] == 2) // dont roll if 2 skulls rolled to avoid risk
            return reroll;
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == Faces.SKULL || rolls[i] == Faces.DIAMOND || rolls[i] == Faces.GOLD)
                continue; // dont reroll skulls, diamonds, and gold
            if (count[rolls[i].ordinal()] == 1)
                reroll[i] = true;
        }
        return reroll;
    }
}