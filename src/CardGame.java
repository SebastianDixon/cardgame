import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private static final ArrayList<Player> players = new ArrayList<>();
    private static final ArrayList<Deck> decks = new ArrayList<>();


    private static File get_text(String fileName, int n) throws NullPointerException, IOException {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int count = 0;

            try {
                while ((st = br.readLine()) != null) {
                    count = count + 1;
                }
            } catch (IOException e) {
                System.out.println("Deck file empty");
            }

            if (count == 8 * n) {
                return file;
            } else {
                System.out.println("Incorrect deck size");
            }

        } catch (NullPointerException e) {
            System.out.println("Invalid deck file");
        }
        return null;
    }

    private static String get_filename() throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Deck address?:");
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid Deck address");
            e.printStackTrace();
        }
        return null;
    }

    private static int get_decks() throws IOException {
        int n = create_players();

        for (int i = 0;i < n; i++) {
            decks.add(new Deck());
        }

        return n;
    }

    private static int get_players() throws IOException, NumberFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("How many players?:");
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

    private static int create_players() throws IOException {
        try {
            int numberPlayer = get_players();
            for (int i = 0; i < numberPlayer; i++) {
                players.add(new Player());
            }
            return numberPlayer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void deal_cards() throws IOException {
        int n = get_decks();
        String deckFile = get_filename();
        File validDeck = get_text(deckFile, n);
        assert validDeck != null;
        Scanner sc = new Scanner(validDeck);

        for (int j = 0; j < 4; j++) {
            for (Player p: players) {
                int num = Integer.parseInt(sc.nextLine());
                p.addCard(num);
            }
        }

        while (sc.hasNextLine()) {
            for (Deck d: decks) {
                int num = Integer.parseInt(sc.nextLine());
                d.addCard(num);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("player" + players.get(i).getPlayerId() + ":" + players.get(i).toString());
            System.out.println("deck" + decks.get(i).getDeckId() + ":" + decks.get(i).toString());
        }

    }

    public static void main(String[] args) throws IOException {
        deal_cards();
    }
}
