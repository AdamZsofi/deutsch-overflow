package ItemClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Tile;

public class Shovel extends Item{

    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.Shovel.used()");


        if(a == Activity.clearingSnow){
            Tile t = PositionLUT.pLUT.getPosition(p);
            t.changeSnow(-1);//csak egy, mert a Player.clearSnowbol jön, ott már egyet alapbol ás az áson kivul.
        }
    }
}
