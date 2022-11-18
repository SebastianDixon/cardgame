package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

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
    private File outputFile;
    public ArrayList<String> logOutput = new ArrayList<>();
    private File deck_file;


    /**
     * This is a constructor for a Deck.
     * deckID is set in the DeckGenerator class and is assigned in this
     * constructor
     */

    public Deck() {
        this.deckId = DeckGenerator.getId();
        this.outputFile = new File("deck" + deckId + "_output.txt");
    }

    /**
     * This is a method to add items to the ArrayList cards
     * 
     * @param n The number of cards being added, this must be an integer
     */
    public void addCard(int n) {
        this.cards.add(n);
    }

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
     * ToString to output the imporant data within a class,
     * 
     * @return All the cards are returned but as strings
     */

    public void writeFile() {
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


    public String toString() {
        // This proccess allows us to output the cards as a string
        String cards = "";
        for (Integer i : this.cards) {
            cards = cards + i + " "; // Put parenthases around each integer in the cards ArrayList
        }
        return cards;
    }
}
