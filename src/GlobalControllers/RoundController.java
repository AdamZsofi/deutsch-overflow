package GlobalControllers;

import ItemClasses.Item;
import ItemClasses.SignalFlare;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;

public class RoundController {

    public static RoundController rc = new RoundController(); //singleton, 1 peldany kell, asszem nem volt leirva a doksiba

    private int curID;
    public SignalFlare sg;
    public SnowStorm ss;
    public PlayerContainer pc;
    public Item it;

    public void init(int playerNum){
        System.out.println("GlobalControllers.init("+ playerNum+")");
    }
    public void startNextRound(){
        System.out.println("GlobalControllers.startNextRound()");
    }
    public void endLastRound(){
        System.out.println("GlobalControllers.endLastRound()");
    }
    public void lose(String cause){
        System.out.println("GlobalControllers.lose(" + cause + ")");
    }
    public void win(){
        System.out.println("GlobalControllers.win()");
    }

    PlayerContainer getPlayerContainer(){
        System.out.println("GlobalControllers.getPlayerContainer()");

    }

}
