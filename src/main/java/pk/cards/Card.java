package pk.cards;

public class Card {

    public final CardFaces face;

    public Card(CardFaces face) {
        this.face = face;
    }

    public Card() {
        this(CardFaces.NOP);
    }
}
