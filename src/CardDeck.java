public class CardDeck {
    private final int deckId;

    public CardDeck() {
        deckId = Generator.getId();
    }

    public int getDeckId() {
        return deckId;
    }
}
