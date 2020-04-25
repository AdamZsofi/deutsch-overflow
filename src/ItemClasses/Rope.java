package ItemClasses;

import CLI.Game;
import PlayerClasses.Player;
import GlobalControllers.PositionLUT;
import TileClasses.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Rope extends Item{

    // Tile's getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())

    /**
     * Called from RoundController
     * Player (p) uses Rope
     * If Activity (a) is 'savingPeople'
     * Prompts the user to get a neighbour Tile
     * Places all player (on neighbour Tile) to the callers Tile
     * @param p Player
     * @param a Activity
     */
    @Override
    public void used(Player p, Activity a){
        if(a == Activity.savingPeople) {
            Tile currentTile = PositionLUT.getInstance().getPosition(p); //static, kellene ismerni a PositionLUT peldanyt, singleton lehetne varázsolni?
            Direction wateTile_dir = p.saveDirection;//nem a legszebb, át lehet később gondolni
            Tile waterTile = currentTile.getNeighbour(wateTile_dir);
            ArrayList<Player> inWaterPlayers = PositionLUT.getInstance().getPlayersOnTile(waterTile);
            for (int count=0 ;count<inWaterPlayers.size();count++) { // Player iwp : inWaterPlayers rossz volt
                int step_dir_value = wateTile_dir.getValue();
                step_dir_value += (wateTile_dir.getValue()%2==0 )? 1:-1;
                Direction step_dir = Direction.valueOf(step_dir_value);//oda lép, ahonnan a segítség jött
                inWaterPlayers.get(count).step(step_dir);
            }
            Game.log.println("$ Rope>used : Transaction 'savingPeople' was successful");
        } else {
            Game.log.println("! Rope>used : Activity is not 'savingPeople'");
        }
    }

    /**
     * Gives back a direction (not HERE) based on standard input
     * Prompts the user
     * Uses the a/w/s/d key as narrows to determine the direction given back
     * @return Direction
     */
    private Direction getDir(){
        System.out.println("for directions press button: a, w, s or d");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        switch (s){
            case "a":
                System.out.println("left");
                return Direction.valueOf(2);
            case "w":
                System.out.println("up");
                return Direction.valueOf(0);
            case "s":
                System.out.println("down");
                return Direction.valueOf(1);
            case "d":
                System.out.println("right");
                return Direction.valueOf(3);
            default:
                return Direction.valueOf(2);
        }
    }
    public String getShortName(){ return "R("+state.getShortName(getState())+")";}
    public String toString() {return "Rope";}
}
