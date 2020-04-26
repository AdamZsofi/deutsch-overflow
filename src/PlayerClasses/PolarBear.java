package PlayerClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import TileClasses.Direction;
import TileClasses.Tile;

/**
 * Auto special Player
 * Functionalities:
 * - auto-step
 * - ))))))))))))))))))
 */
public class PolarBear extends Character{
    /**
     * PolarBear steps, PositionLUT will be refreshed
     * ERROR HANDLING: Tile hasn't neighbour on dir direction, then prints out "You can't go that way" message
     * @param dir Direction
     */
    public void step(Direction dir) {
        Tile position= PositionLUT.getInstance().getPosition(this);
        try {
            Tile next_tile = position.getNeighbour(dir);
            PositionLUT.getInstance().setPosition(this, next_tile);
            Game.log.println("$ PolarBear>step : Transaction 'PolarBearSteps' is completed");
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("PolarBear can't go that way");
            Game.log.format("! PolarBear>step : Sorry, PolarBear cannot go that way CAUGHT INDEX OUTOFBOUNDS EXCEPTION\n");
            return;
        }
    }

    public String getShortName() {
        return "B";
    }
}
