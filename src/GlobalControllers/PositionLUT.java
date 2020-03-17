package GlobalControllers;

import ItemClasses.Item;
import PlayerClasses.Player;
import TileClasses.Tile;

import java.util.ArrayList;

public class PositionLUT {

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
    public void setPosition(Player p);
    public void setPosition(Item i);
}
