package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pk.cards.Card;
import pk.cards.CardFaces;
import pk.cards.Deck;
import pk.cards.SeaBattleCard;
import pk.dice.Dice;
import pk.dice.Faces;
import pk.strategy.Strategy;

public class Player {

    private int totalDice = 0;
    private Faces[] rolledDice;
    private Strategy strategy;
    public final int playerNumber;
    private int score = 0;
    private static final Logger logger = LogManager.getRootLogger();
    private Card card;
    private int bonus = 0;

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

    public void calculateSeaBattleBonus(SeaBattleCard card) {
        int sabers = 0;
        for (Faces face : this.rolledDice) { // count all faces rolled
            if (face == Faces.SABER)
                sabers++;
        }
        if (sabers >= card.swords)
            this.bonus = card.bonus;
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
        this.score += (count[Faces.DIAMOND.ordinal()] + count[Faces.GOLD.ordinal()]) * 100 + this.bonus;
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
        this.bonus = 0;
    }

    public void startTurn(boolean trace, Deck deck) { // carry out a turn by rerolling until 3 skulls obtained or
        this.drawCard(deck);
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " DRAWS " + this.card.face);

        while (this.strategy.canRollAgain(this.rolledDice)) {
            this.reRoll(this.strategy.selectReroll(this.rolledDice));
            if (this.card.face == CardFaces.SEABATTLE) {
                if (trace)
                    logger.info("COMMENCING SEA BATTLE - "
                            + ((SeaBattleCard) this.card).swords + " SWORDS NEEDED FOR BONUS");
                calculateSeaBattleBonus((SeaBattleCard) this.card);
            }
            if (trace)
                logger.info(this.getRolls());
        }
        this.updatePoints();
        if (this.bonus != 0 && trace)
            logger.info("BONUS OF " + this.bonus + " AWARDED");
        this.returnCard(deck);
        this.resetRolls();
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " HAS " + this.getPoints() + " POINTS");
    }
}