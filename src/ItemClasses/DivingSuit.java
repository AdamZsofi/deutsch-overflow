package ItemClasses;

import CLI.Game;
import PlayerClasses.Player;

public class DivingSuit extends Item{

    /**
     * Player picks up the DivingSuit
     * Automatically worn, if picked up
     */
    @Override
    public void pickedUp(Player p){
        p.wear(this);
        Game.log.println("$ DivingSuit>pickedUp : Transaction 'pickingUpDivingSuit' was successful");
    }
    public String getShortName(){ return "D("+this.getState()+")";}
    public String toString() {return "DivingSuit";}
}
