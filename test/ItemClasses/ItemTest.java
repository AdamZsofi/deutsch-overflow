package ItemClasses;

import CLI.Game;
import PlayerClasses.Researcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void itemStateCheck() {
        Game.log=System.out;
        Item i=new Shovel();
        assertTrue(i.getState()==ItemState.frozen);
        i.diggedUp();
        assertTrue(i.getState()==ItemState.thrownDown);
        i.pickedUp(new Researcher(0));
        assertTrue(i.getState()==ItemState.inHand);
        i.thrownDown();
        assertTrue(i.getState()==ItemState.thrownDown);
    }
}