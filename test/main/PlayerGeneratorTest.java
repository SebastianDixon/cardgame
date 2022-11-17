package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerGeneratorTest {

    @Test
    void TestStartId() {
        var cg = new CardGame();

        var p1 = new Player(cg);
        assertEquals(1, p1.getPlayerId());
    }

}
