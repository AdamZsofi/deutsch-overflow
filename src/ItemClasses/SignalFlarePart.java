package ItemClasses;

import PlayerClasses.Player;

public class SignalFlarePart extends Item {

    private int partID; //egyedinek kell lenni!

    public SignalFlarePart(int id){
        partID = id;
    }

    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.SignalFlarePart.used()");
    }
}
