package PlayerClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Direction;
import TileClasses.Tile;

public class Researcher extends Player {

    public Researcher(int id){
        super(id);
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
        System.out.println("! Researcher>detectCapacity : Tile capacity: "+capacity);
    }

    public String getShortName() {
        return "R"+ID;
    }
    public String getInformation(){
        String ih;
        String wear;
        String inwater;
        if(inHand!=null)
            ih=inHand.getShortName();
        else
            ih="-";
        if(wearing!=null)
            wear="+";
        else
            wear="-";
        if(inWater)
            inwater="+";
        else
            inwater="-";
        return "( "+getShortName()+ ":   InHand "+ih+", Wear: "+wear+", "+"Temp: "+BodyHeat+", inWater: "+inwater+" )";
    }
}
