package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Player class.
 * This class generates a Player, which has its own unique ID.
 * There's an ArrayList of cards and methods to add or remove cards. And
 * finally, to check if a player has won
 *
 *
 * @author Sebastian Dixon and Joshua Adebayo
 */

public class Player implements Runnable {
    private int playerId;
    private ArrayList<Integer> cards = new ArrayList<>();
    public ArrayList<String> logOutput = new ArrayList<>();
    private volatile boolean gameOver = false;
    private File player_file;
    private CardGame game;
    public int turnsTaken;

    /**
     * This is the constructor for the Player class
     *
     * @param game The game a player is being added to
     */
    public Player(CardGame game) {
        this.playerId = PlayerGenerator.getId();
        this.player_file = new File("player" + playerId + "_output.txt");
        this.game = game;
    }

    /**
     * Method to run the game and tell the users of the progress of the game
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"running");

        this.setupFile();

        if (checkWon() && game.running.get()) {
            game.winner = this;
            game.running.set(false);
            logOutput.add("player"+playerId+" wins");
            writeToFile();
        }

        while (game.turnsTotal >= 0) {
            synchronized (this) {
                takeTurn();

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        game.numFinished.incrementAndGet();

        if (this == game.winner) {
            System.out.println("player" + playerId + "won");
        }
        else {
            logOutput.add("player" + game.winner.playerId +
                            " has informed player" + playerId +
                            " that player" + game.winner.playerId + " has won");
        }

        finalWriteToFile();

        //increment number of finished players
    }

    /**
     * This method is used to create a file for a player containng all the
     * information about the
     * player, this is added to the log.
     */
    public void setupFile() {
        String s = "player" + playerId + " initial hand: " + this.toString();
        logOutput.add(s);
        writeToFile();
    }

    /**
     * This method is used to log the complete hand of a player once it has all 4
     * cards needed to
     * play.
     */
    public void finalWriteToFile() {
        String s = "player" + playerId + " final hand: " + this.toString();
        logOutput.add("player" + playerId + " exits");
        logOutput.add(s);
        writeToFile();
    }

    /**
     * Method used to log the updated hand of a player after a round.
     */
    public void writeHandToFile() {
        String s = "player" + playerId + " current hand: " + this.toString();
        logOutput.add(s);
        writeToFile();
    }

    /**
     * This method is used to add all the written logs into a file.
     */
    public void writeToFile() {
        try {
            PrintStream out = new PrintStream(player_file);
            for (String s : logOutput) {
                out.println(s);
                System.out.println(s);
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to add cards to player, then remove it from the deck.
     */
    public void deck_add_card() {
        for (Deck d : game.decks) {
            if (d.getDeckId() == playerId) {
                addCard(d.get_cards().get(0));
                d.removeCard(d.get_cards().get(0));
                d.writeFile();
            }
        }
    }

    /**
     * This method is used to add cards to decks, then remove it from the player.
     */
    public void deck_remove_card() {
        int n = remove_card();
        for (Deck d : game.decks) {
            if (d.getDeckId() == playerId + 1) {
                d.addCard(n);
                d.writeFile();
            }
        }

        if (playerId == game.numPlayers) {
            Deck d = game.decks.get(0);
            d.addCard(n);
            d.writeFile();
        }
    }

    /**
     * This method is used to check if a deck's id is the same as it's players'.
     */
    public void takeTurn() {
        Deck sameId = null;
        for (Deck d: game.decks) {
            if (d.getDeckId() == this.playerId) {
                sameId = d;
            }
        }

        assert sameId != null;
        if (!sameId.get_cards().isEmpty()) {
            if (sameId.lock.tryLock()) {    // lock prevents starvation
                //take card
                deck_add_card();
                //place card in next deck
                deck_remove_card();
                //increment turns taken
                turnsTaken += 1;
                //unlock sameId deck
                checkWon();

                sameId.lock.unlock();
            }
        }

        writeHandToFile();
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
        logOutput.add("player" + playerId + " wins");
        game.running.set(false);
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
     * This is a method to get a Player's ID.
     *
     * @return A players unique ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * This is a method to remove a card from the cards ArrayList.
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
     * ToString to output the important data within a class.
     *
     * @return All the cards are returned but as strings
     */
    @Override
    public String toString() {
        // This process allows us to output the cards as a string
        String cards = "";
        for (Integer i : this.cards) {
            cards = cards + i + " "; // Put parentheses around each integer in the cards ArrayList
        }
        return cards;
    }

}
