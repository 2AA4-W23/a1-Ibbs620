package pk;

public class Card {

    public final CardFaces face;
    public final int value;

    public Card(CardFaces face, int value) {
        this.face = face;
        this.value = value;
    }

    public Card(CardFaces face) {
        this(face, 0);
    }
}
