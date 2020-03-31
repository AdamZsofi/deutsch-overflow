package PlayerClasses;

import GlobalControllers.PositionLUT;
import TileClasses.Tile;

public class Eskimo extends Player {

    public void buildIgloo() {
        System.out.print("PlayerClasses.Eskimo, ID"+ID+":");
        System.out.println("buildIgloo()");
        Tile tile = PositionLUT.pLUT.getPosition(this);
        tile.buildIglu();
    }
}
