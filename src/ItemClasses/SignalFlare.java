package ItemClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;

import java.util.ArrayList;

public class SignalFlare {

    public ArrayList<SignalFlarePart> signalFlareParts;

    public void putTogether(RoundController rc){
        System.out.println("ItemClasses.SignalFlare.putTogether()");


        for (SignalFlarePart sfp: signalFlareParts) {//lehetne átláthatóbban csinalni
            if(!PositionLUT.pLUT.getPosition(signalFlareParts.get(0)).equals(PositionLUT.pLUT.getPosition(sfp)))
                return;
            if(!(sfp.state == ItemState.inHand))
                return;
        }
        RoundController.rc.win();
    }
}
