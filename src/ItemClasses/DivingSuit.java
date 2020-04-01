package ItemClasses;

import PlayerClasses.Player;

public class DivingSuit extends Item{

    /**
     * automatically worn, if picked up
     * */
    @Override
    public void pickedUp(Player p){
        System.out.println("ItemClasses.DivingSuit.used");
        p.wear(this);
    }
}
