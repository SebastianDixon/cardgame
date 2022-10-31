public class Player {
    private final int m_playerId;

    public Player() {
        m_playerId = Generator.getId();
    }

    public int getPlayerId() {
        return m_playerId;
    }
}
