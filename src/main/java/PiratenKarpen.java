import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling eight dices");
        Dice myDice = new Dice();
        Faces[] rolls = myDice.rollEight();
        for (Faces face : rolls)
            System.out.println(face);
        System.out.println("That's all folks!");
    }
}