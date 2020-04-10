package ItemClasses;

import PlayerClasses.Player;

/**
 * Parts of SinalFlare
 * It has an unique identifier partID
 */
public class SignalFlarePart extends Item {

    private int partID; //should be unique

    public SignalFlarePart(int id){
        partID = id;
    }

    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.SignalFlarePart.used()");
    }
}
