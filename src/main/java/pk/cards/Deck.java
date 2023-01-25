package pk.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private int cards;

    public Deck() {
        deck = new ArrayList<>();
        this.addCards(new Card(CardFaces.NOP), 29);
        this.addCards(new Card(CardFaces.SEABATTLE), 6);
        this.cards = 35;
    }

    private void addCards(Card card, int n) {
        for (int i = 0; i < n; i++)
            deck.add(card);
    }

    public Card draw() {
        Card drawn = deck.remove(cards - 1);
        cards--;
        return drawn;
    }

    public void putBack(Card card) {
        deck.add(0, card);
        cards++;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public int size() {
        return this.cards;
    }
}
