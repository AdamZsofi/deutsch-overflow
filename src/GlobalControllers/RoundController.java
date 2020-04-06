package GlobalControllers;

import ItemClasses.SignalFlare;
import ItemClasses.Tent;
import PlayerClasses.PolarBear;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;
import TileClasses.Direction;
import TileClasses.Tile;
import javafx.geometry.Pos;

import java.util.Random;
import java.util.Scanner;

public class RoundController {

    private static RoundController rc;
    private int curID; //current player ID, player ID: pc.players arrayban az indexszáma
    public SignalFlare sg;
    public SnowStorm ss;
    public PolarBear polarbear;
    public Tent tent;
    //public Item it; //ez itt ami a játékos kezében van?

    public static RoundController getInstance() {
        // TODO review
        // paraméterek - mi az, amit kap, mi az, amit magának hoz létre? (Kb mi az, amit posLUT és mi az, amit ő hoz létre)
        // Megj.: Ha bármit kap paraméterben, akkor kell egy init fgv és egy getInstance külön, ahol getInstance nem kap paramétert
        // hogy pl. Player működését itt a paraméterek ne nyírják ki
        //egyenlore nem latom hogy lenne külon
        if (rc == null) {
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

    public void init(int playerNum) { //a játékot inicializálja
        System.out.println("GlobalControllers.RoundController.init(" + playerNum + ")");

        //playercontainer itt init (skeletonban a playerNum nem játszik szerepet, minig 4 lesz)
        PlayerContainer.Initialize(playerNum);
        curID = 0;
    }

    public void startNextRound() {
        System.out.println("GlobalControllers.RoundController.startNextRound()");


        PlayerContainer.getInstance().getPlayer(curID).startRound();
    }

    public void endLastRound() {
        System.out.println("GlobalControllers.RoundController.endLastRound()");
        boolean can_move=true;
        curID = (curID + 1) % PlayerContainer.getInstance().getPlayerNum();
        ss.tryStorm();
        checkTent();

        Random r = new Random();
        int direction = r.nextInt(4);
        boolean success=true;
        Tile current_tile= PositionLUT.getInstance().getPosition(polarbear);
        Tile next_tile=null;
        int players_num;
        do{
            polarbear.step(Direction.valueOf(direction));
            next_tile = current_tile.getNeighbour(Direction.valueOf(direction));
            if(current_tile.equals(next_tile)) success=false;
        } while(!success);
        players_num= PositionLUT.pLUT.getPlayersOnTile(next_tile).size();
        if(players_num>0){
            lose("Bear kills player");
        }
    }

    public void lose(String cause) {
        System.out.println("GlobalControllers.RoundController.lose(" + cause + ")");

        System.exit(0);
    }

    public void win() {
        System.out.println("GlobalControllers.RoundController.win()");

        System.exit(0);
    }

    public int getcurID() {
        return curID;
    }

    /**
     * @author adam
     * Check the live of Tent. If the counter is 0, then tentOn parameter of Tile will change to false.
     */
    public void checkTent() {
        tent.counter--;
        if (tent.counter == 0) {
            Tile tile = PositionLUT.getInstance().getTile(tent.x, tent.y);
            tile.tentOn = false;
        }
    }
}
