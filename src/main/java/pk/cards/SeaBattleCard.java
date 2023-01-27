package pk.cards;

public class SeaBattleCard extends Card {
    public final int swords;
    public final int bonus;

    public SeaBattleCard(int swords, int bonus) {
        this.swords = swords;
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "SEA BATTLE: " + this.swords + " SWORDS, " + this.bonus + " BONUS";
    }
}
