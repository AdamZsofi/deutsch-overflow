package ItemClasses;

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
     * After step, increments the counter of FragileShovel. If the player can't clear more tile with this fragile shovel: throws down.
     */
    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.FragileShovel.used()");

        if(a == Activity.clearingSnow && counter>0){
            Tile t = PositionLUT.getInstance().getPosition(p);
            t.changeSnow(-1);//csak egy, mert a Player.clearSnowbol jön, ott már egyet alapbol ás az áson kivul.
        }
        counter--;
        if(counter==0){
            PositionLUT.getInstance().setPosition(this, PositionLUT.getInstance().getTile(0,2)); //másik tile-ra esik
            p.dropFragileShovel();
        }
    }
}
