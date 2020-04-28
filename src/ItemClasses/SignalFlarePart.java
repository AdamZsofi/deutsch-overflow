package ItemClasses;

import CLI.Game;
import PlayerClasses.Player;

/**
 * Parts of SignalFlare
 */
public class SignalFlarePart extends Item {
    /**
     * @param partID unique identifier
     */
    private int partID;

    public SignalFlarePart(int id){
        partID = id;
    }

    @Override
    public void used(Player p, Activity a){
        Game.log.println("# SignalFlarePart>used");
    }
    public String getShortName(){ return "SFP("+state.getShortName(getState())+")";}
    public String toString() {return "SignalFlarePart";}
}
