package main;

public class Card {
    private final int cardId;

    public Card() {
        cardId = PlayerGenerator.getId();
    }

    public int getCardId() {
        return cardId;
    }
}
