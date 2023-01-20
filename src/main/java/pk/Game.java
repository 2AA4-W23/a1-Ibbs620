package pk;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players;
    private int numberOfDice;
    private boolean trace;
    private List<String> logMessages = new ArrayList<>();

    public Game(int numberOfPlayers, int numberOfDice, boolean trace) {
        this.numberOfDice = numberOfDice;
        this.trace = trace;
        this.players = new Player[numberOfPlayers];
        this.initializePlayers();
    }

    public Game(int numberOfPlayers, int numberOfDice) {
        this(numberOfPlayers, numberOfDice, false);
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
            player.setStrategy(strategy);
        }
    }

    public Player playGame() {
        if (trace) {
            logMessages.add("STARTING GAME");
        }
        Player winner = new Player(0, null, 0);
        for (Player player : this.players) {
            if (trace) {
                logMessages.add("PLAYER " + player.playerNumber + " TURN BEGINS");
            }
            List<String> turnSummary = player.startTurn();
            if (trace) {
                logMessages.addAll(turnSummary);
            }
            if (player.countPoints() > winner.countPoints()) {
                winner = player;
            } else if (player.countPoints() == winner.countPoints()) {
                winner = new Player(0, null, 0);
            }
        }
        if (trace && winner.playerNumber != 0) {
            logMessages.add("PLAYER " + winner.playerNumber + "WINS");
        }
        if (trace && winner.playerNumber == 0) {
            logMessages.add("GAME IS A TIE");
        }
        return winner;
    }

    public List<String> getLogMessages() {
        return this.logMessages;
    }
}
