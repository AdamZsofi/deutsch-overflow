package TileClasses;

import PlayerClasses.Player;

public class SnowyHole extends Tile {

    public SnowyHole(int x, int y) {
        super(x, y);
        capacity = 0;
    }

    /**
     * Sets Iglu parameter of Tile to FALSE (igluOn = FALSE)
     */
    @Override
    public void destroyIglu(){
        System.out.println("TileClasses.SnowyHole.destroyIglu(), you shouldn't be able to call this inGame!");
        igluOn = false;
    }

    /**
     * Sets Iglu parameter of Tile to TRUE (igluOn = TRUE)
     */
    @Override
    public void buildIglu(){
        System.out.println("TileClasses.SnowyHole.buildIglu(), you shouldn't be able to call this inGame!");
        igluOn = false;
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
