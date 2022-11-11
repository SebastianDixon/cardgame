package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cardgame class.
 * This class is where all the rules of the card gane are encoded
 * 
 * @author Sebastian Dixon and Joshua Adebayo
 */
public class CardGame {
    // Volatile so value can be different when on diffrent threads
    private volatile boolean gameOver = false;

    /**
     * Using ArrayLists so we can add and remove items from the list.
     * Made to be public static final so they can be accessed from outside the class
     * however,
     * their contents can't be changed.
     */

    public static final ArrayList<Player> players = new ArrayList<>();

    public static final ArrayList<Deck> decks = new ArrayList<>();

    /**
     * This method has been made to create a player/players and add them to the
     * players ArrayList
     * 
     * @param n The number of players
     */
    public static void create_players(int n) {
        for (int i = 0; i < n; i++) {
            players.add(new Player());
        }
    }

    public static void create_decks(int n) {
        for (int i = 0; i < n; i++) {
            decks.add(new Deck());
        }
    }

    public static String get_file() {
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

    public static boolean validate_deck(String s, int n) {
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

    public static int get_players() throws IOException, NumberFormatException {
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

    public static void deal_cards(String s, int n) throws IOException {
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

        for (int i = 0; i < n; i++) {
            System.out.println("player" + players.get(i).getPlayerId() + '\t' + players.get(i).toString());
            System.out.println("deck" + decks.get(i).getDeckId() + '\t' + decks.get(i).toString());
        }

    }

    public static void setup() throws IOException {
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

    public static void pickup_card(Player p) {
        for (Deck d : decks) {
            if (d.getDeckId() == p.getPlayerId()) {
                p.addCard(d.get_cards().get(0));
            }
        }
    }

    public static void remove_card(Player p) {
        int n = p.remove_card();
        for (Deck d : decks) {
            if (d.getDeckId() == p.getPlayerId() + 1) {
                d.addCard(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setup();
    }
}
