package ItemClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Tile;

public class Shovel extends Item{
    /**
     * Called by RoundController
     * Player (p) uses Shovel
     * If Activity (a) is 'clearingSnow' decreases the snow by 1 on Tile, where the caller Player is
     * @param p Player
     * @param a Activity
     */
    @Override
    public void used(Player p, Activity a){
        if(a == Activity.clearingSnow){
            Tile t = PositionLUT.getInstance().getPosition(p);
            t.changeSnow(-1);//csak egy, mert a Player.clearSnowbol jön, ott már egyet alapbol ás az áson kivul.
            Game.log.println("$ Shovel>used : Transaction 'clearingSnow' was successful");
        } else {
            Game.log.println("! Shovel>used : Activity is not 'clearingSnow'");
        }

    }
}
