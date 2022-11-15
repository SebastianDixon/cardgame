package main;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DeckGenerator class
 * This is a class whhich will be used to assign each card with a unique ID
 * within
 * the Deck constructor
 * 
 * @author Sebastian Dixon and Joshua Adebayo
 */
public class DeckGenerator {
    /**
     * AtomicInteger is private final static so it can't be changed anywhere and
     * call only be called within this class
     */
    private final static AtomicInteger counter = new AtomicInteger();

    /**
     * This is a method to reteive a Decks ID.
     * This was made public static to give access to class variables without using
     * the class's
     * object but can be retreived outside of this class.
     * 
     * @return The ID of a Deck
     */
    public static int getId() {
        return counter.incrementAndGet();
    }
}
