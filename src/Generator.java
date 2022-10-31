import java.util.concurrent.atomic.AtomicInteger;

public class Generator {
    private final static AtomicInteger counter = new AtomicInteger();

    public static int getId() {
        return counter.incrementAndGet();
    }
}
