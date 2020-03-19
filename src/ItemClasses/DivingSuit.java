package ItemClasses;

import PlayerClasses.Player;

public class DivingSuit extends Item{

    @Override
    public void used(Player p, Activity a){
        System.out.println("ItemClasses.DivingSuit.used");


        if(a == Activity.puttingOnSuit)
            p.wear(this);
    }
}
