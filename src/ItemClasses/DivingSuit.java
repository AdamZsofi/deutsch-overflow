package ItemClasses;

import PlayerClasses.Player;

public class DivingSuit extends Item{

    /**
     * Player picks up the DivingSuit
     * Automatically worn, if picked up
     */
    @Override
    public void pickedUp(Player p){
        System.out.println("ItemClasses.DivingSuit.used");
        p.wear(this);
    }
}
