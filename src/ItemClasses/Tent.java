package ItemClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Tile;

/**
 * @author adam
 */

public class Tent extends Item{
    public int counter;
    /**
     * @param x,y Position of Tile, where the Tent located
     */
    public int x,y;

    /**
     * Called by RoundController
     * Player(p) puts Tent on Tile
     * If Activity (a) is 'putUpTent' and Tile hasn't Iglu
     * Places a Tent to the caller (Player) position
     * @param p Player
     * @param a Activity
     */
    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.Tent.used()");
        Tile t = PositionLUT.getInstance().getPosition(p);
        if(a == Activity.putUpTent && !t.getIglooOn()){
            t.tentOn=true;
        }
    }
}
