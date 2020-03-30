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
        //Válasz: inkabb a RoundControllerben kene

       // Tile position= PositionLUT.pLUT.playerTileMap.get(this);
        Tile position= PositionLUT.pLUT.playerTileMap.get(this);
        while(workPoints>0) {
            if (hasEnoughWorkPoints(1)) {
                System.out.println("Enter the activity:");
                Scanner scanner = new Scanner(System.in);
                int activity = scanner.nextInt();
                scanner.close();
                switch (activity) {
                    case 0:
                        step(Direction.left);
                        break;
                    case 1:
                        position = PositionLUT.pLUT.playerTileMap.get(this);
                        Item item = PositionLUT.pLUT.getItemOnTile(position).get(0);
                        pickUp(item);
                        break;
                    case 2:
                        clearSnow();
                        break;
                    case 3:
                        position = PositionLUT.pLUT.playerTileMap.get(this);
                        item = PositionLUT.pLUT.getItemOnTile(position).get(0);
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
        }
    };
    public void fallInWater() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("fallInWater()");

        inWater = true;
    };
    public void ateFood() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("ateFood()");

        inHand.used(this, Activity.eatingFood);
    };
    public void changeBodyHeat(int thisMuch) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("changeBodyHeat("+thisMuch+")");

        BodyHeat += thisMuch;
    }; // signed helyett( error volt nekem)
    public void wear(DivingSuit suit) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("wear(ItemClasses.DivingSuit)");

        suit.used(this,Activity.puttingOnSuit);
        wearing = suit;
    };
    protected boolean hasEnoughWorkPoints(int cost) { //van munka, ami több pontba kerül?(lehetne default = 1)
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("hasEnoughWorkPoints("+cost+")");

        if(cost>workPoints)
            return false;
        workPoints -= cost;
        return true;
    };

    // IControllable implementations:
    // TODO: none of these implementations are done

    public void step(Direction dir) { //public lett vizbeeses miatt, kesobb lehet javitani
        System.out.print("(IControllable) Player:");
        System.out.println("step("+dir+")");

        Tile position= PositionLUT.pLUT.playerTileMap.get(this);
        Tile next_tile=position.getNeighbour(dir);

        position.steppedOff(dir);

        PositionLUT.pLUT.setPosition(this, next_tile);
        next_tile.steppedOn(this);
    };
    void pickUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("pickUp("+i+")"); // TODO: Item toString?

        Tile position= PositionLUT.pLUT.getPosition(i);
        int snow= position.getSnow();
        if(snow==0) {
            if (inHand.getState() == ItemState.thrownDown) {
                inHand.diggedUp();
            } else {
                inHand.thrownDown();
            }
            i.pickedUp(this);
        }
    };
    void clearSnow() {
        System.out.print("(IControllable) Player:");
        System.out.println("clearSnow()");

        int thismuch=-1;
        if(inHand.getState()==ItemState.inHand){
            inHand.used(this,Activity.clearingSnow);
            thismuch=-2;
        }
        Tile position= PositionLUT.pLUT.playerTileMap.get(this);
        position.changeSnow(thismuch);
    };
    void digItemUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("digItemUp()");

        i.diggedUp();
    };
    void savePlayers(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("savePlayers("+dir+")");

        inHand.used(this,Activity.savingPeople);

    };
    void putSignalTogether(SignalFlare sg) {
        System.out.print("(IControllable) Player:");
        System.out.println("putSignalTogether("+sg+")");

        sg.putTogether(RoundController.getInstance());
    };
    void passRound() {
        System.out.print("(IControllable) Player:");
        System.out.println("passRound()");

        RoundController.getInstance().endLastRound();
    };
    // Done with IControllable Implementations
}
