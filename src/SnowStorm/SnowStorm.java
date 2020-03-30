package SnowStorm;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

import java.util.Scanner;

public class SnowStorm {
    private PositionLUT posLUT;

    public SnowStorm(PositionLUT pL) {
        posLUT = pL;
    }

    public void tryStorm() {
        System.out.println("Snowstorm.SnowStorm.tryStorm()");
        System.out.println("What should the storm do? (InGame there will be randomly selected events");
        System.out.println("(0) Destroy an Igloo");
        System.out.println("(1) Hurt a player");
        System.out.println("(2) Put snow on a tile");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.close();

        switch (choice) {
            case 0:
                System.out.println("Destroying an Igloo...");
                posLUT.getTile(2,1).destroyIglu();
                break;
            case 1:
                System.out.println("Hurting a player...");
                for (Player p : posLUT.getPlayersOnTile(posLUT.getTile(2,0))) {
                    p.changeBodyHeat(-1);
                }
                break;
            case 2:
                System.out.println("Putting snow on a tile...");
                posLUT.getTile(2,1).changeSnow(1);
                break;
            default:
                System.out.println("That is not a valid test number");
                break;
        }
    }
}
