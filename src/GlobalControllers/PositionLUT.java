package GlobalControllers;

import CLI.Game;
import ItemClasses.*;
import PlayerClasses.*;
import TileClasses.*;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A singleton Look-Up-Table
 * It includes HashMaps between Item - Tiles , Tiles - PLayers ..
 */
public class PositionLUT {
    protected static PositionLUT pLUT;
    /**
     * Gives back the reference of PositionLUT
     */
    public static PositionLUT getInstance() {
        if(pLUT == null) {
            pLUT = new PositionLUT();
        }
        return pLUT;
    }

    private HashMap<Item, Tile> itemTileMap;
    private HashMap<Tile, ArrayList<Item>> tileItemMap; //kövezzetek meg, jo lesz karbantartani 🙂👍 init után put nem lesz ajanlott
    private HashMap<Player, Tile> playerTileMap;
    private HashMap<PolarBear,Tile> polarbearTileMap; // polarbear position
    private HashMap<Tile,ArrayList<PolarBear>> tilePolarBearMap;
    private HashMap<Tile, ArrayList<Player>> tilePlayerMap;//🙂👍
    private ArrayList<ArrayList<Tile>> tileList;//y: array index, x: Tile index

    /**
     * Randomly generated fields are omitted in skeleton, we have a hardcoded map
     * at this phase of testing for the scenarios
     * */
    private PositionLUT(){
        tileList = new ArrayList<>();
        ArrayList<Tile> row1= new ArrayList<>();

        row1.add(new StableTile(0,0));
        row1.add(new StableTile(1,0));
        row1.add(new StableTile(2,0));
        row1.add(new StableTile(3,0));

        ArrayList<Tile> row2= new ArrayList<>();
        row2.add(new UnstableTile(0,1));
        row2.add(new SnowyHole(1,1));
        row2.add(new StableTile(2,1));
        row2.add(new SnowyHole(3,1));

        ArrayList<Tile> row3= new ArrayList<>();
        row3.add(new StableTile(0,2));
        row3.add(new StableTile(1,2));
        row3.add(new StableTile(2,2));
        row3.add(new StableTile(3,2));

        tileList.add(row1);
        tileList.add(row2);
        tileList.add(row3);

        tileItemMap = new HashMap<>();
        tilePlayerMap = new HashMap<>();
        tilePolarBearMap=new HashMap<>();
        for(int y = 0; y<3; y++){//init, h mindenhol legyen
            for(int x = 0; x< 4; x++){
                tilePlayerMap.put(getTile(x,y),new ArrayList<>() );
                tileItemMap.put(getTile(x,y),new ArrayList<>() );
                tilePolarBearMap.put(getTile(x,y),new ArrayList<>());
            }
        }

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Shovel());
        tileItemMap.put(getTile(0,0),items1 );//shovels, 1 tagu lista
        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(new DivingSuit());
        tileItemMap.put(getTile(1,0),items2 );//buvarruha, 1 tagu lista
        ArrayList<Item> items3 = new ArrayList<>();
        items3.add(new Rope());
        tileItemMap.put(getTile(3,0),items3 );//kotel, 1 tagu lista
        ArrayList<Item> items4 = new ArrayList<>();
        items4.add(new Food());
        tileItemMap.put(getTile(0,2),items4 );//alma, 1 tagu lista

        //benne vannak a signalflarepartok is.
        ArrayList<Item> sf1 = new ArrayList<>();
        sf1.add(RoundController.getInstance().sg.signalFlareParts.get(0));
        tileItemMap.put(getTile(1,2),sf1 );//signalflarepart 0ID
        ArrayList<Item> sf2 = new ArrayList<>();
        sf2.add(RoundController.getInstance().sg.signalFlareParts.get(1));
        tileItemMap.put(getTile(2,2),sf2 );//signalflarepart 1ID
        ArrayList<Item> sf3 = new ArrayList<>();
        sf3.add(RoundController.getInstance().sg.signalFlareParts.get(2));
        tileItemMap.put(getTile(2,1),sf3 );//signalflarepart 2ID

        itemTileMap = new HashMap<>();
        itemTileMap.put(items1.get(0), getTile(0, 0));
        itemTileMap.put(items2.get(0), getTile(1, 0));
        itemTileMap.put(items3.get(0), getTile(3, 0));
        itemTileMap.put(items4.get(0), getTile(0, 2));

        itemTileMap.put(RoundController.getInstance().sg.signalFlareParts.get(0), getTile(1,2));
        itemTileMap.put(RoundController.getInstance().sg.signalFlareParts.get(1), getTile(2,2));
        itemTileMap.put(RoundController.getInstance().sg.signalFlareParts.get(2), getTile(2,1));


