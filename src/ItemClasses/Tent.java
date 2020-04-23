package ItemClasses;

import CLI.Game;
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
        if(a == Activity.putUpTent && !t.getIglooOn()){
            //TODO CHECK ITT NEM INKABB A TILE PUTONTNET FUGGVENYET KENE HIVI (pl snowyHolera ez igy nem jo
            t.tentOn=true;
            Game.log.println("$ Tent>used : Transaction 'puttingTent' was successful");
        } else {
            Game.log.format("! Tent>used : Activity is not 'putUpTent' or Tile has Igloo : hasIgloo=%s\n", t.getIglooOn() );
        }
    }
    public String getShortName(){ return "T("+state.getShortName(getState())+")";}
}
