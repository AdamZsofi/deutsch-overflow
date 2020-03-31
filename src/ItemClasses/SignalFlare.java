package ItemClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;

import java.util.ArrayList;

public class SignalFlare {

    public ArrayList<SignalFlarePart> signalFlareParts;
    //3 db alkatrész

    public SignalFlare(){
        signalFlareParts = new ArrayList<>();
        signalFlareParts.add(new SignalFlarePart(0));
        signalFlareParts.add(new SignalFlarePart(1));
        signalFlareParts.add(new SignalFlarePart(2));
    }

    public void putTogether(RoundController rc){
        System.out.println("ItemClasses.SignalFlare.putTogether()");


        for (SignalFlarePart sfp: signalFlareParts) {//lehetne átláthatóbban csinalni
            if(!PositionLUT.getInstance().getPosition(signalFlareParts.get(0)).equals(PositionLUT.getInstance().getPosition(sfp)))
                return;
            if(!(sfp.state == ItemState.inHand))
                return;
        }
        RoundController.getInstance().win();
    }
}
