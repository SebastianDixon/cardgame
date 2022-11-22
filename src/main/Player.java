package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/*
 * Player class.
 * This class generates a Player, which has its own unique ID.
 * There's an ArrayList of cards and methods to add or remove cards. And
 * finally, to check if
 * a player has won
 *
 * @author Sebastian Dixon and Joshua Adebayo
 */

public class Player implements Runnable {
<<<<<<< Updated upstream
    private int playerId;
    private ArrayList<Integer> cards = new ArrayList<>();
    public ArrayList<String> logOutput = new ArrayList<>();
    private volatile boolean gameOver = false;
    private File player_file;
    private CardGame game;
    public int turnsTaken;
    public int turnsLeft;

    public Player(CardGame game) {
        this.playerId = PlayerGenerator.getId();
        this.player_file = new File("player" + playerId + "_output.txt");
        this.game = game;
=======
    private final int playerId;
    private final ArrayList<Integer> cards = new ArrayList<>();
    private final boolean gameOver = false;
    private final File player_file;

    public Player() {
        this.playerId = PlayerGenerator.getId();
        this.player_file = new File("game_output/player" + playerId + "_output.txt");
>>>>>>> Stashed changes
    }

    @Override
    public void run() {
<<<<<<< Updated upstream
        System.out.println(Thread.currentThread().getName()+"running");

        this.setupFile();

        if (checkWon() && game.running.get()) {
            game.winner = this;
            game.running.set(false);
            logOutput.add("player"+playerId+" wins");
            writeToFile();
        }

        while (game.running.get()) {
            takeTurn();
        }

        game.numFinished.incrementAndGet();

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        while (turnsTaken < turnsLeft) {
            takeTurn();
        }

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


    public void setupFile() {
        String s = "player" + playerId + " initial hand: " + this.toString();
        logOutput.add(s);
        writeToFile();
    }


    public void finalWriteToFile() {
        String s = "player" + playerId + " final hand: " + this.toString();
        logOutput.add("player" + playerId + " exits");
        logOutput.add(s);
        writeToFile();
    }


    public void writeHandToFile() {
        String s = "player" + playerId + " current hand: " + this.toString();
        logOutput.add(s);
        writeToFile();
=======
        logPlayer("Player " + this.playerId + " has joined the game");
        logPlayer("player " + this.playerId + " initial hand is " + this);
        System.out.println("Player " + this.playerId + " has joined the game");
        System.out.println("player " + this.playerId + " initial hand is " + this);
>>>>>>> Stashed changes
    }


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
                game.add_card(this);
                //place card in next deck
                game.remove_card(this);
                //increment turns taken
                turnsTaken += 1;
                //unlock sameId deck

                checkWon();

                sameId.lock.unlock();
            }
        }

        writeHandToFile();
    }

    /*
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
        return true;
    }

    /*
     * This is a method to add items to the ArrayList cards.
     *
     * @param n The number of cards being added, this must be an integer
     */
    public void addCard(int n) {
        cards.add(n);
    }

    /*
     * This is a method to get a Players ID
     *
     * @return A players unique ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /*
     * This is a method to remove a card from the cards ArrayList
     *
     * @return the card that is removed is outputted
     */
    public int remove_card() {
<<<<<<< Updated upstream
        for (int i = 0; i < cards.size(); i++) {
            int n = cards.get(i);
            if (n != playerId) {
=======
        int removed = 0;
        /*
         * This loop goes through all of a players cards and if a cards ID doesn't match
         * the
         * Players then it is removed
         */
        for (int i = 0; i < 4; i++) {
            if (cards.get(i) != playerId) {
                removed = cards.get(i);
>>>>>>> Stashed changes
                cards.remove(i);
                return n;
            }
        }
        return 0;
    }

    /*
     * ToString to output the important data within a class,
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
<<<<<<< Updated upstream

=======

    private void logPlayer(String s) {
        System.out.println(s);
        Logger.writeNewLine(player_file, s);
    }


>>>>>>> Stashed changes
}
