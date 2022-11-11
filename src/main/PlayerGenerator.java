package main;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a class which will be used to assign each player a unique ID within
 * the Player constructor
 * 
 * @author Joshua Adebayo and Sebastian Dixon
 */

public class PlayerGenerator {
    /**
     * AtomicInteger is private final static so it can't be changed anywhere and
     * call only be
     * called within this class
     */
    private final static AtomicInteger counter = new AtomicInteger();

    /**
     * This is a method to reteive a Players ID.
     * This was made public static to give access to class variables without using
     * the class's
     * object but can be retreived outside of this class.
     * 
     * @return The ID of a Player
     */
    public static int getId() {
        return counter.incrementAndGet();
    }
}