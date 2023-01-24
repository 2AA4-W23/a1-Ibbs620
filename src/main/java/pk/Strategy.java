package pk;

public interface Strategy {
    public boolean[] selectReroll(Faces[] rolls);

    public boolean canRollAgain(Faces[] rolls);

    public void printStrategy();
}