package pk.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck = new ArrayList<>();
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
        // monkey business cards
        this.addCards(new MonkeyBusinessCard(), 4);
        // filler cards (does nothing)
        this.addCards(new Card(), 29);
        this.shuffle();
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

    public String peekDeck(int n) {
        String contents = "";
        for (int i = 1; i <= n; i++) {
            contents += deck.get(cards - i).toString() + " | ";
        }
        return contents;
    }
}