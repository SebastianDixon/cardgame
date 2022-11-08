package main;

import java.util.ArrayList;

public class Player {
    private final int playerId;
    private final ArrayList<Integer> cards = new ArrayList<>();

    public Player() {
        playerId = PlayerGenerator.getId();
    }

    public void addCard(int n) { cards.add(n); }

    public int getPlayerId() {
        return playerId;
    }

    public String toString() {
        String cards = "";
        for (Integer i: this.cards) {
            cards = cards + i + " ";
        }
        return cards;
    }
}
