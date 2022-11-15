package main;

import java.io.File;
import java.util.ArrayList;

/**
 * Player class.
 * This class generates a Player, which has its own unique ID.
 * There's an ArrayList of cards and methods to add or remove cards. And
 * finally, to check if
 * a player has won
 *
 * @author Sebastian Dixon and Joshua Adebayo
 */

public class Player extends Thread {
    private int playerId;
    private ArrayList<Integer> cards = new ArrayList<>();
    private volatile boolean gameOver = false;
    private File player_file;

    public Player() {
        this.playerId = PlayerGenerator.getId();
    }

    public void run() {
        logPlayer("Player " + this.playerId + " has joined the game");
        logPlayer("player " + this.playerId + " initial hand is " + this);
        boolean won = checkWon();
    }

    /**
     * This method is used to check if a player has won
     * 
     * @return true or false
     */
    public boolean checkWon() {
        // The loop checks if all the players cards are the same or not
        for (int i : cards) {
            if (i != this.getPlayerId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * This is a method to add items to the ArrayList cards.
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
        /*
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
    /*
     * private void logPlayer(String s) {
     * System.out.println(s);
     * Logger.writeNewLine(player_file, s);
     * }
     * 
     */

    private void logPlayer(String s) {
        System.out.println(s);
        Logger.writeNewLine(player_file, s);
    }


}
