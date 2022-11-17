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
    private String player_file;

    public Player() {
        this.playerId = PlayerGenerator.getId();
        this.player_file = "player" + playerId + "_output.txt";

    }

    public String getPlayer_file() {
        return player_file;
    }

    @Override
    public void run() {
        logPlayer("Player " + this.playerId + " has joined the game");
        logPlayer("player " + this.playerId + " initial hand is " + this);

        System.out.println("Player " + this.playerId + " has joined the game");
        System.out.println("player " + this.playerId + " initial hand is " + this);
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
        for (int i = 0; i < cards.size(); i++) {
            int n = cards.get(i);
            if (n != playerId) {
                cards.remove(i);
                return n;
            }
        }
        return 0;
    }

    /**
     * ToString to output the important data within a class,
     *
     * @return All the cards are returned but as strings
     */

    public String toString() {
        // This process allows us to output the cards as a string
        String cards = "";
        for (Integer i : this.cards) {
            cards = cards + i + " "; // Put parentheses around each integer in the cards ArrayList
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
        File f = new File(player_file);
        Logger.writeNewLine(f, s);
    }


}
