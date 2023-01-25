package pk.cards;

public class SeaBattleCard extends Card {
    public final int swords;
    public final int bonus;

    public SeaBattleCard(int swords, int bonus) {
        super(CardFaces.SEABATTLE);
        this.swords = swords;
        this.bonus = bonus;
    }
}
