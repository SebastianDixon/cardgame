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
        for (int i = 0; i < n; i++) {// Loop to add as many decks as instructed into ArrayList
            decks.add(new Deck());
        }
    }

    /*
     * This method has been made to retreive files that are to be used in a deck
     * 
     * @return Either the deck's address or null
     */
=======
    private volatile boolean gameOver = false;
    private int numPlayers;
    public Thread[] threads;

    public final ArrayList<Player> players = new ArrayList<>();

    public final ArrayList<Deck> decks = new ArrayList<>();

    public void create_players(int n) {
        for (int i = 0; i < n; i++) {
            players.add(new Player());
        }
    }

    public void create_decks(int n) {
        for (int i = 0;i < n; i++) {
            decks.add(new
                    Deck());
        }
    }

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    /*
     * This method is used to retrieve the number of players in a game
     * 
     * @return The number of players in a game
     * @throws IOException           Exception used to access data from files
     * @throws NumberFormatException Exception used to convert a string into a
     *                               numerical data type
     */
    public int get_players() throws IOException, NumberFormatException {
=======
    public void get_players() throws IOException, NumberFormatException {
>>>>>>> Stashed changes
=======
    public void get_players() throws IOException, NumberFormatException {
>>>>>>> Stashed changes
        var reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Number of players:");
        String str = reader.readLine();
        // check positive integer
        try {
            numPlayers = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("invalid integer");
            e.printStackTrace();
        }
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream

    public void deal_cards(String s) throws IOException {
=======
=======
>>>>>>> Stashed changes
    public void deal_cards(String s, int n) throws IOException {
        // must pass file parameter
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream

    public void setup() throws IOException {
        String s = get_file();
        int n = get_players();
        numPlayers = n;
        boolean b = validate_deck(s, n);
        if (b) {
            create_players(n);
            create_decks(n);
            deal_cards(s);
=======
    public void setup() throws IOException {
        String s = get_file();
        get_players();
        System.out.println(numPlayers);
        boolean b = validate_deck(s, numPlayers);
        if (b) {
            create_players(numPlayers);
            create_decks(numPlayers);
            deal_cards(s, numPlayers);
>>>>>>> Stashed changes
=======
    public void setup() throws IOException {
        String s = get_file();
        get_players();
        System.out.println(numPlayers);
        boolean b = validate_deck(s, numPlayers);
        if (b) {
            create_players(numPlayers);
            create_decks(numPlayers);
            deal_cards(s, numPlayers);
>>>>>>> Stashed changes
        } else {
            System.out.println("Invalid deck");
        }

        for (Player p:players) {
            System.out.println(p.getPlayerId() + " : "  + p);
        }
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream

    public void add_card(Player p) {
        for (Deck d : decks) {
=======
=======
>>>>>>> Stashed changes
    public void pickup_card(Player p) {
        for (Deck d: decks) {
>>>>>>> Stashed changes
            if (d.getDeckId() == p.getPlayerId()) {
                p.addCard(d.get_cards().get(0));
                d.removeCard(d.get_cards().get(0));
                d.writeFile();
            }
        }
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public void remove_card(Player p) {
        int n = p.remove_card();
        for (Deck d : decks) {
            if (d.getDeckId() == p.getPlayerId() + 1) {
                d.addCard(n);
                d.writeFile();
            }
        }

        if (p.getPlayerId() == numPlayers) {
            Deck d = decks.get(0);
            d.addCard(n);
            d.writeFile();
        }
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream

    public void startGame() throws IOException {
        setup();
        threads = new Thread[numPlayers];
        turnsTotal = 0;
        numFinished.set(0);

=======
=======
>>>>>>> Stashed changes
    public void startGame() throws IOException {
        setup();

        threads = new Thread[numPlayers];
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

        for (int i = 0; i < numPlayers; i++) {
            Thread thread = new Thread(players.get(i));
            threads[i] = thread;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        }

        while (this.running.get()) {
            for (Thread t: threads) {
                t.start();
            }

            //LOOK HERE
            for (Player p:players) {
                if (p.turnsTaken > turnsTotal) {
                    this.turnsTotal = p.turnsTaken;
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

            this.running.set(false);
        }

        for (Deck d:decks) {
            d.writeFile();
        }

=======
=======
>>>>>>> Stashed changes
            thread.start();
        }

    }

    public static void main(String[] args) throws IOException {
        CardGame newgame = new CardGame();
        newgame.startGame();
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }


    public static void main(String[] args) throws IOException {
        CardGame newgame = new CardGame();
        newgame.startGame();
    }

}
