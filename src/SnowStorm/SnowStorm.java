package SnowStorm;

import GlobalControllers.PositionLUT;

import java.util.Scanner;

public class SnowStorm {
    private PositionLUT posLUT;

    public SnowStorm(PositionLUT pL) {
        posLUT = pL;
    }

    public void tryStorm() {
        System.out.println("Snowstorm.SnowStorm.tryStorm()");
        System.out.println("What should the storm do?");
        System.out.println("(0) Destroy an Igloo");
        System.out.println("(1) Hurt a player");
        System.out.println("(2) Put snow on a tile");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.close();

        switch (choice) {
            case 0:
                System.out.println("Destroying an Igloo...");
                // posLUT.getTile();
                break;
            case 1:

                break;
            case 2:

                break;
            default:
                System.out.println("That is not a valid test number");
                break;
        }
    }
}
