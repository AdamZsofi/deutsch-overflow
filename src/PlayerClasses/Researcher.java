package PlayerClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Direction;
import TileClasses.Tile;

public class Researcher extends Player {

    public void detectCapacity(Direction dir) {
        System.out.print("ResearcherID "+ID+":");
        System.out.println("detectCapacity("+dir+")");

        // getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
        Tile thisTile = PositionLUT.getInstance().getPosition(this);
        Tile checkedTile = thisTile.getNeighbour(dir);
        int capacity = checkedTile.getCapacity();
        System.out.println("Tile capacity: "+capacity);
    };
}
