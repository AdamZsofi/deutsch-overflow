package SnowStorm;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

import java.util.Scanner;

public class SnowStorm {
    /**
     * Called by the RoundController after a player round
     * Based randomly => befall:
     * On some Tile selected randomly
     * 1) destroys Igloo on them
     * 2) hurts player on them (BodyHeat of players will decreased)
     * 3) puts snow on them
     */
    public void tryStorm() { //TODO rendes mukodes
        PositionLUT pL = PositionLUT.getInstance();
        System.out.println("Snowstorm.SnowStorm.tryStorm()");
        System.out.println("What should the storm do?");
        System.out.println("(1) Destroy an Igloo");
        System.out.println("(2) Hurt a player");
        System.out.println("(3) Put snow on a tile");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.close();

        switch (choice) {
            case 1:
                System.out.println("Destroying an Igloo...");
                pL.getTile(2,1).destroyIgloo();
                break;
            case 2:
                System.out.println("Hurting a player...");
                for (Player p : pL.getPlayersOnTile(pL.getTile(2,0))) {
                    p.changeBodyHeat(-1);
                }
                break;
            case 3:
                System.out.println("Putting snow on a tile...");
                pL.getTile(2,1).changeSnow(1);
                break;
            default:
                System.out.println("That is not a valid test number");
                break;
        }
    }
}
