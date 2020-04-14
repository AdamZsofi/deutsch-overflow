package TileClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;

import java.util.Random;

/**
 * Class of Tile, the "small portions" of the game board
 * A Tile is indexed by x and y
 * It includes:
 */
public abstract class Tile { // TODO végiggondolni, hogy abstract maradjon-e (stableTile helyett lehetne, az üres)
    /**
     *  @param capacity : max achievable capacity
     *  @param standingHere : the 'actual capacity' (determined by the numOfPlayers on the Tile)
     *  @param snow : amount of snow currently on Tile
     *  @param iglooOn manages Igloo on Tile
     *  @param tenOn manages Tent on Tile
     */
    protected int capacity;
    public int standingHere; // TODO kell, h public legyen? check later

    protected final int x,y;
    protected int snow;
    protected boolean iglooOn;
    public boolean tentOn;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        iglooOn = false;
        Random r = new Random();
        snow = r.nextInt(4) + 1 ;// snow lehet: 1, 2, 3, 4
        capacity = PlayerContainer.getInstance().getPlayerNum(); // ennél nagyobb capacity-nek nincs értelme
        standingHere = 0;
    }

    /**
     * Makes nothing
     * It's relevant for inherited members like unstableTile and SnowyHole redefines this function
     * @param p player
     */
    public void steppedOn(Player p) { // void, cause this method's only job is to set it's object's attributes
        System.out.println("TileClasses.Tile.steppedOn()");
    }

    /**
     * Makes nothing
     * It's relevant for UnstableTile
     * @param dir direction
     */
    public void steppedOff(Direction dir) {
        System.out.println("TileClasses.Tile.steppedOff()");
    }

    /**
     * Called by the Player clearSnow () or Shovel used() or SnowsStorm tryStorm()
     * Changes the amount of snow based on actual snow
     * DIFFERENCE should be given
     * @param thisMuch DIFFERENCE
     */
    public void changeSnow(int thisMuch) {
        System.out.println("TileClasses.Tile.changeSnow(): " + thisMuch);
        //TODO itt visszairni 0-tol csak negativ ertekeket fogadjon el
        if(snow+thisMuch>-4 && snow+thisMuch<=4)
            snow += thisMuch;
    }

    /**
     * Returns the amount of snow
     * @return amount of snow (unit)
     */
    public int getSnow(){
        System.out.println("TileClasses.Tile.getSnow()");
        return snow;
    }

    /**
     * Called by the Researcher
     * Returns the capacity of Tile
     * @return capacity
     */
    public int getCapacity() {
        System.out.println("TileClasses.Tile.getCapacity()");
        return capacity;
    }

    /**
     * Tile gets it's neighbour Tile in a specific direction
     * @param dir direction
     * ERROR HANDLING:
     * @throws IndexOutOfBoundsException, when no neighbour in that direction => SHOULD CHECKED BY THE CALLER
     */
    public Tile getNeighbour(Direction dir) throws IndexOutOfBoundsException {
        System.out.println("TileClasses.Tile.getNeighbour(): " + dir);
        int nx = this.x, ny = this.y;
        switch (dir.valueOf(dir.getValue())) {
            case DOWN:
                nx = x; ny = y - 1;
                break;
            case LEFT:
                nx = x-1; ny = y;
                break;
            case UP:
                nx = x; ny = y + 1;
                break;
            case RIGHT:
                nx = x + 1; ny = y;
                break;
            case HERE:
                return this;
        }
        // Trükk: nem itt kéne lekezelni, ha nincs dir irányba tile, hanem ott, ahol a player input jön (IControllable)
        // Indok: Itt ha újrahívnám ezt a fgv-t, az könnyen végtelen rekurzióba hajszolhatná
        // viszont ha csak pl this-t visszaadok és nem kezelünk semmit feljebb, akkor olyan hibák jöhetnek ki, mint
        // levonódik egy workPoint, de egy helyben marad a player, stb.
        return PositionLUT.getInstance().getTile(nx, ny); // throws IndexOutOfBoundsException if, and only if there's no neighbour
    }

    /**
     * Called by SnowStorm: tryStorm()
     * Destroys Igloo on Tile (iglooOn = false)
     */
    public void destroyIgloo(){
        System.out.println("TileClasses.Tile.destroyIgloo()");
        iglooOn = false;
    }

    /**
     * Called by Eskimo
     * Sets Igloo on Tile (iglooOn = true)
     */
    public void buildIgloo(){
        System.out.println("TileClasses.Tile.buildIgloo()");
        iglooOn = true;
    }
    public boolean getIglooOn(){ return iglooOn;}

    /**
     * @author adam
     * Tent set up (tenOn = true). The parameters of the tent is set. (position and counter for count the live of tent.)
     */
    public void putOnTent(){
        System.out.println("TileClasses.Tile.putOnTent()");
        tentOn=true;
        RoundController.getInstance().tent.counter =PlayerContainer.getInstance().getPlayerNum();
        RoundController.getInstance().tent.x=x;
        RoundController.getInstance().tent.y=y;
    }

    /**
     * For PolarBear moving
     */
    public boolean equals(Tile tile){
        if(x==tile.x && y==tile.y) return true;
        return false;
    }

}
