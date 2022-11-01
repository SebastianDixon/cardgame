import java.io.*;

public class CardGame {

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

            if (count == 8*n) {
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

        String str = reader.readLine();
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("invalid integer");
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Player p1 = new Player();
        System.out.println(p1.getPlayerId());
        System.out.println(p1);

        int numberPlayer = get_players();
        System.out.println(numberPlayer);
    }
}
