public class RoundController {

    private int curID;
    public SignalFlare sg;
    public SnowStorm ss;
    public PlayerContainer pc;
    public Item it;

    public void init(int playerNum);
    public void startNextRound();
    public void endLastRound();
    public void lose(String cause);
    public void win();
    PlayerContainer getPlayerContainer();

}
