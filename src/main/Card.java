package main;

public class Card {

    private int val;

    public Card(int val) {
        this.val = val;
    }

    public int get_val() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.get_val());
    }

}
