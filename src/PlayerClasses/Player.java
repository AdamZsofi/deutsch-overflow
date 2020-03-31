package PlayerClasses;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.*;
import TileClasses.*;
import TileClasses.Direction;

import java.io.IOException;
import java.util.Scanner;

public abstract class Player {
    protected int BodyHeat;
    protected int ID;
    protected int workPoints;
    public boolean inWater; //public lett, kesobb lehet javitani
    protected Item inHand;
    protected Item wearing; //lehetne DivingSuit is vagy csak egy boolean, ugyse csinalunk mar vele semmit

    public void startRound() {
        workPoints = 4;
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("startRound()");
        System.out.println("Waiting for player input...");
        // TODO: lehetséges inputokat kérni a tesztelőtől (for step press (1) ...)
        // Válasz: inkabb a RoundControllerben kene

       // Tile position= PositionLUT.getInstance().getPosition(this);
        Tile position = PositionLUT.getInstance().getPosition(this);
        while(workPoints>0 && !inWater) {
            System.out.println("Enter the activity:");
            Scanner scanner = new Scanner(System.in);
            int activity = scanner.nextInt();
            scanner.close();
            switch (activity) {
                case 0:
                    step(Direction.left);
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
                    savePlayers(Direction.up);
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
        passRound(); // ha elfogy a workPoint, akkor automatikus pass
    }
    public void fallInWater() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("fallInWater()");

        inWater = true;
    }
    public void ateFood() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("ateFood()");

        inHand.used(this, Activity.eatingFood);
    }
    public void changeBodyHeat(int thisMuch) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("changeBodyHeat("+thisMuch+")");

        BodyHeat += thisMuch;
    }
    public void wear(DivingSuit suit) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("wear(ItemClasses.DivingSuit)");

        suit.used(this,Activity.puttingOnSuit);
        wearing = suit;
    }

    // IControllable implementations:
    // TODO: none of these implementations are done

    // getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
    public void step(Direction dir) { //public lett vizbeeses miatt, kesobb lehet javitani
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
            next_tile.steppedOn(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't go that way");
            return;
        }
        workPoints--;
    }
    public void pickUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("pickUp("+i+")"); // TODO: Item toString?
        Tile position = PositionLUT.getInstance().getPosition(i);
        int snow = position.getSnow();
        if(snow==0) {
            if (inHand != null) {
                if (inHand.getState() == ItemState.thrownDown) {
                    inHand.diggedUp();
                } else {
                    inHand.thrownDown();
                }
            }
            i.pickedUp(this);
            inHand = i;//EZ JO ITT?
        };
        workPoints--;
    }
    //atirni protectedre
    public void clearSnow() {
        System.out.print("(IControllable) Player:");
        System.out.println("clearSnow()");

        int thismuch=-1;
        if (inHand != null) {
            if(inHand.getState()==ItemState.inHand){
                inHand.used(this,Activity.clearingSnow);
                thismuch=-2;
            }
        }

        Tile position= PositionLUT.getInstance().getPosition(this);
        position.changeSnow(thismuch);
        workPoints--;
    }
    //atirni protectedre
    void digItemUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("digItemUp()");

        i.diggedUp();
        workPoints--;
    }
    //atirni protectedre
    public void savePlayers(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("savePlayers("+dir+")");
        if(dir==Direction.here) {
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
