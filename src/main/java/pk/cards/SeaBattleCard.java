package pk.cards;

public class SeaBattleCard extends Card {
    public final int swords;
    public final int bonus;

    // class for sea battle card

    public SeaBattleCard(int swords, int bonus) { // set swords needed and bonus of the card
        this.swords = swords;
        this.bonus = bonus;
    }

    @Override
    public String toString() { // for logging
        return "SEA BATTLE: " + this.swords + " SWORDS, " + this.bonus + " BONUS";
    }
}