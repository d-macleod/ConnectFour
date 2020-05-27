import java.time.Instant;
import java.util.Random;

public class ConnectAgent {
    public int choice() {
        Instant time = Instant.now();
        long seed = time.getEpochSecond();
        Random random = new Random(seed);
        return random.nextInt(7);
    }
}
