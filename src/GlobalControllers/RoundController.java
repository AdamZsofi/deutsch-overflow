package GlobalControllers;

import ItemClasses.Item;
import ItemClasses.SignalFlare;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;

public class RoundController {

    private static RoundController rc;
    private int curID; //current player ID, player ID: pc.players arrayban az indexszáma
    public SignalFlare sg;
    public SnowStorm ss;
    //public Item it; //ez itt ami a játékos kezében van?

    public static RoundController getInstance() {
        // TODO review
        // paraméterek - mi az, amit kap, mi az, amit magának hoz létre? (Kb mi az, amit posLUT és mi az, amit ő hoz létre)
        // Megj.: Ha bármit kap paraméterben, akkor kell egy init fgv és egy getInstance külön, ahol getInstance nem kap paramétert
        // hogy pl. Player működését itt a paraméterek ne nyírják ki
        //egyenlore nem latom hogy lenne külon
        if(rc == null) {
            rc = new RoundController();
        }
        return rc;
    }

    /*
    public static RoundController initializeInstance() { //ilyen formában összevonható a getInstance-val, nem kap parametert
        if(rc == null) {
            rc = new RoundController();
        }
        return rc;
    }*/

    private RoundController() {
        ss = new SnowStorm();
        sg = new SignalFlare();//feltölti itemekkel a sajat ArrrayList-ét, posLUT initnél mapon is ott lesz.
        // TODO review
    }

    public void init(int playerNum){ //a játékot inicializálja
        System.out.println("GlobalControllers.RoundController.init("+ playerNum+")");

        //playercontainer itt init (skeletonban a playerNum nem játszik szerepet, minig 4 lesz)
        PlayerContainer.Initialize(playerNum);
        curID = 0;
    }
    public void startNextRound(){
        System.out.println("GlobalControllers.RoundController.startNextRound()");


        PlayerContainer.getInstance().getPlayer(curID).startRound();
    }
    public void endLastRound(){
        System.out.println("GlobalControllers.RoundController.endLastRound()");

        curID = (curID + 1) % PlayerContainer.getInstance().getPlayerNum();
        ss.tryStorm();
    }
    public void lose(String cause){
        System.out.println("GlobalControllers.RoundController.lose(" + cause + ")");

        System.exit(0);
    }
    public void win(){
        System.out.println("GlobalControllers.RoundController.win()");

        System.exit(0);
    }

}
