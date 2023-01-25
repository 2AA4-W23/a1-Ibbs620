package pk.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private int cards;

    public Deck() {
        this.cards = 35;
        this.initializeDeck();
    }

    private void initializeDeck() {
        // sea battle cards
        this.addCards(new SeaBattleCard(2, 300), 2);
        this.addCards(new SeaBattleCard(3, 500), 2);
        this.addCards(new SeaBattleCard(4, 1000), 2);
        // filler cards (does nothing)
        this.addCards(new Card(), 29);
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
        if (card == null)
            return;
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
