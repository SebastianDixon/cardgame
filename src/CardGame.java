import java.io.*;
import java.util.ArrayList;

public class CardGame {
    private static final ArrayList<Integer> players = new ArrayList<>();

    private static File get_text(String fileName, int n) throws NullPointerException, IOException {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int count = 0;

            try {
                while ((st = br.readLine()) != null) {
                    count = count++;
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

    private static int get_players() throws IOException, NumberFormatException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("How many players?:");
        String str = reader.readLine();
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("invalid integer");
            e.printStackTrace();
        }
        return 0;
    }

    private static void create_players() throws IOException {
        try {
            int numberPlayer = get_players();
            for (int i = 0; i < numberPlayer; i++) {
                Player player = new Player();
                players.add(player.getPlayerId());
                System.out.println("player " + player.getPlayerId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        create_players();

    }
}
