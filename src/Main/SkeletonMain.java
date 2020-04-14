package Main;

import GlobalControllers.*;
import PlayerClasses.*;
import SnowStorm.*;
import ItemClasses.*;
import TileClasses.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Test class for sequences
 */
public class SkeletonMain {

    public static void main(String[] args) {
        PlayerContainer.Initialize(4);
        RoundController.getInstance(); //letrehoz
        PositionLUT.getInstance();

        System.out.println("Choose a scene you want to test:");
        System.out.println("(1) PlayerSteps");
        System.out.println("(2) TileSteppedOff");
        System.out.println("(3) SnowyHoleSteppedOn");
        System.out.println("(4) UnstableTileSteppedOn");
        System.out.println("(5) PlayerDigsItemUp"); //StableTile extra
        System.out.println("(6) PlayerPicksItemUp");
        System.out.println("(7) PlayerClearsSnow");
        System.out.println("(8) PlayerSavesPlayers");
        System.out.println("(9) PutSignalFlareTogether");
        System.out.println("(10) PlayerAteFood");
        System.out.println("(11) PlayerWearsDivingSuit");
        System.out.println("(12) RoundPassing");
        System.out.println("(13) SnowStorming");
        System.out.println("(14) EskimoBuildsIgloo");
        System.out.println("(15) ResearcherDetectsCapacity");

        Scanner scan = new Scanner(System.in);

        //scan.close();

        switch (scan.nextInt()) {
            case 1:
                playerStepsSEQ();
                break;
            case 2:
                tileSteppedOffSEQ(); //NEM SOK MINDENT CSINAL DE LEGYEN
                break;
            case 3:
                snowyHoleSteppedOnSEQ();
                break;
            case 4:
                unstableTileSteppedOnSEQ();
                break;
            case 5:
                playerDigsItemUpSEQ();
                //stableTileSteppedOnSEQ(); EXTRA
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
                eskimoBuildsIglooSEQ();
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
        System.out.println("@Player steps (4 cases)");
        System.out.println("(1) Player steps inBound");
        System.out.println("(2) Player steps outBound");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextInt()) {
            case 1:
                System.out.println("#Init(1)");
                Player p1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
                System.out.println();
                System.out.println("#   (1)     Player steps inBound");
                p1.step(Direction.valueOf(3)); //researcher1 steps right 3: right
                break;
            case 2:
                System.out.println("#Init(2)");
                Player p2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
                System.out.println();
                System.out.println("#   (2)     Player steps outBound");
                p2.step(Direction.valueOf(1)); //eskimo1 steps up 1: down
                break;
        }
    }

    static void tileSteppedOffSEQ() {
        System.out.println("@TileSteppedOff");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(2,0);
        System.out.println();
        System.out.println("#      TileSteppedOff");
        t.steppedOff(Direction.valueOf(0)); //up:0
    }

    static void snowyHoleSteppedOnSEQ() {
        System.out.println("@SnowyHole SteppedOn");
        System.out.println("#Init");
        Player p = new Researcher();
        Tile t = PositionLUT.getInstance().getTile(1,1);
        System.out.println();
        System.out.println("#      SnowyHole SteppedOn");
        t.steppedOn(p);
    }

