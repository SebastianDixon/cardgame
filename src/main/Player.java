package main;

import java.io.File;
import java.util.ArrayList;

public class Player extends Thread{
    private int playerId;
    private ArrayList<Integer> cards = new ArrayList<>();
    private volatile boolean gameOver = false;
    private File player_file;

    public Player() {
        playerId = PlayerGenerator.getId();
        player_file = new File("game_files/player" + playerId + "_output.txt");
    }

    public void run() {
        logPlayer("Player " + this.playerId + " has joined the game");
        logPlayer("player " + this.playerId + " initial hand is " + this);
        boolean won = checkWon();
    }

    public boolean checkWon() {
        for (int i: cards) {
            if (i != this.getPlayerId()) {
                return false;
            }
        }
        return true;
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

    private void logPlayer(String s) {
        System.out.println(s);
        Logger.writeNewLine(player_file, s);
    }
}
