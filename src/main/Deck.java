package main;

import java.util.ArrayList;

/**
 * Deck class.
 * This class generates a Deck, which has its own unique ID.
 * There's an ArrayList of cards to add and remove items in the cards list
 * 
 * @author Joshua Adebayo and Sebastian Dixon
 */
public class Deck {
    private final int deckId; // Final so it can't be changed ever

    // Using an ArrayList so we can add and remove items from the list
    private final ArrayList<Integer> cards = new ArrayList<>();

    /**
     * This is a constructor for a Deck.
     * deckID is set in the DeckGenerator class and is assigned in this
     * constructor
     */

    public Deck() {
        deckId = DeckGenerator.getId();
    }

    /**
     * This is a method to add items to the ArrayList cards
     * 
     * @param n The number of cards being added, this must be an integer
     */
    public void addCard(int n) {
        cards.add(n);
    }

    /**
     * This is a method to get a decks ID
     * 
     * @return A decks unique ID
     */

    public int getDeckId() {
        return deckId;
    }

    /**
     * This is a method to retreive the cards within a deck
     * 
     * @return The ArrayList of cards that's in a deck
     */

    public ArrayList<Integer> get_cards() {
        return this.cards;
    }

    /**
     * ToString to output the imporant data within a class,
     * 
     * @return All the cards are returned but as strings
     */

    public String toString() {
        // This proccess allows us to output the cards as a string
        String cards = "";
        for (Integer i : this.cards) {
            cards = cards + i + " "; // Put parenthases around each integer in the cards ArrayList
        }
        return cards;
    }
}
