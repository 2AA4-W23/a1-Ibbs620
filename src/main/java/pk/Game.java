package pk;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pk.cards.Deck;
import pk.strategy.ComboStrategy;
import pk.strategy.Strategy;

public class Game {
    private Player[] players;
    private int numberOfDice;
    private boolean trace = false;
    private static final Logger logger = LogManager.getRootLogger();
    private Deck deck = new Deck();
    private List<Strategy> playerStrategies;

    public Game(int numberOfPlayers, int numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.players = new Player[numberOfPlayers];
        this.initializePlayers();
        // if no strats provided, set to default
    }

    public Game(int numberOfPlayers, int numberOfDice, List<Strategy> playerStrategies) {
        this.numberOfDice = numberOfDice;
        this.players = new Player[numberOfPlayers];
        this.playerStrategies = playerStrategies;
        this.initializePlayers(playerStrategies);
        // if player strats provided in constructor, initialize with the strats

    }

    public void setTrace(boolean t) { // sets trace for logging
        this.trace = t;
    }

    private void initializePlayers(List<Strategy> playerStrategies) { // initialize players with provided strategies
        for (int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(this.numberOfDice, playerStrategies.get(i), i + 1);
        }
    }

    private void initializePlayers() { // initialize players to default strategies
        List<Strategy> playerStrategies = new ArrayList<>();
        for (int i = 0; i < this.players.length; i++) {
            Strategy s = new ComboStrategy();
            playerStrategies.add(s);
        }
        this.playerStrategies = playerStrategies;
        this.initializePlayers(playerStrategies);
    }

    public Player playGame() { // plays a full game of piraten karpen
        boolean lastRound = false;
        if (trace) {
            logger.info("STARTING GAME");
        }
        Player winner = new Player(0, null, 0);
        Player firstTo6000 = new Player(0, null, 0);
        // initialize a winner and first player to 6000 points for comparison to
        // existing players

        while (true) {
            for (Player player : this.players) { // loop players
                if (firstTo6000.playerNumber == player.playerNumber) {
                    // executes once a player with 6000 points exists and its their turn
                    // end game if round comes back to player that hit 6000 points first
                    if (trace && winner.playerNumber != 0) {
                        logger.info("PLAYER " + winner.playerNumber + " WINS");
                    }
                    if (trace && winner.playerNumber == 0) {
                        logger.info("GAME IS A TIE");
                    }
                    return winner;
                }

                if (trace) {
                    logger.info("PLAYER " + player.playerNumber + " TURN BEGINS");
                }

                player.startTurn(trace, deck);

                if (lastRound) {
                    // set winners based on highest points
                    if (player.getPoints() > winner.getPoints()) {
                        winner = player;
                    } else if (player.getPoints() == winner.getPoints()) {
                        // tie if multiple players with the most points
                        winner = new Player(0, null, 0);
                    }
                }
                if (player.getPoints() >= 6000 && !lastRound) {
                    // determine if its the last round
                    lastRound = true;
                    firstTo6000 = player;
                    winner = player;
                }
            }
        }
    }

    public void resetGame() { // resets all values
        this.players = new Player[this.players.length];
        this.initializePlayers(playerStrategies);
        this.deck = new Deck();
    }
}