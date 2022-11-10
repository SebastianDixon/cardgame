package main;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {

    @Test
    void TestPlayersSize() {
        CardGame.create_players(1);
        ArrayList<Player> p_test = new ArrayList<>();
        p_test.add(new Player());
        assertEquals(p_test.size(), CardGame.players.size());
    }

    @Test
    void TestDecksSize() {
        CardGame.create_decks(1);
        ArrayList<Deck> d_test = new ArrayList<>();
        d_test.add(new Deck());
        assertEquals(d_test.size(), CardGame.decks.size());
    }

    @Test
    void TestPickupCard() {
        Player p = new Player();
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(2);

        Deck d = new Deck();
        d.addCard(3);

        System.out.println(d.getDeckId() == p.getPlayerId());

        CardGame.pickup_card(p);

        assertEquals(p.toString(), "1 1 1 2 3 ");

    }

    @Test
    void TestRemoveCard() {
        Player p = new Player();
        p.addCard(1);
        p.addCard(1);
        p.addCard(1);
        p.addCard(2);
        p.addCard(3);

        Deck d1 = new Deck();
        Deck d2 = new Deck();

        System.out.println(d2.getDeckId() == p.getPlayerId() + 1);
        CardGame.remove_card(p);

        assertEquals(d2.toString(), "2 ");
    }
}
