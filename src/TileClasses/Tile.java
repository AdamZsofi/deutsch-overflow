package TileClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;

import java.util.Random;

public abstract class Tile { // TODO végiggondolni, hogy abstract maradjon-e (stableTile helyett lehetne, az üres)
    protected int capacity;
    public int standingHere; // TODO kell, h public legyen? check later

    protected final int x,y;
    protected int snow;
    protected boolean igluOn;
    public boolean tentOn;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        igluOn = false;
        Random r = new Random();
        snow = r.nextInt(4) + 1 ;// snow lehet: 1, 2, 3, 4
        capacity = PlayerContainer.getInstance().getPlayerNum(); // ennél nagyobb capacity-nek nincs értelme
        standingHere = 0;
    }

    public void steppedOn(Player p) { // void, cause this method's only job is to set it's object's attributes
        System.out.println("TileClasses.Tile.steppedOn()");
        // nothing to do here, this method is relevant at unstableTiles and snowyHoles
    }
    public void steppedOff(Direction dir) {
        System.out.println("TileClasses.Tile.steppedOff()");
        // nothing to do here, this method is relevant at unstableTiles
    }
    public void changeSnow(int thisMuch) {
        System.out.println("TileClasses.Tile.changeSnow(): " + thisMuch);
        //igen ezt itt visszairni 0-tol
        if(snow+thisMuch>-4 && snow+thisMuch<=4)
            snow += thisMuch;
    }
    public int getSnow(){
        System.out.println("TileClasses.Tile.getSnow()");
        return snow;
    }

    public int getCapacity() {
        System.out.println("TileClasses.Tile.getCapacity()");
        return capacity;
    }

    public Tile getNeighbour(Direction dir) throws IndexOutOfBoundsException {
        System.out.println("TileClasses.Tile.getNeighbour(): " + dir);
        int nx = this.x, ny = this.y;
        switch (dir.getValue()) {
            case 1:
                nx = x; ny = y - 1;
                break;
            case 2:
                nx = x-1; ny = y;
                break;
            case 0:
                nx = x; ny = y + 1;
                break;
            case 3:
                nx = x + 1; ny = y;
                break;
            case 4:
                return this;
        }
        // Trükk: nem itt kéne lekezelni, ha nincs dir irányba tile, hanem ott, ahol a player input jön (IControllable)
        // Indok: Itt ha újrahívnám ezt a fgv-t, az könnyen végtelen rekurzióba hajszolhatná
        // viszont ha csak pl this-t visszaadok és nem kezelünk semmit feljebb, akkor olyan hibák jöhetnek ki, mint
        // levonódik egy workPoint, de egy helyben marad a player, stb.
        return PositionLUT.getInstance().getTile(nx, ny); // throws IndexOutOfBoundsException if, and only if there's no neighbour
    }

    public void destroyIglu(){
        System.out.println("TileClasses.Tile.destroyIglu()");
        igluOn = false;
    }
    public void buildIglu(){
        System.out.println("TileClasses.Tile.buildIglu()");
        igluOn = true;
    }
    public boolean getIgluOn(){ return igluOn;}

    /**
     * @author adam
     * Tent set up. The parameters of the tent is set. (position and counter for count the live of tent.)
     */
    public void putOnTent(){
        System.out.println("TileClasses.Tile.putOnTent()");
        tentOn=true;
        RoundController.getInstance().tent.counter =PlayerContainer.getInstance().getPlayerNum();
        RoundController.getInstance().tent.x=x;
        RoundController.getInstance().tent.y=y;
    }

    /**
     * for polarbear moving
     */
    public boolean equals(Tile tile){
        if(x==tile.x && y==tile.y) return true;
        return false;
    }

}
