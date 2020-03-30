package PlayerClasses;

import ItemClasses.Activity;
import ItemClasses.DivingSuit;
import ItemClasses.Item;
import ItemClasses.SignalFlare;
import TileClasses.Direction;

import java.io.IOException;

public abstract class Player {
    protected int BodyHeat;
    protected int ID;
    protected int workPoints;
    public boolean inWater; //public lett, kesobb lehet javitani
    protected Item inHand;
    protected Item wearing; //lehetne DivingSuit is vagy csak egy boolean, ugyse csinalunk mar vele semmit

    public void startRound() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("startRound()");
        System.out.println("Waiting for player input...");
        workPoints = 4;
        // TODO: lehetséges inputokat kérni a tesztelőtől (for step press (1) ...)
        //Válasz: inkabb a RoundControllerben kene
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
    };
    void pickUp(Item i) {
        System.out.print("(IControllable) Player:");
        System.out.println("pickUp("+i+")"); // TODO: Item toString?
    };
    void clearSnow() {
        System.out.print("(IControllable) Player:");
        System.out.println("clearSnow()");
    };
    void digItemUp() {
        System.out.print("(IControllable) Player:");
        System.out.println("digItemUp()");
    };
    void savePlayers(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("savePlayers("+dir+")");
    };
    void putSignalTogether(SignalFlare sg) {
        System.out.print("(IControllable) Player:");
        System.out.println("putSignalTogether("+sg+")");
    };
    void passRound() {
        System.out.print("(IControllable) Player:");
        System.out.println("passRound()");
    };
    // Done with IControllable Implementations
}
