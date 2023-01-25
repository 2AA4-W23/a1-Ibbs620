package pk;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        deck.shuffle();
    }

    public Game(int numberOfPlayers, int numberOfDice, List<Strategy> playerStrategies) {
        this.numberOfDice = numberOfDice;
        this.players = new Player[numberOfPlayers];
        this.playerStrategies = playerStrategies;
        this.initializePlayers(playerStrategies);
        deck.shuffle();

    }

    public void setTrace(boolean t) {
        this.trace = t;
    }

    private void initializePlayers(List<Strategy> playerStrategies) {
        for (int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(this.numberOfDice, playerStrategies.get(i), i + 1);
        }
    }

    private void initializePlayers() {
        List<Strategy> playerStrategies = new ArrayList<>();
        for (int i = 0; i < this.players.length; i++) {
            Strategy s = new ComboStrategy();
            playerStrategies.add(s);
        }
        this.playerStrategies = playerStrategies;
        this.initializePlayers(playerStrategies);
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
        boolean lastRound = false;
        if (trace) {
            logger.info("STARTING GAME");
        }
        Player winner = new Player(0, null, 0);
        Player firstTo6000 = new Player(0, null, 0);
        while (true) {
            for (Player player : this.players) {
                if (firstTo6000.playerNumber == player.playerNumber) {
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
                    if (player.getPoints() > winner.getPoints()) {
                        winner = player;
                    } else if (player.getPoints() == winner.getPoints()) {
                        winner = new Player(0, null, 0);
                    }
                }
                if (player.getPoints() >= 6000 && !lastRound) {
                    lastRound = true;
                    firstTo6000 = player;
                    winner = player;
                }
            }
        }
    }

    public void resetGame() {
        this.players = new Player[this.players.length];
        this.initializePlayers(playerStrategies);
        this.deck = new Deck();
    }
}