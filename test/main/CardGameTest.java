package main;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {
<<<<<<< Updated upstream
    @Test
    public void TestAddCard() {
        var cg = new CardGame();

        var p = new Player(cg);
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(5);

        var d = new Deck();
        d.addCard(2);
        d.addCard(5);

        cg.players.add(p);
        cg.decks.add(d);

        cg.add_card(p);

        assertEquals(p.toString(), "1 1 1 5 2 ");
        assertEquals(d.toString(), "5 ");

    }

    @Test
    public void TestRemoveCard() {
        var cg = new CardGame();
        var p = new Player(cg);
        var d = new Deck();
        var d2 = new Deck();

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


=======
    // redo testing for cardgame
>>>>>>> Stashed changes
}
