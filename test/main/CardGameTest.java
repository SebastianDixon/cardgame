package main;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {
    @Test
    public void TestAddCard() {
        var p = new Player();
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(5);

        var d = new Deck();
        d.addCard(2);
        d.addCard(5);

        var cg = new CardGame();
        cg.players.add(p);
        cg.decks.add(d);

        cg.add_card(p);

        assertEquals(p.toString(), "1 1 1 5 2 ");
        assertEquals(d.toString(), "5 ");

    }

    @Test
    public void TestRemoveCard() {
        var p = new Player();
        var d = new Deck();
        var d2 = new Deck();
        var cg = new CardGame();

        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(5);
        p.addCard(9);

        d2.addCard(2);

        cg.players.add(p);
        cg.decks.add(d);
        cg.decks.add(d2);

        cg.remove_card(p);

        assertEquals(p.toString(), "1 1 1 9 ");
        assertEquals(d2.toString(), "2 5 ");
    }
}
