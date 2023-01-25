package pk.dice;

import java.util.Random;

public class Dice {

    public static Faces roll() {
        int howManyFaces = Faces.values().length;
        // System.out.println(" (DEBUG) there are " + howManyFaces + " faces");
        // System.out.println(" (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random(System.nanoTime());
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public static Faces[] rollN(int n) {
        Faces[] rolls = new Faces[n];
        for (int i = 0; i < n; i++) {
            rolls[i] = roll();
        }
        return rolls;
    }
}