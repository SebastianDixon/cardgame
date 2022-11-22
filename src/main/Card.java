package main;

/**
 * Card class.
 * This object is what is assigned to Players and their decks to
 */
public class Card {
    private int val;

    /**
     * Constructor for the Card class
     *
     * @param val The value being assgned to the card
     */
    public Card(int val) {
        this.val = val;
    }

    /**
     * Method to get the value of the card
     *
     * @return The value of the card
     */
    public int get_val() {
        return val;
    }

    /**
     * toString holding the important information of the card, which is just it's
     * value
     *
     * @return The card's value
     */
    @Override
    public String toString() {
        return String.valueOf(this.get_val());
    }

}
