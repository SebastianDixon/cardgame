package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void TestAddSingle() {
        int card = 1;
        var p1 = new Player();
        p1.addCard(card);
        assertEquals("1 ",p1.toString());
    }

    @Test
    void TestAddMultiple() {
        int c1 = 1;
        int c2 = 2;
        var p2 = new Player();
        p2.addCard(c1);
        p2.addCard(c2);
        assertEquals("1 2 ", p2.toString());
    }

    @Test
    void TestWinningCondition() {
        var p = new Player();
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);

        boolean won = p.checkWon();
        assertEquals(won, true);
    }
}
