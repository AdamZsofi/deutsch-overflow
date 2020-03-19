package GlobalControllers;

import ItemClasses.Item;
import PlayerClasses.Player;
import TileClasses.Direction;
import TileClasses.StableTile;
import TileClasses.Tile;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.ArrayList;

public class PositionLUT {
    public static PositionLUT i = new PositionLUT(); //singleton, 1 peldany kell

    public ArrayList<Item> itemTileMap;
    public ArrayList<Item> tileItemMap;
    public ArrayList<Player> playerTileMap;
    public ArrayList<Player> tilePlayerMap;
    public ArrayList<Tile> tileList;

    public Tile getPosition(Player p);
    public Tile getPosition(Item i);
    public ArrayList<Player> getPlayersOnTile(Tile t);
    public ArrayList<Item> getItemOnTile(Tile t);
    public Tile getTile(int x, int y);
    public void setPosition(Player p);//adtam neki Tile parametert is
    public void setPosition(Item i);
}
