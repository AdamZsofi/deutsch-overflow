package TileClasses;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

import java.util.Random;

public abstract class Tile {
    protected int x,y; //x, y final lehetne --> public is lehetne
    protected int snow;
    private boolean igluOn;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        igluOn = false;
        Random r = new Random();
        snow = r.nextInt(4) + 1 ;//snow lehet: 1, 2, 3, 4
    }


    public void steppedOn(Player p){ //class diagramban void seq-ben Tile a return
//todo
    }
    public void steppedOff(Direction dir) {
//todo
    }
    public int changeSnow(int thisMuch){ //hasznaljuk valamire a returnt?
        System.out.println("TileClasses.Tile.changeSnow(): " + thisMuch);


        if(snow+thisMuch>0)
            snow += thisMuch;
        else
            snow = 0;
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
        return PositionLUT.pLUT.getTile(nx, ny);//todo kéne rossz indexelest nezni
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