        playerTileMap = new HashMap<>();
        playerTileMap.put(PlayerContainer.getInstance().getPlayer(0),  getTile(2,0));//eskimo1
        playerTileMap.put(PlayerContainer.getInstance().getPlayer(1),  getTile(3,2));//eskimo2
        playerTileMap.put(PlayerContainer.getInstance().getPlayer(2),  getTile(0,0));//researcher1
        playerTileMap.put(PlayerContainer.getInstance().getPlayer(3),  getTile(0,2));//researcher2

        polarbearTileMap = new HashMap<>();
        polarbearTileMap.put(RoundController.getInstance().polarbear,getTile(1,1)); //polarbear

        ArrayList polarbear= new ArrayList();
        polarbear.add(RoundController.getInstance().polarbear);
        tilePolarBearMap.put(getTile(1,1),polarbear);


        ArrayList player1 = new ArrayList();
        player1.add(PlayerContainer.getInstance().getPlayer(0));
        tilePlayerMap.put(getTile(2,0), player1);
        ArrayList player2 = new ArrayList();
        player2.add(PlayerContainer.getInstance().getPlayer(1));
        tilePlayerMap.put(getTile(3,2), player2);
        ArrayList player3 = new ArrayList();
        player3.add(PlayerContainer.getInstance().getPlayer(2));
        tilePlayerMap.put(getTile(0,0), player3);
        ArrayList player4 = new ArrayList();
        player4.add(PlayerContainer.getInstance().getPlayer(3));
        tilePlayerMap.put(getTile(0,2), player4);

    }

    /**
     * Gives back position (Tile) of a Player (p)
     * @param p Player
     * @return position(Tile)
     */
    public Tile getPosition(Player p){
        //Game.log.println("# PositionLUT>getPosition(Player) returns Tile");
        return playerTileMap.get(p);
    }

    /**
     * Gives back position (Tile) of an Item (i)
     * @param i Item
     * @return position(Tile)
     */
    public Tile getPosition(Item i){
        //Game.log.println("# PositionLUT>getPosition(Item) returns Tile");
        return itemTileMap.get(i);
    }

    /**
     * Gives back all player on a specific Tile
     * @param t position (Tile)
     * @return ArrayList<Item> players of the Tile
     */
    public ArrayList<Player> getPlayersOnTile(Tile t){
        //Game.log.println("# PositionLUT>getPlayersOnTile(Tile) returns ArrayList<Player>");
        return tilePlayerMap.get(t);
    }

    /**
     * Gives back all item on a specific Tile
     * @param t position (Tile)
     * @return ArrayList<Item> items of the Tile
     */
    public ArrayList<Item> getItemOnTile(Tile t){
        //Game.log.println("# PositionLUT>getItemOnTile(Tile) returns ArrayList<Item>");
        return tileItemMap.get(t);
    }

    /**
     * Gets two integers
     * @param x Descartes coord. x (Row)
     * @param y Descartes coord. y (Column)
     * @return position (Tile)
     */
    public Tile getTile(int x, int y){
        //Game.log.format("# PositionLUT>getTile(%d, %d) returns Tile\n", x, y);
        return tileList.get(y).get(x); //indexing convension
    }

    /**
     * Sets position (Tile - t) of the Player (p)
     * @param p Player
     * @param t Tile
     */
    public void setPosition(Player p, Tile t){
        //Game.log.println("# PositionLUT>setPosition(Player, Tile)");
        tilePlayerMap.get(playerTileMap.get(p)).remove(p);
        tilePlayerMap.get(t).add(p);//uj hely add
        playerTileMap.put(p, t);//put folulirja az elozot
        //todo megnezni jo e?
    }//kell frissiteni: tilePlayerMap, playerTileMap más osztalyokban kell? remelem nem

    /**
     * Sets the position (Tile - t) of an Item (i)
     * @param i Item
     * @param t position (Tile)
     */
    public void setPosition(Item i, Tile t){
        //Game.log.println("# PositionLUT>setPosition(Item, Tile)");
        tileItemMap.get(itemTileMap.get(i)).remove(i);//átlátható. regi hely remove
        tileItemMap.get(t).add(i);//uj hely add
        itemTileMap.put(i, t);//put folulirja az elozot
    }

    /**
     * Gives back the position (Tile) of PolarBear
     * @param pb PolarBear
     * @return position(Tile)
     */
    public Tile getPosition(PolarBear pb){
        //Game.log.println("# PositionLUT>getPosition(PolarBear)");
        return polarbearTileMap.get(pb);
    }

    /**
     * Sets the position (Tile - t) of the PolarBear(pb)
     * @param pb PolarBear
     * @param t position(Tile)
     */
    public void setPosition(PolarBear pb, Tile t){
        //Game.log.println("# PositionLUT>setPosition(PolarBear, Tile)");
        tilePolarBearMap.get(polarbearTileMap.get(pb)).remove(pb); //.
        tilePolarBearMap.get(t).add(pb);//uj hely add
        polarbearTileMap.put(pb, t);//put folulirja az elozot
    }
}
