package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckGeneratorTest {

    @Test
    void TestStartId() {
        var d1 = new Deck();
        assertEquals(1, d1.getDeckId());
    }

}
