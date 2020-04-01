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
            Direction wateTile_dir = Direction.up;///getDir();//csak egy gyors pelda, megadja milyen irányban van a waterTile
            Tile waterTile = currentTile.getNeighbour(wateTile_dir);
            ArrayList<Player> inWaterPlayers = PositionLUT.getInstance().getPlayersOnTile(waterTile);
            for (int count=0 ;count<inWaterPlayers.size();count++) { // Player iwp : inWaterPlayers rossz volt
                Direction step_dir = Direction.down;//getDir(); //melyik irányba szeretnénk menteni
                    inWaterPlayers.get(count).step(step_dir);
            }
        }
    }

    private Direction getDir(){
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
