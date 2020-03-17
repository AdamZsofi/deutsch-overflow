package TileClasses;

import PlayerClasses.Player;

public abstract class Tile {
    protected int x,y,snow;
    private boolean igluOn;

    public void steppedOn(Player p);
    public void steppedOff(Direction dir);
    public int changeSnow(int thisMuch);
    public Tile getNeighbour(Direction dir);
    public void destroyIglu();
    public void buildIglu();
}
