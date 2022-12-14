package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Deck class.
 * This class generates a Deck, which has its own unique ID.
 * There's an ArrayList of cards to add and remove items in the cards list
 * 
 * @author Joshua Adebayo and Sebastian Dixon
 */
public class Deck {
    private int deckId;
    private ArrayList<Integer> cards = new ArrayList<>();
    public ArrayList<String> logOutput = new ArrayList<>();
    private File deck_file;
    public ReentrantLock lock;

    /**
     * This is a constructor for a Deck.
     * deckID is set in the DeckGenerator class and is assigned in this
     * constructor
     */
    public Deck() {
        this.deckId = DeckGenerator.getId();
        this.deck_file = new File("deck" + deckId + "_output.txt");
        this.lock = new ReentrantLock();
    }

    /**
     * This is a method to add items to the ArrayList cards
     *
     * @param n The number of cards being added, this must be an integer
     */
    public void addCard(int n) {
        this.cards.add(n);
    }

    /**
     * Method used to remove a card from the cards ArrayList
     *
     * @param n The number card in the deck that is being removed
     */
    public void removeCard(int n) {
        for (int i = 0; i < cards.size(); i++) {
            if (n == cards.get(i)) {
                cards.remove(i);
            }
        }
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
     * This is a method to retrieve the cards within a deck
     * 
     * @return The ArrayList of cards that's in a deck
     */
    public ArrayList<Integer> get_cards() {
        return this.cards;
    }

    /**
     * Method used to log which deck a card is in
     */
    public void writeFile() {
        logOutput.add("Cards in deck: " + this);

        try {
            PrintStream out = new PrintStream(deck_file);
            for (String s : logOutput) {
                out.println(s);
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ToString to output the important data within a class,
     *
     * @return All the cards are returned but as strings
     */

    @Override
    public String toString() {
        // This proccess allows us to output the cards as a string
        String cards = "";
        for (Integer i : this.cards) {
            cards = cards + i + " "; // Put parenthases around each integer in the cards ArrayList
        }
        return cards;
    }
}
