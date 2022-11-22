package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Cardgame class.
 * This class is where all the rules of the card gane are encoded
 * 
 * @author Sebastian Dixon and Joshua Adebayo
 */
public class CardGame {
    public Thread[] threads;
    public int numPlayers;
    public final ArrayList<Player> players = new ArrayList<>();
    public final ArrayList<Deck> decks = new ArrayList<>();
    public Player winner = null;
    public AtomicBoolean running = new AtomicBoolean(true);
    public AtomicInteger numFinished = new AtomicInteger(0);
    public int turnsTotal;

    /**
     * This method has been made to create a player/players and add them to the
     * players ArrayList
     *
     * @param n The number of players being created
     */
    public void create_players(int n) {
        for (int i = 0; i < n; i++) { // Loop to add as many players as intructed into ArrayList
            players.add(new Player(this));
        }
    }

    /**
     * This method has been made to a new deck/ decks
     *
     * @param n The number of new decks
     */
    public void create_decks(int n) {
        for (int i = 0; i < n; i++) {// Loop to add as many decks as instructed into ArrayList
            decks.add(new Deck());
        }
    }

    /**
     * This method has been made to retreive files that are to be used in a deck.
     *
     * @return null
     */
    public String get_file() {
        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Deck address:");
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid Deck address");
            System.exit(1);
        }
        return null;
    }

    /**
     * Boolean method to check the validity of a deck by reading in the file in then
     * checking how
     * many lines it has.
     *
     * @param s String holding the deck
     * @param n The number of people playing in the game.
     * @return false
     */
    public boolean validate_deck(String s, int n) {
        var f = new File(s);
        try {
            var br = new BufferedReader(new FileReader(f));
            int count = 0;
            String st;

            try {
                while ((st = br.readLine()) != null) {
                    count = count + 1;
                }
            } catch (IOException e) {
                System.out.println("Deck file empty");
            }

            return count == 8 * n;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is used to retrieve the number of players in a game
     *
     * @return The number of players in a game
     * @throws IOException           IOException Exception used to access data from
     *                               files
     * @throws NumberFormatException Exception used to convert a string into a
     *                               numerica ldata type
     */
    public int get_players() throws IOException, NumberFormatException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Number of players:");
        String str = reader.readLine();
        // check positive integer
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("invalid integer");
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This method is used to deal the cards, i.e distributing the equal number
     * cards to
     * each player
     *
     * @param s A number n string form so that is being turned into a file in order
     *          to be added to a player through the addCard method
     * @throws IOException Exeption used to access the file ,f, which contains a
     *                     card
     */
    public void deal_cards(String s) throws IOException {
        File f = new File(s);
        Scanner sc = new Scanner(f);

        for (int j = 0; j < 4; j++) {
            for (Player p : players) {
                p.addCard(Integer.parseInt(sc.nextLine()));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (Deck d : decks) {
                d.addCard(Integer.parseInt(sc.nextLine()));
            }
        }
    }

    /**
     * Method used to set up a game by collecting the needed data to create a game
     *
     * @throws IOException Exception needed to access data within files
     */
    public void setup() throws IOException {
        String s = get_file();
        int n = get_players();
        numPlayers = n;
        boolean b = validate_deck(s, n);
        if (b) {
            create_players(n);
            create_decks(n);
            deal_cards(s);
        } else {
            System.out.println("Invalid deck");
        }

        for (Player p:players) {
            System.out.println(p.getPlayerId() + " : "  + p);
        }
    }

    /**
     * Method to start the game of cards with all the players
     *
     * @throws IOException
     */
    public void startGame() throws IOException {
        setup();
        threads = new Thread[numPlayers];
        turnsTotal = 0;
        numFinished.set(0);


        for (int i = 0; i < numPlayers; i++) {
            Thread thread = new Thread(players.get(i));
            threads[i] = thread;
            thread.start();
        }

        for (Player p:players) {
            if (p.turnsTaken > turnsTotal) {
                turnsTotal = p.turnsTaken;
            }
        }

        for (Player p: players) {
            synchronized (p){
                p.notify();
            }
        }

        /*
        while (numFinished.get() != players.size()) {

        }
        */


        for (Deck d:decks) {
            d.writeFile();
        }

    }


    public static void main(String[] args) throws IOException {
        CardGame newgame = new CardGame();
        newgame.startGame();
    }

}
