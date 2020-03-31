package PlayerClasses;

import GlobalControllers.PositionLUT;

public class Eskimo extends Player {

    public void buildIgloo() {
        System.out.print("PlayerClasses.Eskimo, ID"+ID+":");
        System.out.println("buildIgloo()");

        TileClasses.Tile tile = PositionLUT.pLUT.playerTileMap.get(this);
        tile.buildIglu();
    };
}