    static void unstableTileSteppedOnSEQ() {
        System.out.println("@Unstable Tile SteppedOn");
        System.out.println("#Init");
        Player p = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        Player p2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,2)).get(0);
        Tile t = PositionLUT.getInstance().getTile(0,1);
        p.step(Direction.valueOf(0));
        p2.step(Direction.valueOf(1));
        System.out.println("^^Here 2 players fall in water, but vv for SteppedOn scenario(again)");
        System.out.println();
        System.out.println("#   Unstable Tile SteppedOn");
        t.steppedOn(p2);
    }

    static void playerDigsItemUpSEQ() {
        System.out.println("@Player digs item up");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(0, 0);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t); //Items on Tile(0, 0)
        Player p = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        System.out.println();
        System.out.println("#       Player digs item up");
        p.digItemUp(its.get(0));
    }
    static void playerPickItemUpSEQ() {
        System.out.println("@Player picks item up");
        System.out.println("(1) Player cannot pickUp item, because it's frozen");
        System.out.println("(2) Player pickUp item");
        System.out.println("(3) Player pickUp item and throws the item down, which already picked up");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextInt()){
            case 1:
                System.out.println("#Init(1)");
                Tile t1 = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
                ArrayList<Item> its1 = PositionLUT.getInstance().getItemOnTile(t1); //Items on Tile(0, 0)
                Player p1 = PositionLUT.getInstance().getPlayersOnTile(t1).get(0);
                System.out.println();
                System.out.println("#       (1)        Player cannot pickUp item, because it's frozen");
                p1.pickUp(its1.get(0)); //researcher1 not get's shovel, because it's frozen
                break;
            case 2:
                System.out.println("#Init (2)");
                Tile t2 = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
                ArrayList<Item> its2 = PositionLUT.getInstance().getItemOnTile(t2); //Items on Tile(0, 0)
                Player p2 = PositionLUT.getInstance().getPlayersOnTile(t2).get(0);
                t2.changeSnow(-t2.getSnow());
                System.out.println();
                System.out.println("#      (2)         Player picks item up");
                p2.pickUp(its2.get(0)); //researcher1 gets shovel
                break;
            case 3:
                System.out.println("#Init (3)");
                Tile t3 = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
                ArrayList<Item> its3 = PositionLUT.getInstance().getItemOnTile(t3); //Items on Tile(0, 0)
                Player p3 = PositionLUT.getInstance().getPlayersOnTile(t3).get(0);
                t3.changeSnow(-t3.getSnow());
                p3.pickUp(its3.get(0)); //researcher1 gets shovel
                p3.step(Direction.valueOf(0));
                p3.step(Direction.valueOf(0));
                Tile t3_2 = PositionLUT.getInstance().getTile(0,2);  //Tile (0, 2)
                ArrayList<Item> its3_2 = PositionLUT.getInstance().getItemOnTile(t3_2); //Items on Tile(0, 2)
                t3_2.changeSnow(-t3_2.getSnow());
                System.out.println();
                System.out.println("#      (3)         Player picks item up and throws the item down, which already picked up");
                p3.pickUp(its3_2.get(0)); //researcher1 gets apple
                break;

        }
    }

    static void playerClearsSnowSEQ() {
        System.out.println("@Player clears snow");
        System.out.println("(1)    Player clears snow without shovel");
        System.out.println("(2)    Player clears snow with shovel");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextInt()) {
            case 1:
                System.out.println("#Init(1)");
                Tile t1 = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
                Player p1 = PositionLUT.getInstance().getPlayersOnTile(t1).get(0);
                System.out.println();
                System.out.println("#     (1)    Player clears snow without shovel");
                p1.clearSnow();
                break;
            case 2:
                System.out.println("#Init(2)");
                Tile t2 = PositionLUT.getInstance().getTile(0,0);  //Tile (0, 0)
                Player p2 = PositionLUT.getInstance().getPlayersOnTile(t2).get(0);
                ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t2);
                t2.changeSnow(-t2.getSnow()); //Tile (0,0) snow = 0 to pickUp Shovel
                PositionLUT.getInstance().getPlayersOnTile(t2).get(0).pickUp(its.get(0)); //researcher1 gets showel
                System.out.println();
                p2.step(Direction.valueOf(3)); // 3: right
                System.out.println("#     (2)    Player clears snow with shovel");
                p2.clearSnow();
                System.out.println("^^thisMuch total -2");
                break;
        }
    }

    static void playerSavesPlayersSEQ() {
        System.out.println("@Player saves players");
        System.out.println("#Init");
        Player eskimo1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        eskimo1.step(Direction.valueOf(3));
        Tile t = PositionLUT.getInstance().getTile(3,0);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t);
        t.changeSnow(-t.getSnow());
        eskimo1.pickUp(its.get(0)); //eskimo1 picks up Rope

        Player eskimo2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(3,2)).get(0);
        eskimo2.step(Direction.valueOf(1)); //eskimo2 steps in snowyhole
        System.out.println();
        System.out.println("#       Player saves players");
        eskimo1.savePlayers(Direction.valueOf(0));
    }

    static void putSignalFlareTogetherSEQ() {
        System.out.println("@Put SignalFlare together");
        System.out.println("#Init");
        Player eskimo1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        eskimo1.step(Direction.valueOf(0));    //eskimo1 to Tile (2,1)

        Player researcher1 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        researcher1.step(Direction.valueOf(3));
        researcher1.step(Direction.valueOf(3));
        researcher1.step(Direction.valueOf(0));  //researcher1 to Tile (2, 1)

        Player eskimo2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(3,2)).get(0);
        eskimo2.step(Direction.valueOf(1));
        Tile t2 = PositionLUT.getInstance().getTile(2,2);
        ArrayList<Item> sgf2 = PositionLUT.getInstance().getItemOnTile(t2);
        t2.changeSnow(-t2.getSnow());
        eskimo2.pickUp(sgf2.get(0)); // one sgf collected
        eskimo2.step(Direction.valueOf(1)); //eskimo2 to Tile (2, 1)

        Player researcher2 = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,2)).get(0);
        researcher2.step(Direction.valueOf(3));
        Tile t1 = PositionLUT.getInstance().getTile(1,2);
        ArrayList<Item> sgf1 = PositionLUT.getInstance().getItemOnTile(t1);
        t1.changeSnow(-t1.getSnow());
        researcher2.pickUp(sgf1.get(0)); // one sgf collected
        researcher2.step(Direction.valueOf(3));
        researcher2.step(Direction.valueOf(1)); //researcher2 to Tile (2, 1)
        //one sgf is on Tile (2, 1) thwrownDown

        System.out.println();
        System.out.println("#   Put SignalFlare together");
        researcher1.putSignalTogether(RoundController.getInstance().sg);
    }

    static void playerAteFoodSEQ() {
        System.out.println("@Player picks up and automatically eats food");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(0,2);
        Player p = PositionLUT.getInstance().getPlayersOnTile(t).get(0);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t);
        t.changeSnow(-t.getSnow());
        p.pickUp(its.get(0));
        System.out.println();
        System.out.println("#       Player ate food");
    }

    static void playerWearsDivingSuitSEQ() {
        System.out.println("@Player picks up and automatically wears DivingSuit");
        System.out.println("#Init");
        Tile t = PositionLUT.getInstance().getTile(1,0);
        ArrayList<Item> its = PositionLUT.getInstance().getItemOnTile(t); // divingsuit
        Player p = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
        p.step(Direction.valueOf(3));
        t.changeSnow(-t.getSnow());
        p.pickUp(its.get(0));
        System.out.println();
        System.out.println("#       Player wears DivingSuit");
    }

    static void roundPassingSEQ() {
        System.out.println("@Round passing");
        System.out.println("#Init");
        Player p = PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
        System.out.println();
        System.out.println("#   Round passing");
        p.passRound();
    }

    static void snowStormingSEQ() { // Storm tesztje, az aleseteket a tryStorm-ban választjuk ki
        System.out.println("@Snowstorming");
        System.out.println("#Init");
        SnowStorm ss = new SnowStorm();
        // In case of an igloo destroying storm, we need an igloo first, we initialize that here
        // This is a "syntetic use" of our classes, normally you don't build an igloo outside of the round of an eskimo
        PositionLUT.getInstance().getTile(2,1).buildIgloo();
        System.out.println();
        System.out.println("#     Snowstorming");
        ss.tryStorm();
    }

    static void eskimoBuildsIglooSEQ() {
        System.out.println("@Eskimo builds Igloo (3 cases)");
        System.out.println("(1) Eskimo builds Igloo on stable Tile");
        System.out.println("(2) Eskimo builds Igloo on unstable Tile");
        System.out.println("(3) Eskimo builds Igloo on SnowyHole");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextInt()) {
            case 1:
                System.out.println("#Init(1)");
                Eskimo es1 = (Eskimo) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
                System.out.println();
                System.out.println("#   (1)     Eskimo builds Igloo on stable Tile");
                es1.buildIgloo(); //eskimo1 builds Igloo
                break;
            case 2:
                System.out.println("#Init(2)");
                Eskimo es2 = (Eskimo) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
                es2.step(Direction.valueOf(2));
                es2.step(Direction.valueOf(2));
                es2.step(Direction.valueOf(0));
                System.out.println();
                System.out.println("#   (2)     Eskimo builds Igloo on unstable Tile");
                es2.buildIgloo(); //eskimo1 builds Igloo
                break;
            case 3:
                System.out.println("#Init(3)");
                Eskimo es3 = (Eskimo) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(2,0)).get(0);
                es3.step(Direction.valueOf(2));
                es3.step(Direction.valueOf(0));
                System.out.println();
                System.out.println("#   (3)     Eskimo builds Igloo on SnowyHole");
                es3.buildIgloo(); //eskimo1 builds Igloo
                break;
        }
    }

    static void researcherDetectsCapacitySEQ() {
        System.out.println("@Researcher detects capacity (3 cases)");
        System.out.println("(1) Researcher measures stable Tile");
        System.out.println("(2) Researcher measures unstable Tile");
        System.out.println("(3) Researcher measures SnowyHole");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextInt()) {
            case 1:
                System.out.println("#Init(1)");
                Researcher researcher1 = (Researcher) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
                System.out.println();
                System.out.println("#   (1)     Researcher measures stable Tile");
                researcher1.detectCapacity(Direction.valueOf(3));//researcher1 measures stable Tile
                break;
            case 2:
                System.out.println("#Init(2)");
                Researcher researcher2 = (Researcher) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
                System.out.println();
                System.out.println("#   (2)     Researcher measures unstable Tile");
                researcher2.detectCapacity(Direction.valueOf(0)); //researcher1 measures unstable Tile
                break;
            case 3:
                System.out.println("#Init(3)");
                Researcher researcher3 = (Researcher) PositionLUT.getInstance().getPlayersOnTile(PositionLUT.getInstance().getTile(0,0)).get(0);
                researcher3.step(Direction.valueOf(3));
                System.out.println();
                System.out.println("#   (3)     Researcher measures SnowyHole");
                researcher3.detectCapacity(Direction.valueOf(0)); // researcher1 measures SnowyHole
                break;
        }
    }
}

