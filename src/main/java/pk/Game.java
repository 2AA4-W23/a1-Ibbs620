package pk;

public class Game {
    private Player[] players;
    private int numberOfDice;
    private boolean trace;

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
        Player winner = new Player(0, null, 0);
        for (Player player : this.players) {
            player.startTurn();
            System.out.println("Player " + player.playerNumber + " Points: " + player.countPoints());
            if (player.countPoints() > winner.countPoints()) {
                winner = player;
            } else if (player.countPoints() == winner.countPoints()) {
                winner = new Player(0, null, 0);
            }
        }
        return winner;
    }
}
