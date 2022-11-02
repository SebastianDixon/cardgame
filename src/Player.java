public class Player {
    private final int playerId;

    public Player() {
        playerId = PlayerGenerator.getId();
    }

    public int getPlayerId() {
        return playerId;
    }

    public String toString() {
        return "player " + getPlayerId() + " ";
    }
}
