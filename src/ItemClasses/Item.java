package ItemClasses;

import PlayerClasses.Player;

public abstract class Item {

    public void thrownDown();
    public void pickedUp(Player Picker);
    public void diggedUp();
    void used(Player p, Activity a){} // ItemClasses.Activity lett ( enum vs paraméter más a osztály diagrammon)

}
