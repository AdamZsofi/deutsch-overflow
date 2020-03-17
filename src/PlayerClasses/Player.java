package PlayerClasses;

import ItemClasses.DivingSuit;
import ItemClasses.Item;
import ItemClasses.SignalFlare;
import TileClasses.Direction;

public abstract class Player {
    protected int BodyHeat;
    protected int ID;
    protected int workPoints;
    protected boolean inWater;
    protected Item inHand;
    protected Item wearing;

    public void startRound() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("startRound()");
        System.out.println("Waiting for player input...");
        // TODO: lehetséges inputokat kérni a tesztelőtől (for step press (1) ...)
    };
    public void fallInWater() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("fallInWater()");
        // TODO
    };
    public void ateFood() {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("ateFood()");
        // TODO
    };
    public void changeBodyHeat(int thisMuch) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("changeBodyHeat("+thisMuch+")");
        // TODO
    }; // signed helyett( error volt nekem)

    public void wear(DivingSuit suit) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("wear(ItemClasses.DivingSuit)");
        // TODO
    };

    protected boolean hasEnoughWorkPoints(int cost) {
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("hasEnoughWorkPoints("+cost+")");
        if(cost>workPoints) return false;
        else return true;
    };

    // IControllable implementations:
    // TODO: none of these implementations are done

    void step(Direction dir) {
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
