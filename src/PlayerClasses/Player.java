package PlayerClasses;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.*;
import TileClasses.*;
import TileClasses.Direction;

import java.io.IOException;
import java.util.Scanner;

public abstract class Player extends Character{
    protected int BodyHeat;
    protected int ID;
    protected int workPoints;
    public boolean inWater; // public lett, kesobb ezt átgondolhatjuk még
    public Item inHand; // public lett, hogy más is eltudja dobni a kezéből a törékeny ásót, más megoldás esetleg?
    protected Item wearing;

    public void startRound() {
        workPoints = 4;
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("startRound()");
        System.out.println("Waiting for player input...");

        Tile position;
        while(workPoints>0 && !inWater) {
            System.out.println("Enter the activity:");
            Scanner scanner = new Scanner(System.in);
            int activity = scanner.nextInt();
            scanner.close();
            switch (activity) {
                case 0:
                    step(Direction.valueOf(2));//left
                    break;
                case 1:
                    position = PositionLUT.getInstance().getPosition(this);
                    Item item = PositionLUT.getInstance().getItemOnTile(position).get(0);
                    pickUp(item);
                    break;
                case 2:
                    clearSnow();
                    break;
                case 3:
                    position = PositionLUT.getInstance().getPosition(this);
                    item = PositionLUT.getInstance().getItemOnTile(position).get(0);
                    digItemUp(item);
                    break;
                case 4:
                    savePlayers(Direction.valueOf(0));//up
                    break;
                case 5:
                    putSignalTogether(RoundController.getInstance().sg);
                case 6:
                    passRound();
                    break;
                default:
                    System.out.println("Invalid Activity number!");
                    break;
            }
        }
        passRound(); // ha elfogy a workPoint/vízbe esik, akkor automatikus pass
    }
    public void fallInWater() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("fallInWater()");

        inWater = true;
    }
    /**
     * Called by Food, if its picked up. Food is automatically eaten if its picked up
     * */
    public void ateFood() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("ateFood()");
        inHand = null;
        changeBodyHeat(1);
    }
    public void changeBodyHeat(int thisMuch) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("changeBodyHeat("+thisMuch+")");

        BodyHeat += thisMuch;
    }
    /**
     * Called by DivingSuit, if its pickedUp (its automatically worn if its picked up)
     * */
    public void wear(DivingSuit suit) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("wear(ItemClasses.DivingSuit)");
        wearing = suit;
        inHand = null;
    }

    // IControllable implementations:

    // getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
    /*public void step(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("step("+dir+")");
        if(dir == Direction.here) {
            System.out.println("You stay where you were");
            return;
        }

        Tile position= PositionLUT.getInstance().getPosition(this);
        try {
            Tile next_tile = position.getNeighbour(dir);
            position.steppedOff(dir);
            PositionLUT.getInstance().setPosition(this, next_tile);
            Item player_item = this.inHand;
            if(inHand!=null){
               PositionLUT.getInstance().setPosition(player_item,next_tile);
            }
            next_tile.steppedOn(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't go that way");
            return;
        }
        workPoints--;
    }*/
    public void pickUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("pickUp(Item)");
        Tile position = PositionLUT.getInstance().getPosition(i);
        int snow = position.getSnow();
        if(snow==0) {
            if (inHand != null) {
                if (inHand.getState() == ItemState.thrownDown) {
                    inHand.diggedUp();
                } else {
                    inHand.thrownDown();
                    inHand = null;
                }
            }
            inHand = i;
            i.pickedUp(this);
        }
        workPoints--;
    }
    //atirni protectedre
    public void clearSnow() {
        System.out.print("(IControllable) Player:");
        System.out.println("clearSnow()");


        if (inHand != null) {
            if(inHand.getState()==ItemState.inHand){
                inHand.used(this,Activity.clearingSnow);
            }
        }

        Tile position= PositionLUT.getInstance().getPosition(this);
        position.changeSnow(-1);

        workPoints--;
    }
    //atirni protectedre
    public void digItemUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("digItemUp()");

        i.diggedUp();
        workPoints--;
    }
    //atirni protectedre
    public void savePlayers(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("savePlayers("+dir+")");
        if(dir==Direction.valueOf(4)) {
            System.out.println("You can't save yourself");
            return;
        }
        inHand.used(this,Activity.savingPeople);
        workPoints--;
    }
    //atirni protectedre
    public void putSignalTogether(SignalFlare sg) {
        System.out.print("(IControllable) Player:");
        System.out.println("putSignalTogether("+sg+")");

        sg.putTogether(RoundController.getInstance());
    }
    //atirni protectedre
    public void passRound() {
        System.out.print("(IControllable) Player:");
        System.out.println("passRound()");

        RoundController.getInstance().endLastRound();
    }
    // Done with IControllable Implementations
}
