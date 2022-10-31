public class Card {
    private final int cardId;

    public Card() {
        cardId = Generator.getId();
    }

    public int getCardId() {
        return cardId;
    }
}
