package PlayerClasses;

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
        System.out.println("step("+dir+")");
        Tile position= PositionLUT.getInstance().getPosition(this);
        try {
            Tile next_tile = position.getNeighbour(dir);
            PositionLUT.getInstance().setPosition(this, next_tile);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't go that way");
            return;
        }
    }

    public String getShortName() {
        return "B";
    }
}
