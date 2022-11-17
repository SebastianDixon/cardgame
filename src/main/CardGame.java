package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cardgame class.
 * This class is where all the rules of the card gane are encoded
 * 
 * @author Sebastian Dixon and Joshua Adebayo
 */
public class CardGame {

    private volatile boolean gameOver = false;

    public Thread[] threads;
    public int numPlayers;
    public final ArrayList<Player> players = new ArrayList<>();
    public final ArrayList<Deck> decks = new ArrayList<>();
    public Player winner = null;
    public AtomicBoolean running = new AtomicBoolean(true);
    public AtomicInteger numFinished = new AtomicInteger(0);


    public int turnsTotal;

    /*
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

    /*
     * This method has been made to a new deck/ decks
     * 
     * @param n The number of new decks
     */
    public void create_decks(int n) {
        for (int i = 0; i < n; i++) {// Loop to add as many decks as intructed into ArrayList
            decks.add(new Deck());
        }
    }

    /*
     * This method has been made to retreive files that are to be used in a deck
     * 
     * @return Either the deck's address or null
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

    /*
     * This method is used to retrieve the number of players in a game
     * 
     * @return The number of players in a game
     * @throws IOException           Exception used to access data from files
     * @throws NumberFormatException Exception used to convert a string into a
     *                               numerical data type
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


    public void deal_cards(String s, int n) throws IOException {
        // must pass file parameter
        File f = new File(s);
        Scanner sc = new Scanner(f);

        for (int j = 0; j < 4; j++) {
            for (Player p : players) {
                int num = Integer.parseInt(sc.nextLine());
                p.addCard(num);
            }
        }

        while (sc.hasNextLine()) {
            for (Deck d : decks) {
                int num = Integer.parseInt(sc.nextLine());
                d.addCard(num);
            }
        }
    }


    public void setup() throws IOException {
        String s = get_file();
        int n = get_players();
        boolean b = validate_deck(s, n);
        if (b) {
            create_players(n);
            create_decks(n);
            deal_cards(s, n);
        } else {
            System.out.println("Invalid deck");
        }
    }


    public void add_card(Player p) {
        for (Deck d : decks) {
            if (d.getDeckId() == p.getPlayerId()) {
                p.addCard(d.get_cards().get(0));
                d.removeCard(d.get_cards().get(0));
            }
        }
    }


    public void remove_card(Player p) {
        int n = p.remove_card();
        for (Deck d : decks) {
            if (d.getDeckId() == p.getPlayerId() + 1) {
                d.addCard(n);
            }
        }
    }


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

        for (Player p: players) {
            synchronized (p){
                p.notify();
            }
        }


    }


    public static void main(String[] args) throws IOException {
        CardGame newgame = new CardGame();
        newgame.startGame();
    }

}
