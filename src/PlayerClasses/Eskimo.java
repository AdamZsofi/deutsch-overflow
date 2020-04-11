package PlayerClasses;

import GlobalControllers.PositionLUT;
import TileClasses.Tile;

/**
 * Specification of Player
 * Eskimo can build Iglu (special ability)
 */
public class Eskimo extends Player {
    /**
     * Sets Iglu on Tile, where the Eskimo is actually standing
     */
    public void buildIgloo() {
        System.out.print("PlayerClasses.Eskimo, ID"+ID+":");
        System.out.println("buildIgloo()");
        Tile tile = PositionLUT.getInstance().getPosition(this);
        tile.buildIglu();
    }
}
