package pk;

import java.util.Arrays;
import java.util.Random;

public class Dice {

    public static Faces roll() {
        int howManyFaces = Faces.values().length;
        // System.out.println(" (DEBUG) there are " + howManyFaces + " faces");
        // System.out.println(" (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public static Faces[] rollEight() {
        int howManyFaces = Faces.values().length;
        Faces[] rolls = new Faces[8];
        Random bag = new Random();
        for (int i = 0; i < 8; i++) {
            int roll = bag.nextInt(howManyFaces);
            rolls[i] = Faces.values()[roll];
        }
        return rolls;
    }

    public static Faces[] rollN(int n) {
        int howManyFaces = Faces.values().length;
        Faces[] rolls = new Faces[n];
        Random bag = new Random();
        for (int i = 0; i < n; i++) {
            int roll = bag.nextInt(howManyFaces);
            rolls[i] = Faces.values()[roll];
        }
        return rolls;
    }
}