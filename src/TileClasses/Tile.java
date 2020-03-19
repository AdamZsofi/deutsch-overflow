package TileClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

public abstract class Tile {
    protected int x,y,snow;
    private boolean igluOn;

    public void steppedOn(Player p){
//todo
    }
    public void steppedOff(Direction dir) {
//todo
    }
    public int changeSnow(int thisMuch){ //hasznaljuk valamire a returnt?
        System.out.println("TileClasses.Tile.changeSnow(): " + thisMuch);


        snow += thisMuch;//todo ne lehessen túlásni.
        return snow;
    }
    public Tile getNeighbour(Direction dir){
        System.out.println("TileClasses.Tile.getNeighbour(): " + dir);


        int nx, ny;
        switch (dir) {
            case down:
                nx = x-1; ny = y;
                break;
            case left:
                nx = x; ny = y-1;
                break;
            case up:
                nx = x+1; ny = y;
                break;
            case right:
                nx = x; ny = y+1;
                break;
            default:
                nx= x; ny= y;
        }
        return PositionLUT.i.getTile(nx, ny);//todo kéne rossz indexelest nezni
    }
    public void destroyIglu(){
        System.out.println("TileClasses.Tile.destroyIglu()");


        igluOn = false;
    }
    public void buildIglu(){
        System.out.println("TileClasses.Tile.buildIglu()");


        igluOn = true;
    }
}
