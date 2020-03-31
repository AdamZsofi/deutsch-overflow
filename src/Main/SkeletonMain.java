package Main;

import GlobalControllers.*;
import PlayerClasses.*;
import SnowStorm.*;
import ItemClasses.*;
import TileClasses.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SkeletonMain {

    public static void main(String[] args) {
        PlayerContainer.Initialize(4);
        RoundController.getInstance(); //letrehoz
        PositionLUT.getInstance();



        System.out.println("Choose a scene you want to test:");
        System.out.println("(0) ");
        System.out.println("(1) PlayerSteps");
        System.out.println("(2) TileSteppedOff");
        System.out.println("(3) SnowyHoleSteppedOn");
        System.out.println("(4) UnstableTileSteppedOn");
        System.out.println("(5) StableTileSteppedOn");
        System.out.println("(6) PlayerPicksItemUp");
        System.out.println("(7) PlayerClearsSnow");
        System.out.println("(8) PlayerSavesPlayers");
        System.out.println("(9) PutSignalFlareTogether");
        System.out.println("(10) PlayerAteFood");
        System.out.println("(11) PlayerWearsDivingSuit");
        System.out.println("(12) RoundPassing");
        System.out.println("(13) SnowStorimg");
        System.out.println("(14) EskimoBuildsIglu");
        System.out.println("(15) ResearcherDetectsCapacity");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        //scan.close();

        switch (choice) {
            case 0:
                ///
                break;
            case 1:
                playerStepsSEQ();
                break;
            case 2:
                //tileSteppedOffSEQ();
                break;
            case 3:
                snowyHoleSteppedOnSEQ();
                break;
            case 4:
                unstableTileSteppedOnSEQ();
                break;
            case 5:
                stableTileSteppedOnSEQ();
                break;
            case 6:
                playerPickItemUpSEQ();
                break;
            case 7:
                playerClearsSnowSEQ();
                break;
            case 8:
                playerSavesPlayersSEQ();
                break;
            case 9:
                putSignalFlareTogetherSEQ();
                break;
            case 10:
                playerAteFoodSEQ();
                break;
            case 11:
                playerWearsDivingSuitSEQ();
                break;
            case 12:
                roundPassingSEQ();
                break;
            case 13:
                snowStormingSEQ();
                break;
            case 14:
                eskimoBuildsIgluSEQ();
                break;
            case 15:
                researcherDetectsCapacitySEQ();
                break;
            default:
                System.out.println("That is not a valid test number");
                break;
        }
    }
    static void playerStepsSEQ() {
        System.out.println("@Player steps");
        //a)
        System.out.println("#Init(1)");
        Player researcher1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        System.out.println("#Player steps right");
        researcher1.step(Direction.right);

        //b)
        System.out.println("#Player steps left");
        researcher1.step(Direction.left);

        //c)
        System.out.println("#Init(2)");
        Player eskimo1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        System.out.println("#Player steps up");
        eskimo1.step(Direction.up);

        //d)
        System.out.println("#Player steps down");
        eskimo1.step(Direction.down);
    }
        /*
    static void tileSteppedOffSEQ() {
        System.out.println("@TileSteppedOff");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(2,0);
        Player p = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        System.out.println("#TileSteppedOff");
        t.steppedOff(p);
        System.out.println("-----");
        ArrayList<Player> plys = PositionLUT.getInstance().getPlayersOnTile(t);
        if (plys == null) System.out.println("#Passed");
    }
*/
    static void snowyHoleSteppedOnSEQ() {
        System.out.println("@SnowyHole SteppedOn");
        System.out.println("#Init");
        Player p = new Researcher();
        Tile t = PositionLUT.getInstance().getTile(1,1);
        System.out.println("#SnowyHole SteppedOn");
        t.steppedOn(p);
        System.out.println("-----");
        ArrayList<Player> plys = PositionLUT.getInstance().getPlayersOnTile(t);
        if (plys.size() == 1) System.out.println("#Passed");
    }

    static void unstableTileSteppedOnSEQ() {
        System.out.println("@Unstable Tile SteppedOn");
        System.out.println("#Init");
        Player p = new Researcher();
        Tile t = PositionLUT.getInstance().getTile(0,1);
        System.out.println("#Unstable Tile SteppedOn");
        t.steppedOn(p);
        System.out.println("-----");
        ArrayList<Player> plys = PositionLUT.getInstance().getPlayersOnTile(t);
        if (plys.size() == 1) System.out.println("#Passed");
    }
    static void stableTileSteppedOnSEQ() {
        System.out.println("@Stable Tile SteppedOn");
        System.out.println("#Init");
        Player p = new Researcher();
        Tile t = PositionLUT.getInstance().getTile(2,1);
        System.out.println("#Stable Tile SteppedOn");
        t.steppedOn(p);
        System.out.println("-----");
        ArrayList<Player> plys = PositionLUT.getInstance().getPlayersOnTile(t);
        if (plys.size() == 1) System.out.println("#Passed");
    }

    static void playerPickItemUpSEQ() {
        System.out.println("@Player picks item up");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t); //Items on Tile(0, 0)
        Player p = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        System.out.println("#Player picks item up");
        p.pickUp(its.get(0)); //researcher1 gets showel
    }

    static void playerClearsSnowSEQ() {
        System.out.println("@Player clears snow");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
        //a)
        System.out.println("#Init(1)");
        Player p = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        System.out.println("#Player clears snow without shovel");
        p.clearSnow();

        //b
        System.out.println("#Init(2)");
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t);
        PositionLUT.getInstance().getPlayersOnTile(t).get(0).pickUp(its.get(0)); //researcher1 gets showel
        System.out.println("#Player clears snow with shovel");
        PositionLUT.getInstance().getPlayersOnTile(t).get(0).clearSnow();
    }

    static void playerSavesPlayersSEQ() {
        System.out.println("@Player saves players");
        System.out.println("#Init");
        Player eskimo1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        eskimo1.step(Direction.right);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(PositionLUT.getInstance().getTile(3,0));
        eskimo1.pickUp(its.get(0)); //eskimo1 picks up Rope

        Player eskimo2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(3,2)).get(0);
        eskimo2.step(Direction.down); //eskimo2 steps in snowyhole
        System.out.println("#Player saves players");
        eskimo1.savePlayers(Direction.up);
        System.out.println("----");
        if (PositionLUT.getInstance().getPosition(eskimo2) == PositionLUT.getInstance().getPosition(eskimo1))
            System.out.println("#Passed");
    }

    static void putSignalFlareTogetherSEQ() {
        System.out.println("@Put SignalFlare together");
        System.out.println("#Init");
        Player eskimo1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        eskimo1.step(Direction.up);    //eskimo1 to Tile (2,1)

        Player researcher1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        researcher1.step(Direction.right);
        researcher1.step(Direction.right);
        researcher1.step(Direction.up);  //researcher1 to Tile (2, 1)

        Player eskimo2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(3,2)).get(0);
        eskimo2.step(Direction.left);
        eskimo2.step(Direction.down); //eskimo2 to Tile (2, 1)

        Player researcher2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,2)).get(0);
        researcher2.step(Direction.right);
        ArrayList<Item> sgf1 = PositionLUT.getInstance().getItemOnTile(PositionLUT.getInstance().getTile(1,2));
        researcher2.pickUp(sgf1.get(0)); // one sgf collected
        researcher2.step(Direction.right);
        ArrayList<Item> sgf2 = PositionLUT.getInstance().getItemOnTile(PositionLUT.getInstance().getTile(1,2));
        researcher2.pickUp(sgf2.get(0)); // one sgf collected
        researcher2.step(Direction.down); //researcher2 to Tile (2, 1)
        //one sgf is on Tile (2, 1) thwrownDown

        ArrayList<Player> pls = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,1)); //all Players on Tile (2, 1)

        System.out.println("#Put SignalFlare together");
        researcher1.putSignalTogether(RoundController.getInstance().sg);
        System.out.println("----");
        if (pls.size()==4) System.out.println("#Passed");//meg mast is kell ellenorizni!!!
    }

    static void playerAteFoodSEQ() {
        System.out.println("@Player ate food");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(0,2);
        Player researcher2 = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t);
        System.out.println("#Player ate food");
        researcher2.pickUp(its.get(0)); // researcher2 ates apple
    }

    static void playerWearsDivingSuitSEQ() {
        System.out.println("@Player wears DivingSuit");
        System.out.println("#Init");
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(PositionLUT.getInstance().getTile(2,1)); // divingsuit
        Player researcher1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        researcher1.step(Direction.right);
        System.out.println("#Player wears DivingSuit");
        researcher1.pickUp(its.get(0)); //researcher1 steps (right) a tile and picks DivingSuitUp
    }

    static void roundPassingSEQ() {
        System.out.println("@Round passing");
        System.out.println("#Init");
        Player p = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        System.out.println("#Round passing");
        p.passRound();
    }

    static void snowStormingSEQ() { // Storm tesztje, az aleseteket a tryStorm-ban választjuk ki
        System.out.println("@Snowstorming");
        SnowStorm ss = new SnowStorm();
        // In case of an igloo destroying storm, we need an igloo first, we initialize that here
        // This is a "syntetic use" of our classes, normally you don't build an igloo outside of the round of an eskimo
        System.out.println("Initializing an Igloo for the test case 'destroying igloo'...");
        PositionLUT.getInstance().getTile(2,1).buildIglu();
        ss.tryStorm();
    }

    static void eskimoBuildsIgluSEQ() {
        System.out.println("@Eskimo builds Iglu");
        System.out.println("#Init");
        Eskimo eskimo1 = (Eskimo) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        System.out.println("#Eskimo builds Iglu");
        eskimo1.buildIgloo(); //eskimo1 builds Igloo
    }

    static void researcherDetectsCapacitySEQ() {
        System.out.println("@Researcher detects capacity");
        System.out.println("#Init(1)");
        Researcher researcher1 = (Researcher) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        System.out.println("#Researcher mesaures stable Tile");
        researcher1.detectCapacity(Direction.right);//researcher1 mesaures stable Tile

        System.out.println("#Researcher mesaures unstable Tile");
        researcher1.detectCapacity(Direction.up); //researcher1 mesaures unstable Tile

        System.out.println("#Init(2)");
        researcher1.step(Direction.right);

        System.out.println("#Researcher mesaures SnowyHole");
        researcher1.detectCapacity(Direction.up); // researcher1 measures SnowyHole
    }
}

