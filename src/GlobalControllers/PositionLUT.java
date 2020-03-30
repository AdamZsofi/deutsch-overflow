package GlobalControllers;

import ItemClasses.*;
import PlayerClasses.Eskimo;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import PlayerClasses.Researcher;
import TileClasses.*;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PositionLUT {//todo a containerek feltöltése adattal, itemek legyenek mind initelve. kellene majd egy init method
    public static PositionLUT pLUT = new PositionLUT(); //singleton, 1 peldany kell

    public static PositionLUT getInstance() {
        if(pLUT == null) {
            pLUT = new PositionLUT();
        }
        return pLUT;
    }
    /*
    private PositionLUT(){
        itemTileMap = new HashMap<>();
        tileItemMap = new HashMap<>();
        playerTileMap = new HashMap<>();
        tilePlayerMap = new HashMap<>();
        tileList = new ArrayList<>();
        int [][] spawnMatrix = new int[6][6]; //segedmatrix, h ne rakjunk lyukra embert meg itemet, konstruktoron kivulre is mehet

        Random random = new Random();
        for(int y = 0; y<6; y++){               //tileList feltöltése.
            tileList.add(new ArrayList<>());
            for(int x = 0; x<6; x++ ){
                int randNum = random.nextInt(100) + 1;//randNum :1-100
                if( randNum <= 50 ) {
                    tileList.get(y).add(new StableTile(x, y));//50%
                    spawnMatrix[y][x] = 1;
                }else if(randNum<=80) {
                    tileList.get(y).add(new UnstableTile(x, y));//30%
                    spawnMatrix[y][x] = 1;
                }else {
                    tileList.get(y).add(new SnowyHole(x, y));//20%
                    spawnMatrix[y][x] = 0;
                }
            }
        }

        //itt most 36 hosszu, eleg sok a fölösleg, később lehet finomitani, csak akkor az áthelyezéseket kell fokozottan figyelni.
        for(int y = 0; y<6; y++){               //tileItemMap feltöltése. egyenlore uresek az itemek, kezdetben mindenhova max 1
            for(int x = 0; x<6; x++ ){
                tileItemMap.put(tileList.get(y).get(x), new ArrayList<>());
            }
        }

        for(int y = 0; y<6; y++){               //tilePlayerMap feltöltése. egyenlore uresek a playerek, kezdetben mindenhova max 1
            for(int x = 0; x<6; x++ ){
                tilePlayerMap.put(tileList.get(y).get(x), new ArrayList<>());
            }
        }
    }
*/
    public HashMap<Item, Tile> itemTileMap;
    public HashMap<Tile, ArrayList<Item>> tileItemMap; //kövezzetek meg, jo lesz karbantartani 🙂👍 init után put nem lesz ajanlott
    public HashMap<Player, Tile> playerTileMap;
    public HashMap<Tile, ArrayList<Player>> tilePlayerMap;//🙂👍
    public ArrayList<ArrayList<Tile>> tileList;//y: array index, x: Tile index

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
        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Shovel());
        tileItemMap.put(getTile(0,0),items1 );//shovels, 1 tagu lista
        ArrayList<Item> items2 = new ArrayList<>();
        items1.add(new DivingSuit());
        tileItemMap.put(getTile(1,0),items2 );//buvarruha, 1 tagu lista
        ArrayList<Item> items3 = new ArrayList<>();
        items1.add(new Rope());
        tileItemMap.put(getTile(3,0),items3 );//kotel, 1 tagu lista
        ArrayList<Item> items4 = new ArrayList<>();
        items1.add(new Food());
        tileItemMap.put(getTile(0,2),items4 );//alma, 1 tagu lista

        itemTileMap = new HashMap<>();
        itemTileMap.put(items1.get(0), getTile(0, 0));
        itemTileMap.put(items2.get(0), getTile(1, 0));
        itemTileMap.put(items3.get(0), getTile(3, 0));
        itemTileMap.put(items4.get(0), getTile(0, 2));

        playerTileMap = new HashMap<>();
        playerTileMap.put(PlayerContainer.pc.players.get(0),  getTile(2,0));//eskimo1
        playerTileMap.put(PlayerContainer.pc.players.get(1),  getTile(3,1));//eskimo2
        playerTileMap.put(PlayerContainer.pc.players.get(2),  getTile(0,0));//researcher1
        playerTileMap.put(PlayerContainer.pc.players.get(3),  getTile(0,3));//researcher2

        tilePlayerMap = new HashMap<>();
        ArrayList player1 = new ArrayList();
        player1.add(PlayerContainer.pc.players.get(0));
        tilePlayerMap.put(getTile(2,0), player1);
        ArrayList player2 = new ArrayList();
        player1.add(PlayerContainer.pc.players.get(1));
        tilePlayerMap.put(getTile(3,1), player2);
        ArrayList player3 = new ArrayList();
        player1.add(PlayerContainer.pc.players.get(2));
        tilePlayerMap.put(getTile(0,0), player3);
        ArrayList player4 = new ArrayList();
        player1.add(PlayerContainer.pc.players.get(3));
        tilePlayerMap.put(getTile(0,3), player4);



    }

    public Tile getPosition(Player p){
        System.out.println("GlobalControllers.PositionLUT.getPosition(Player p)");

        return playerTileMap.get(p);
    }
    public Tile getPosition(Item i){
        System.out.println("GlobalControllers.PositionLUT.getPosition(Item i)");


        return itemTileMap.get(i);
    }
    public ArrayList<Player> getPlayersOnTile(Tile t){
        System.out.println("GlobalControllers.PositionLUT.getPlayersOnTile(Tile t)");

        return tilePlayerMap.get(t);
    }
    public ArrayList<Item> getItemOnTile(Tile t){
        System.out.println("GlobalControllers.PositionLUT.getItemOnTile(Tile t)");

        return tileItemMap.get(t);
    }
    public Tile getTile(int x, int y){
        System.out.println("GlobalControllers.PositionLUT.getTile(int x, int y)");

        return tileList.get(y).get(x); //indexing convension
    }
    public void setPosition(Player p, Tile t){
        System.out.println("GlobalControllers.PositionLUT.setPosition(Player p, Tile t)");

        tilePlayerMap.get(playerTileMap.get(p)).remove(p);
        tilePlayerMap.get(t).add(p);//uj hely add
        playerTileMap.put(p, t);//put folulirja az elozot
        //todo megnezni jo e?

    }//kell frissiteni: tilePlayerMap, playerTileMap más osztalyokban kell? remelem nem
    public void setPosition(Item i, Tile t){
        System.out.println("GlobalControllers.PositionLUT.setPosition(Item i, Tile t)");

        tileItemMap.get(itemTileMap.get(i)).remove(i);//átlátható. regi hely remove
        tileItemMap.get(t).add(i);//uj hely add
        itemTileMap.put(i, t);//put folulirja az elozot
    }

}
