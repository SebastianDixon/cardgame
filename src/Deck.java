public class Deck {
    private final int deckId;

    public Deck() {
        deckId = DeckGenerator.getId();
    }

    public int getDeckId() {
        return deckId;
    }
}
