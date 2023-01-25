package pk;

import java.util.ArrayList;
import java.util.List;

public class ArgumentHandler {
    public static Game getGame(String[] args) {
        Game game;
        if (args.length <= 1) {
            game = new Game(2, 8);
        } else if (args.length == 2) {
            Strategy s;
            List<Strategy> playerStrategies = new ArrayList<>();
            if (args[1].toLowerCase().equals("combo")) {
                s = new ComboStrategy();
                playerStrategies.add(s);
                playerStrategies.add(s);
            } else if (args[1].toLowerCase().equals("random")) {
                s = new RandomStrategy();
                playerStrategies.add(s);
                playerStrategies.add(s);
            } else {
                System.out
                        .println("Strategy " + args[1] + " not found, players will be set to combo.");
                s = new ComboStrategy();
                playerStrategies.add(s);
                playerStrategies.add(s);
            }
            game = new Game(2, 8, playerStrategies);
        } else if (args.length >= 3) {
            List<Strategy> playerStrategies = new ArrayList<>();
            for (int j = 1; j < args.length; j++) {
                if (args[j].toLowerCase().equals("combo")) {
                    Strategy s = new ComboStrategy();
                    playerStrategies.add(s);
                } else if (args[j].toLowerCase().equals("random")) {
                    Strategy s = new RandomStrategy();
                    playerStrategies.add(s);
                } else {
                    System.out
                            .println("Strategy " + args[j] + " not found, player " + j + " will be set to random.");
                    Strategy s = new RandomStrategy();
                    playerStrategies.add(s);
                }
            }
            game = new Game(2, 8, playerStrategies);
        } else {
            game = new Game(2, 8);
        }

        if (args.length > 0) {// handles command line arguments. If none provided, default to no tracing
            boolean trace = args[0].equals("trace") || args[0].equals("t");
            game.setTrace(trace);
        }
        return game;
    }
}