package ItemClasses;

import PlayerClasses.Player;
import GlobalControllers.PositionLUT;
import TileClasses.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Rope extends Item{

    // Tile's getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.Rope.used()");


        if(a == Activity.savingPeople) {
            Tile currentTile = PositionLUT.getInstance().getPosition(p); //static, kellene ismerni a PositionLUT peldanyt, singleton lehetne varázsolni?
            Direction dir = getDir();//csak egy gyors pelda
            Tile waterTile = currentTile.getNeighbour(dir);
            ArrayList<Player> inWaterPlayers = PositionLUT.getInstance().getPlayersOnTile(waterTile);
            for (Player iwp:inWaterPlayers) {
                if(iwp.inWater){
                    dir = getDir();
                    iwp.step(dir);//mi van, ha nem lehet arra lepni? hibakezeles, ujra, esetleg mint masik oldat folytatja mint snake
                                  //stepen belul lehetne lekezelni
                }
            }
        }
    }

    private Direction getDir(){ //csak most csinaltam, lehessen iranyitani
        System.out.println("for directions press button: a, w, s or d");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        switch (s){
            case "a":
                System.out.println("left");
                return Direction.left;
            case "w":
                System.out.println("up");
                return Direction.up;
            case "s":
                System.out.println("down");
                return Direction.down;
            case "d":
                System.out.println("right");
                return Direction.right;
            default:
                return Direction.left;
        }
    }

}
