package PlayerClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Direction;
import TileClasses.Tile;

public class Researcher extends Player {

    public Researcher(){
        super();
        BodyHeat=4;
    }
    /**
     * Special function of the player
     * Researcher is able to detect the capacity of a Tile in a given direction
     * It prints out the capacity of the neighbour Tile
     * ERROR HANDLING: dir should be checked before the call of this method (if tile is OutOfBound)
     * @param dir direction
     */
    public void detectCapacity(Direction dir) {
        Game.log.format("# Researcher>detectCapacity started by Player %d\n", ID);

        //TODO getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
        Tile thisTile = PositionLUT.getInstance().getPosition(this);
        Tile checkedTile = thisTile.getNeighbour(dir);
        int capacity = checkedTile.getCapacity();
        Game.log.println("$ Researcher>detectCapacity : Transaction 'detectCapacity' is completed");
        System.out.println("Tile capacity: "+capacity);
    }

    public String getShortName() {
        return "R"+ID;
    }
}
