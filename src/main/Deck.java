package main;

import java.util.ArrayList;

public class Deck {
    private final int deckId;
    private final ArrayList<Integer> cards = new ArrayList<>();

    public Deck() {
        deckId = DeckGenerator.getId();
    }

    public void addCard(int n) {
        cards.add(n);
    }

    public int getDeckId() {
        return deckId;
    }

    public ArrayList<Integer> get_cards() {
        return this.cards;
    }

    public String toString() {
        String cards = "";
        for (Integer i: this.cards) {
            cards = cards + i + " ";
        }
        return cards;
    }
}
