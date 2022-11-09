import org.junit.jupiter.api.Test;

import java.io.IOException;
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
}
