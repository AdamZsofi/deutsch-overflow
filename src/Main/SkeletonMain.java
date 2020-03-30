package Main;

import GlobalControllers.PositionLUT;
import PlayerClasses.Eskimo;
import SnowStorm.SnowStorm;
import java.util.Scanner;

public class SkeletonMain {

    public static void main(String[] args) {
        // TODO RoundController init here
        PositionLUT.getInstance();
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
        SnowStorm ss = new SnowStorm();
        // In case of an igloo destroying storm, we need an igloo first, we initialize that here
        // This is a "syntetic use" of our classes, normally you don't build an igloo outside of the round of an eskimo
        System.out.println("Initializing an Igloo for the test case 'destroying igloo'...");
        PositionLUT.getInstance().getTile(2,1).buildIglu();
        ss.tryStorm();
    }
}

