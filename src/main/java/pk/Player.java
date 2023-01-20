package pk;

public class Player {

    private int totalDice = 0;
    private Faces[] rolledDice;
    private int skullsRolled = 0;
    private Strategy strategy;
    public final int playerNumber;

    public Player(int totalDice, Strategy strategy, int playerNumber) {
        this.totalDice = totalDice;
        this.strategy = strategy;
        this.rolledDice = new Faces[totalDice];
        this.playerNumber = playerNumber;
    }

    public void reRoll(boolean[] diceToRoll) {
        if (!canRollAgain())
            return;

        if (this.rolledDice[0] == null) { // No dice rolled yet, so roll all eight
            rolledDice = Dice.rollN(totalDice);
        }

        else { // reroll specific dice
            for (int i = 0; i < this.totalDice; i++) {
                if (diceToRoll[i])
                    this.rolledDice[i] = Dice.roll();
                if (rolledDice[i] == Faces.SKULL)
                    skullsRolled++;
            }
            System.out.print("Rerolling dice "); // print rerolled dice for debug
            for (int i = 0; i < diceToRoll.length; i++) {
                if (diceToRoll[i])
                    System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

    public int countPoints() {
        int score = 0;
        for (Faces face : this.rolledDice) {
            if (face == Faces.DIAMOND || face == Faces.GOLD)
                score += 100;
        }
        return score;
    }

    public boolean canRollAgain() {
        return this.skullsRolled < 3;
    }

    public void printRolls() {
        System.out.print("Rolls: ");
        for (Faces face : this.rolledDice) {
            System.out.print(face + " ");
        }
        System.out.println();
    }

    public void startTurn() {
        while (this.canRollAgain()) {
            this.reRoll(this.strategy.selectReroll(this.rolledDice));
            this.printRolls();
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
