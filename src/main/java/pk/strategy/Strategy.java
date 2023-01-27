package pk.strategy;

import pk.cards.Card;
import pk.dice.Faces;

public interface Strategy {
    public boolean[] selectReroll(Faces[] rolls);

    public boolean canRollAgain(Faces[] rolls, Card card);

    public int countPoints(Faces[] rolls, Card card);
}