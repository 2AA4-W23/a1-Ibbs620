package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private Player[] players;
    private int numberOfDice;
    private boolean trace;
    private static final Logger logger = LogManager.getRootLogger();
    private Deck deck = new Deck();

    public Game(int numberOfPlayers, int numberOfDice, boolean trace) {
        this.numberOfDice = numberOfDice;
        this.trace = trace;
        this.players = new Player[numberOfPlayers];
        this.initializePlayers();
        deck.shuffle();
    }

    public Game(int numberOfPlayers, int numberOfDice) {
        this(numberOfPlayers, numberOfDice, false);
    }

    public void setTrace(boolean t) {
        this.trace = t;
    }

    private void initializePlayers() {
        int playerNum = 1;
        for (int i = 0; i < this.players.length; i++) {
            Strategy strategy = new RandomStrategy();
            this.players[i] = new Player(this.numberOfDice, strategy, playerNum);
            playerNum++;
        }
    }

    public void setGameStrategy(Strategy strategy) {
        for (Player player : this.players) {
            player = new Player(this.numberOfDice, strategy, player.playerNumber);
        }
    }

    public void setPlayerStrategy(int playerNumber, Strategy strategy) {
        this.players[playerNumber] = new Player(this.numberOfDice, strategy, playerNumber);
    }

    public Player playGame() {
        if (trace) {
            logger.info("STARTING GAME");
        }
        Player winner = new Player(0, null, 0);
        for (Player player : this.players) {
            if (trace) {
                logger.info("PLAYER " + player.playerNumber + " TURN BEGINS");
            }
            player.startTurn(trace, deck);
            if (player.countPoints() > winner.countPoints()) {
                winner = player;
            } else if (player.countPoints() == winner.countPoints()) {
                winner = new Player(0, null, 0);
            }
        }
        if (trace && winner.playerNumber != 0) {
            logger.info("PLAYER " + winner.playerNumber + " WINS");
        }
        if (trace && winner.playerNumber == 0) {
            logger.info("GAME IS A TIE");
        }
        return winner;
    }

    public void resetGame() {
        this.players = new Player[this.players.length];
        this.initializePlayers();
        this.deck = new Deck();
    }
}