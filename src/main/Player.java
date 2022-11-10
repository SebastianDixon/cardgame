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

    public int remove_card() {
        int removed = 0;
        for (int i = 0; i < 4; i++) {
            if (cards.get(i) != playerId) {
                removed = cards.get(i);
                cards.remove(i);
                break;
            }
        }
        return removed;
    }

    public String toString() {
        String cards = "";
        for (Integer i: this.cards) {
            cards = cards + i + " ";
        }
        return cards;
    }
}
