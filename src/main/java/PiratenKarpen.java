import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling eight dices");
        Faces[] rolls = Dice.rollEight();
        for (Faces face : rolls) {
            System.out.println(face);
            if (face == Faces.SKULL) {
                face = null;
            }
        }
        System.out.println("That's all folks!");
    }
}