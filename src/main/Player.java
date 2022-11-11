package main;

import java.util.ArrayList;

/**
 * Player class.
 * This class generates a Player, which has their own unique ID.
 * There's an arraylist of cards and methods to add or remove cards.
 * 
 * @author Sebastian Dixon and Joshua Adebayo
 */

public class Player {
    private final int playerId; // Final so it can't be changed ever

    // Using an ArrayList so we can add and remove items from the list
    private final ArrayList<Integer> cards = new ArrayList<>();

    /**
     * This is a constructor for a Player.
     * playerID is set in the PlayerGenerator class and is assigned in this
     * constructor
     */

    public Player() {
        playerId = PlayerGenerator.getId();
    }

    /**
     * This is a method to add items to the ArrayList cards ArrayList
     * 
     * @param n The number of cards being added, this must be an integer
     */

    public void addCard(int n) {
        cards.add(n);
    }

    /**
     * This is a method to get a Players ID
     * 
     * @return A players unique ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * This is a method to remove a card from the cards ArrayList
     * 
     * @return the card that is removed is outputted
     */

    public int remove_card() {
        int removed = 0;
        /**
         * This loop goes through all of a players cards and if a cards ID doesn't match
         * the
         * Players then it is removed
         */
        for (int i = 0; i < 4; i++) {
            if (cards.get(i) != playerId) {
                removed = cards.get(i);
                cards.remove(i);
                break;
            }
        }
        return removed;
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