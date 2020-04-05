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

    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.Tent.used()");
        Tile t = PositionLUT.getInstance().getPosition(p);
        if(a == Activity.putUpTent && !t.getIgluOn()){
            t.tentOn=true;
        }
    }
}
