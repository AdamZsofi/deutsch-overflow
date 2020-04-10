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
     * Places a Tent to the caller (Player) position
     * Tent cannot be placed to the Tile if an Iglu is on TIle
     * @param p Player
     * @param a Activity
     */
    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.Tent.used()");
        Tile t = PositionLUT.getInstance().getPosition(p);
        if(a == Activity.putUpTent && !t.getIgluOn()){
            t.tentOn=true;
        }
    }
}
