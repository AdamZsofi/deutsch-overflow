package PlayerClasses;

import GlobalControllers.PositionLUT;
import TileClasses.Tile;

/**
 * Specification of Player
 * Eskimo can build Igloo (special ability)
 */
public class Eskimo extends Player {
    /**
     * Sets Igloo on Tile, where the Eskimo is actually standing
     */
    public Eskimo(){
        super();
        BodyHeat=5;
    }
    public void buildIgloo() {
        System.out.print("PlayerClasses.Eskimo, ID"+ID+":");
        System.out.println("buildIgloo()");
        Tile tile = PositionLUT.getInstance().getPosition(this);
        tile.buildIgloo();
    }
}
