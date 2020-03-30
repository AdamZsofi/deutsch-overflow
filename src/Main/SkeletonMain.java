package Main;

import GlobalControllers.PositionLUT;
import SnowStorm.SnowStorm;

import java.util.Scanner;

public class SkeletonMain {
    // TODO RoundController init here
    static PositionLUT positionLUT = PositionLUT.getInstance();

    public static void main(String[] args) {

        // nem rakok ide végtelen ciklust, egy lefutás = egy scene tesztje
        System.out.println("Choose a scene you want to test:");
        System.out.println("(0) Example test");
        System.out.println("(2) Test Storm");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.close();

        switch (choice) {
            case 0:
                exampleTest();
                break;
            case 2:
                testStorm();
                break;
            default:
                System.out.println("That is not a valid test number");
                break;
        }
    }

    static void exampleTest() { // minden scene-hez egy ilyen static fgv mehet a SkeletonMain-be
        System.out.println("Hi, I am an example test, I do nothing.");
    }

    static void testStorm() { // Storm tesztje, az aleseteket a tryStorm-ban választjuk ki
        SnowStorm ss = new SnowStorm(positionLUT);
    }
}

