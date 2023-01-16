package pk;

import java.util.Arrays;
import java.util.Random;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        System.out.println("  (DEBUG) there are " + howManyFaces + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public Faces[] rollEight() {
        int howManyFaces = Faces.values().length;
        Faces[] rolls = new Faces[8];
        Random bag = new Random();
        for (int i = 0; i < 8; i++) {
            int roll = bag.nextInt(howManyFaces);
            rolls[i] = Faces.values()[roll];
        }
        return rolls;
    }

}