package GlobalControllers;

import CLI.Game;
import ItemClasses.SignalFlare;
import ItemClasses.Tent;
import PlayerClasses.PolarBear;
import PlayerClasses.PlayerContainer;
import SnowStorm.SnowStorm;
import TileClasses.Direction;
import TileClasses.Tile;

import java.util.Random;

public class RoundController {

    private static RoundController rc;
    private int curID; //current player ID, player ID: pc.players arrayban az indexszáma
    public SignalFlare sg;
    public SnowStorm ss;
    public PolarBear polarbear;
    public Tent tent;
    //public Item it; //ez itt ami a játékos kezében van?

    /**
     * Gives back a reference to the RoundController
     * @return RoundController
     */
    public static RoundController getInstance() {
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
        sg = new SignalFlare(); //feltölti itemekkel a sajat ArrrayList-ét, posLUT initnél mapon is ott lesz.
        polarbear= new PolarBear();
        tent = new Tent();
    }

    /**
     * Initialises the game
     * Initialises the PlayContainer and curID
     * @param playerNum NumOfPlayer
     */
    public void init(int playerNum) { //a játékot inicializálja
        Game.log.format("# RoundController>init : RoundController initialised by %d players\n", playerNum);
        curID = 0;
    }


    /**
     * if end of whole round: polarbear steps
     * storm comes with 30% odds
     */
    public void endLastRound() {
        Game.log.println("# RoundController>endLastRound : Round end started");
        //boolean can_move=true;
        curID = (curID + 1) % PlayerContainer.getInstance().getPlayerNum();
        ss.tryStorm();

        if(curID == 0) { // end of whole round
            boolean step=false;
            Direction dir = Direction.DOWN;
            Game.log.println("# RoundController>endLastRound : PolarBear steps");
            do{
                // det. játékban down-ból indulva óra iránnyal ellentétesen próbálkozunk
                if(Game.isDeterministic) { step=stepPolarbear(dir); dir=Direction.valueOf((dir.getValue()+1)%4); }
                else step=stepPolarbear();
            } while(!step);
            int players_num;
            Tile bearTile = PositionLUT.getInstance().getPosition(polarbear);
            players_num = PositionLUT.pLUT.getPlayersOnTile(bearTile).size();
            if (players_num > 0) {
                Game.log.println("! RoundController>endLastRound : Bear killed the players on Tile");
                lose("Bear kills player");
            }
            else {
                Game.log.println("! RoundController>endLastRound : Bear has found no prey on his new Tile");
            }
        }
        checkTent(); // vihar után, hogy védjen a viharban
        Game.log.println("# RoundController>endLastRound : Round end ended");
        PlayerContainer.getInstance().getPlayer(curID).startRound();
    }

    /**
     * Polarbear steps
     * @author Ádám
     */
    public boolean stepPolarbear() {
        Random r = new Random();
        Direction direction;
        Tile current_tile = PositionLUT.getInstance().getPosition(polarbear);
        Tile next_tile;
        direction = Direction.valueOf(r.nextInt(4));
        polarbear.step(direction);
        try {
            next_tile = current_tile.getNeighbour(direction);
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Polarbear steps in the deterministic game
     * @author Zsófi
     */
    public boolean stepPolarbear(Direction dir) {
        Random r = new Random();
        Direction direction;
        Tile current_tile = PositionLUT.getInstance().getPosition(polarbear);
        Tile next_tile;
        direction=dir;
        polarbear.step(direction);
        try {
            next_tile = current_tile.getNeighbour(direction);
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Lose of game -> System exit
     * @param cause String of reason
     */
    public void lose(String cause) {
        Game.log.format("You lose the game, %s\n", cause);
        Game.log.println("$ RoundController>lose : Game lose, ended");
        System.exit(1);
    }

    /**
     * Win of game -> System exit
     */
    public void win() {
        Game.log.println("You win the game, congratulation");
        Game.log.println("$ RoundController>win : Game won, ended");
        System.exit(1);
    }

    /**
     * Gives back the unique identifier ID of CURRENT Player (use to indexing etc.)
     * @return playerID
     */
    public int getcurID() {
        return curID;
    }

    /**
     * @author adam
     * Check the live of Tent. If the counter is 0, then tentOn parameter of Tile will change to false.
     */
    public void checkTent() {
        if(tent==null) return; // a storm elpusztítja rögtön, de utána rc ezt még meghívja -> kell a check
        tent.counter--;
        if (tent.counter == 0) {
            Tile tile = PositionLUT.getInstance().getTile(tent.x, tent.y);
            tile.tentOn = false;
            tent = null;
            Game.log.println("RoundController>checkTent : Tent is destroyed (reason: was too old)");
        }
    }

    public void destroyTent() {
        if(tent==null) return; // a storm elpusztítja rögtön, de utána rc ezt még meghívja -> kell a check
        Tile tile = PositionLUT.getInstance().getTile(tent.x, tent.y);
        tile.tentOn = false;
        tent = null;
        Game.log.println("RoundController>checkTent : Tent is destroyed (reason: storm)");
    }
}
