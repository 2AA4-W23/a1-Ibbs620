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

    private void initializeDeck() { // add all cards to deck
        // sea battle cards
        this.addCards(new SeaBattleCard(2, 300), 2);
        this.addCards(new SeaBattleCard(3, 500), 2);
        this.addCards(new SeaBattleCard(4, 1000), 2);
        // monkey business cards
        this.addCards(new MonkeyBusinessCard(), 4);
        // sorceress cards
        this.addCards(new SorceressCard(), 4);
        // filler cards (does nothing)
        this.addCards(new Card(), 29);
        this.shuffle(); // shuffles deck
    }

    private void addCards(Card card, int n) { // adds n cards to top of deck
        for (int i = 0; i < n; i++)
            deck.add(card);
    }

    public Card draw() {
        Card drawn = deck.remove(cards - 1);
        cards--;
        return drawn;
    }

    public void putBack(Card card) { // return card to bottom of deck
        if (card == null) // dont add null entries
            return;
        deck.add(0, card); // add to beginning of arraylist (bottom of deck)
        cards++;
    }

    public void shuffle() { // shuffles deck
        Collections.shuffle(deck);
    }

    public String peekDeck(int n) { // shows the first 5 cards in deck for debug
        String contents = "";
        for (int i = 1; i <= n; i++) { // build a string containing the string representations of the cards
            contents += deck.get(cards - i).toString() + " | ";
        }
        return contents;
    }
}