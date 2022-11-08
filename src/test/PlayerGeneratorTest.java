package test;
import Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerGeneratorTest {

    @Test
    void TestStartId() {
        var p1 = new Player();
        assertEquals(1, p1.getPlayerId());
    }

}
