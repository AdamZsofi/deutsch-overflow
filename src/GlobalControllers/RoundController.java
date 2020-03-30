package GlobalControllers;

import ItemClasses.Item;
import ItemClasses.SignalFlare;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;

public class RoundController {

    // TODO nem jó, az attr.-t init-elni kéne, kapott getInstancet, kéne neki private c'tor
    // public static RoundController rc = new RoundController(); //singleton, 1 peldany kell, asszem nem volt leirva a doksiba
    private static RoundController rc;
    private int curID; //current player ID, player ID: pc.players arrayban az indexszáma
    public SignalFlare sg;
    public SnowStorm ss;
    public Item it;

    public static RoundController getInstance() {
        // TODO paraméterek - mi az, amit kap, mi az, amit magának hoz létre? (Kb mi az, amit posLUT és mi az, amit ő hoz létre)
        // Megj.: Ha bármit kap paraméterben, akkor kell egy init fgv és egy getInstance külön, ahol getInstance nem kap paramétert
        // hogy pl. Player működését itt a paraméterek ne nyírják ki
        if(rc == null) {
            throw new NullPointerException("RoundController wasn't initialized");
        }
        return rc;
    }

    public static RoundController initializeInstance() { // TODO put parameters here and in c'tor
        if(rc == null) {

        }
        return rc;
    }

    private RoundController() {
        // TODO befejezni - lásd init-ben
    }

    public void init(int playerNum){
        System.out.println("GlobalControllers.RoundController.init("+ playerNum+")");


        //pc = new PlayerContainer(playerNum);
        curID = 0;
    }
    public void startNextRound(){
        System.out.println("GlobalControllers.RoundController.startNextRound()");


        PlayerContainer.getInstance().getPlayer(curID).startRound();
    }
    public void endLastRound(){
        System.out.println("GlobalControllers.RoundController.endLastRound()");

        curID = (curID + 1) % PlayerContainer.getInstance().getPlayerNum();

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
