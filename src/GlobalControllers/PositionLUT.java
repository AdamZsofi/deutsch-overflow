package GlobalControllers;

import ItemClasses.Item;
import PlayerClasses.Player;
import TileClasses.*;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PositionLUT {//todo a containerek feltöltése adattal, itemek legyenek mind initelve. kellene majd egy init method
    public static PositionLUT pLUT = new PositionLUT(); //singleton, 1 peldany kell

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

    public HashMap<Item, Tile> itemTileMap;
    public HashMap<Tile, ArrayList<Item>> tileItemMap; //kövezzetek meg, jo lesz karbantartani 🙂👍 init után put nem lesz ajanlott
    public HashMap<Player, Tile> playerTileMap;
    public HashMap<Tile, ArrayList<Player>> tilePlayerMap;//🙂👍
    public ArrayList<ArrayList<Tile>> tileList;//y: array index, x: Tile index

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
    public void setPosition(Player p, Tile t){//adtam neki Tile parametert is, habár lehetne dir is
        System.out.println("GlobalControllers.PositionLUT.setPosition(Player p, Tile t)");


        //igazabol lehetne Direction dir a Tile t helyett, és akkor:
        //Tile t = playerTileMap.get(p).getNeighbour(dir);
        //igy a Tile.steppedOn és Tile.steppedOff folosleges (e nélkül is kb az)
        tilePlayerMap.get(playerTileMap.get(p)).remove(p);//átlátható. regi hely remove
        tilePlayerMap.get(t).add(p);//uj hely add
        playerTileMap.put(p, t);//put folulirja az elozot

    }//kell frissiteni: tilePlayerMap, playerTileMap más osztalyokban kell? remelem nem
    public void setPosition(Item i, Tile t){
        System.out.println("GlobalControllers.PositionLUT.setPosition(Item i, Tile t)");


        tileItemMap.get(itemTileMap.get(i)).remove(i);//átlátható. regi hely remove
        tileItemMap.get(t).add(i);//uj hely add
        itemTileMap.put(i, t);//put folulirja az elozot
    }

}
