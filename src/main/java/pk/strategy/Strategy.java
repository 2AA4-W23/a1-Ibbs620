package pk.strategy;

import pk.dice.Faces;

public interface Strategy {
    public boolean[] selectReroll(Faces[] rolls);

    public boolean canRollAgain(Faces[] rolls);

    public void printStrategy();
}