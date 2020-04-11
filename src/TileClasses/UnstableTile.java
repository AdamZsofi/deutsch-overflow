package TileClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;

import java.util.ArrayList;
import java.util.Random;

public class UnstableTile extends Tile {

    public UnstableTile(int x, int y) {
        super(x, y);
        capacity = 1 ; // ez majd random lesz élesben
    }

    /**
     * Called by Character
     * It increases the capacity of Tile => standinghere-- (somebody has left the Tile)
     * @param dir direction
     */
    @Override
    public void steppedOff(Direction dir) {
        System.out.println("TileClasses.UnstableTile.steppedOff()");
        standingHere--;
    }

    /**
     * Called by the Character
     * It decreases the capacity of Tile => standinghere++ (somebody entered on Tile)
     * It also check if the OVERWEIGHT OF TILE, in case: all Player fall in water
     * @param p player
     */
    @Override
    public void steppedOn(Player p) {
        System.out.println("TileClasses.UnstableTile.steppedOn()");
        standingHere++;
        if(capacity-standingHere<0){
            Tile t = PositionLUT.getInstance().getPosition(p);
            ArrayList<Player> players = PositionLUT.getInstance().getPlayersOnTile(t);
            for (Player player: players) {
                player.fallInWater();
            }
        }
    }
}
