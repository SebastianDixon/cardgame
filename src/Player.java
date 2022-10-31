public class Player {
    private final int playerId;

    public Player() {
        playerId = Generator.getId();
    }

    public int getPlayerId() {
        return playerId;
    }
}
