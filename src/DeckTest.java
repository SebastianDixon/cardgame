import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void TestAddSingle() {
        int card = 1;
        var d1 = new Deck();
        d1.addCard(card);
        assertEquals("1 ",d1.toString());
    }

    @Test
    void TestAddMultiple() {
        int c1 = 1;
        int c2 = 2;
        var d2 = new Deck();
        d2.addCard(c1);
        d2.addCard(c2);
        assertEquals("1 2 ", d2.toString());
    }
}
