package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pk.cards.Card;
import pk.cards.Deck;
import pk.cards.MonkeyBusinessCard;
import pk.cards.SeaBattleCard;
import pk.dice.Dice;
import pk.dice.Faces;
import pk.strategy.MonkeyBusinessStrategy;
import pk.strategy.SeaBattleStrategy;
import pk.strategy.Strategy;

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

    public void reRoll(Strategy turnStrategy) {
        // this.strategy.printStrategy(); //debug
        if (!turnStrategy.canRollAgain(this.rolledDice, this.card)) {
            // dont roll if conditions for rerolling are not met
            return;
        }
        boolean[] diceToRoll = turnStrategy.selectReroll(this.rolledDice);
        if (this.rolledDice[0] == null) { // No dice rolled yet, so roll all eight
            rolledDice = Dice.rollN(totalDice);
        } else { // reroll specific dice. determined by strategy
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

    private Strategy selectStrategy(Card card) {
        if (card instanceof SeaBattleCard)
            return new SeaBattleStrategy();
        if (card instanceof MonkeyBusinessCard)
            return new MonkeyBusinessStrategy();
        return this.strategy;
    }

    public void startTurn(boolean trace, Deck deck) { // carry out a turn by rerolling until 3 skulls obtained or
        if (trace)
            logger.info("FIRST 5 CARDS IN DECK: " + deck.peekDeck(5));
        this.drawCard(deck);
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " DRAWS " + this.card.toString());

        Strategy turnStrategy = selectStrategy(this.card);

        while (turnStrategy.canRollAgain(this.rolledDice, this.card)) {
            this.reRoll(turnStrategy);
            if (trace)
                logger.info(this.getRolls());
        }
        this.score += turnStrategy.countPoints(this.rolledDice, this.card);
        this.returnCard(deck);
        this.resetRolls();
        if (trace)
            logger.info("PLAYER " + this.playerNumber + " HAS " + this.getPoints() + " POINTS");
    }
}