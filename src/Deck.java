public class Deck {
    private final int deckId;

    public Deck() {
        deckId = Generator.getId();
    }

    public int getDeckId() {
        return deckId;
    }
}
