package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {

    private int totalDice = 0;
    private Faces[] rolledDice;
    private Strategy strategy;
    public final int playerNumber;
    private int score = 0;
    private static final Logger logger = LogManager.getRootLogger();
    private Card card;

    public Player(int totalDice, Strategy strategy, int playerNumber) {
        this.totalDice = totalDice;
        this.strategy = strategy;
        this.rolledDice = new Faces[totalDice];
        this.playerNumber = playerNumber;
    }

    public void reRoll(boolean[] diceToRoll) {
        // this.strategy.printStrategy(); //debug
        if (!this.strategy.canRollAgain(this.rolledDice)) { // dont roll if conditions for rerolling are not met
            return;
        }

        if (this.rolledDice[0] == null) { // No dice rolled yet, so roll all eight
            rolledDice = Dice.rollN(totalDice);
        } else { // reroll specific dice
            for (int i = 0; i < this.totalDice; i++) {
                if (diceToRoll[i])
                    this.rolledDice[i] = Dice.roll();
            }
        }
    }

    public void drawCard(Deck deck) {
        this.card = deck.draw();
    }

    public void returnCard(Deck deck) {
        if (this.card == null)
            return;
        deck.putBack(this.card);
        this.card = null;
    }

    private void updatePoints() {
        int[] count = new int[6];
        int[] nOfAKindScores = { 0, 0, 0, 100, 200, 500, 1000, 2000, 4000 };

        for (Faces face : this.rolledDice) { // count all faces rolled
            if (face != null)
                count[face.ordinal()]++;
        }
        if (count[Faces.SKULL.ordinal()] >= 3) // no points scored if 3 skulls rolled
            return;
        for (Faces face : Faces.values()) {
            if (face == Faces.SKULL)
                continue;
            this.score += nOfAKindScores[count[face.ordinal()]];
        }
        this.score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100;
    }

    public int getPoints() {
        return this.score;
    }

    public String getRolls() { // get a string containing dice information for logging
        String rolls = "";
        for (Faces face : this.rolledDice) {
            rolls += face + " ";
        }
        return rolls;
    }

    public void resetRolls() {
        this.rolledDice = new Faces[this.rolledDice.length];
    }

    public void startTurn(boolean trace, Deck deck) { // carry out a turn by rerolling until 3 skulls obtained or
        this.drawCard(deck);
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " DRAWS " + this.card.face);
        this.returnCard(deck);
        while (this.strategy.canRollAgain(this.rolledDice)) {
            this.reRoll(this.strategy.selectReroll(this.rolledDice));
            if (trace)
                logger.info(this.getRolls());
        }
        this.updatePoints();
        this.resetRolls();
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " HAS " + this.getPoints() + " POINTS");
    }
}