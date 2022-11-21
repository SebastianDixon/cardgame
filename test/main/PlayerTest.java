package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void TestAddSingle() {
        int card = 1;
        var cg = new CardGame();
        var p1 = new Player(cg);
        p1.addCard(card);
        assertEquals("1 ",p1.toString());
    }

    @Test
    void TestAddMultiple() {
        int c1 = 1;
        int c2 = 2;
        var cg = new CardGame();

        var p2 = new Player(cg);
        p2.addCard(c1);
        p2.addCard(c2);
        assertEquals("1 2 ", p2.toString());
    }

    @Test
    void TestWinningCondition() {
        var cg = new CardGame();

        var p = new Player(cg);
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);

        boolean check = p.checkWon();

        String output = p.logOutput.get(0);
        assertEquals(output, "player1 wins");
        assertEquals(check, true);
    }

    @Test
    void TestWriteToFile() {
        var cg = new CardGame();
        var p = new Player(cg);

        p.logOutput.add("test");
        p.logOutput.add("test2");

        p.writeToFile();

    }
}
