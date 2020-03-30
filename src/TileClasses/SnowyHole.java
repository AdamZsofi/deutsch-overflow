package TileClasses;

import PlayerClasses.Player;

public class SnowyHole extends Tile {

    public SnowyHole(int x, int y) {
        super(x, y);
    }

    @Override
    public void destroyIglu(){
        System.out.println("TileClasses.SnowyHole.destroyIglu(), you shouldn't be able to call this inGame!");
        igluOn = false;
    }

    @Override
    public void buildIglu(){
        System.out.println("TileClasses.SnowyHole.buildIglu(), you shouldn't be able to call this inGame!");
        igluOn = false;
    }

    @Override
    public void steppedOn(Player p) {
        System.out.println("TileClasses.SnowyHole.steppedOn()");
        p.fallInWater();
    }
}
