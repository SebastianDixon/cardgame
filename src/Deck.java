import java.util.ArrayList;

public class Deck {
    private final int deckId;
    private final ArrayList<Integer> cards = new ArrayList<>();

    public Deck() {
        deckId = DeckGenerator.getId();
    }

    public void addCard(int n) {
        cards.add(n);
    }

    public int getDeckId() {
        return deckId;
    }
}
