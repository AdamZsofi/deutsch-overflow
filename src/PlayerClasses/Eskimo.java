package PlayerClasses;

import CLI.Game;
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
        Game.log.format("# Eskimo>buildIgloo : started by PlayerID: %d\n", ID);
        Tile tile = PositionLUT.getInstance().getPosition(this);
        tile.buildIgloo();
        Game.log.println("# Eskimo>buildIgloo : ended");
    }

    public String getShortName() {
        return "E"+ID;
    }
}
