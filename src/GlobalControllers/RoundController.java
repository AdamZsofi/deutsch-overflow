package GlobalControllers;

import ItemClasses.Item;
import ItemClasses.SignalFlare;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;

public class RoundController {

    public static RoundController rc = new RoundController(); //singleton, 1 peldany kell, asszem nem volt leirva a doksiba

    private int curID; //current player ID?
    public SignalFlare sg;
    public SnowStorm ss;
    public  PlayerContainer pc; //ötlet: pc lehetne final, init
    public Item it;

    public void init(int playerNum){
        System.out.println("GlobalControllers.init("+ playerNum+")");


        //pc = new PlayerContainer(playerNum);
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

    public PlayerContainer getPlayerContainer(){ //publicnak minek getter? felolem lehet privat tag, de pc egy static final osztaly kene legyen.
        System.out.println("GlobalControllers.getPlayerContainer()");


        return pc;
    }

}
