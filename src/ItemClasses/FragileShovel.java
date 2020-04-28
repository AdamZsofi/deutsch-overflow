package ItemClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Tile;

/**
 * @author adam
 *
 */

public class FragileShovel extends Shovel{
    private int counter;

    public FragileShovel(){
        counter=3;
    }

    /**
     * After step, increments the counter of FragileShovel. If the player can't clear more tile with this fragile shovel: throws down
     * @param p Player
     * @param a Activity
     */
    @Override
    public void used(Player p, Activity a){

        if(a == Activity.clearingSnow && counter>0){
            Tile t = PositionLUT.getInstance().getPosition(p);
            t.changeSnow(-1);//csak egy, mert a Player.clearSnowbol jön, ott már egyet alapbol ás az áson kivul.
            counter--;
            Game.log.println("$ FragileShovel>used : Transaction 'clearingSnow' was successful");
        } else {
            Game.log.format("! FragileShovel>used : Activity is not 'clearingSnow' or counter is not: %d>0\n", counter);
        }

        if(counter==0){
            PositionLUT.getInstance().setPosition(this, PositionLUT.getInstance().getTile(0,2)); //másik tile-ra esik
            p.dropFragileShovel();
            Game.log.println("$ FragileShovel>used : Transaction 'dropFragileShovel' was successful");
        }
    }
    public String getShortName(){ return "FS("+state.getShortName(getState())+")";}
    public String toString() {return "FragileShovel";}
}
