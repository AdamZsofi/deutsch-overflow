package PlayerClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;
import TileClasses.Direction;

public class Researcher extends Player {

    public void detectCapacity(Direction dir) {
        System.out.print("ResearcherID "+ID+":");
        System.out.println("detectCapacity("+dir+")");

        TileClasses.Tile tile = PositionLUT.pLUT.playerTileMap.get(this);
        int capacity = tile.getSnow();
        System.out.println("Tile capacity: "+capacity);
    };
}
