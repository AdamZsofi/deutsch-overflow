package ItemClasses;

import Control.Game;
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
        Tile t = PositionLUT.getInstance().getPosition(p);
        if(a == Activity.putUpTent && !t.getIglooOn()&& t.getCapacity()>0){
            t.putOnTent();
            p.workPoints--;
            Game.log.println("$ Tent>used : Transaction 'puttingTent' was successful");
        } else {
            Game.log.format("! Tent>used : Activity is not 'putUpTent' or Tile has Igloo : hasIgloo=%s\n", t.getIglooOn() );
        }
    }
    @Override
    public String getShortName(){ return "T("+state.getShortName(getState())+")";}

    @Override
    public String toString() { return "tent"+state.getShortName(getState());}

}
