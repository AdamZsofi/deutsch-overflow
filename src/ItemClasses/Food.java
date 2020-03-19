package ItemClasses;

import PlayerClasses.Player;

public class Food extends Item{

    @Override
    public void used(Player p, Activity a){ //ha Activity más, nincs még lekezelve(igy semmit nem csinal), hol lesz?
        System.out.println("ItemClasses.Food.used");


        if(a == Activity.eatingFood)
            p.changeBodyHeat(1);
    }
}
