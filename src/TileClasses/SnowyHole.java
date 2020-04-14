package TileClasses;

import PlayerClasses.Player;

public class SnowyHole extends Tile {

    public SnowyHole(int x, int y) {
        super(x, y);
        capacity = 0;
    }

    /**
     * Sets Igloo parameter of Tile to FALSE (iglooOn = FALSE)
     */
    @Override
    public void destroyIgloo(){
        System.out.println("TileClasses.SnowyHole.destroyIgloo(), you shouldn't be able to call this inGame!");
        iglooOn = false;
    }

    /**
     * Sets Igloo parameter of Tile to TRUE (iglooOn = TRUE)
     */
    @Override
    public void buildIgloo(){
        System.out.println("TileClasses.SnowyHole.buildIgloo(), you shouldn't be able to call this inGame!");
        iglooOn = false;
    }

    /**
     * If Player steps on a SnowyHole, falls in water
     * @param p player
     */
    @Override
    public void steppedOn(Player p) {
        System.out.println("TileClasses.SnowyHole.steppedOn()");
        p.fallInWater();
    }
}
