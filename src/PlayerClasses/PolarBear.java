package PlayerClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import TileClasses.Direction;
import TileClasses.Tile;

public class PolarBear extends Character{
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
}
