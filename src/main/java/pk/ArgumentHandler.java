package pk;

public class ArgumentHandler {
    public static Game getGame(String[] args) {
        Game game = new Game(2, 8, false);
        if (args.length == 0) {// handles command line arguments. If none provided, default to no tracing
        } else if (args.length == 1) {
            boolean trace = args[0].equals("trace") || args[0].equals("t");
            game.setTrace(trace);
        } else if (args.length == 2) {
            boolean trace = args[0].equals("trace") || args[0].equals("t");
            game.setTrace(trace);
            if (args[1].toLowerCase().equals("combo")) {
                Strategy s = new ComboStrategy();
                game.setGameStrategy(s);
            } else if (args[1].toLowerCase().equals("random")) {
            } // players are set to random by default so no action needed
            else
                System.out.println("Strategy " + args[1] + " not found, all players will be set to random.");
        } else if (args.length >= 3) {
            boolean trace = args[0].equals("trace") || args[0].equals("t");
            game.setTrace(trace);
            for (int j = 1; j < args.length; j++) {
                if (args[j].toLowerCase().equals("combo")) {
                    Strategy s = new ComboStrategy();
                    game.setPlayerStrategy(j - 1, s);
                } else if (args[j].toLowerCase().equals("random")) {
                } // players are set to random by default so no action needed
                else
                    System.out
                            .println("Strategy " + args[j] + " not found, player " + j + " will be set to random.");
            }
        } else
            game = new Game(2, 8, false);
        return game;
    }
}