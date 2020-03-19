package ItemClasses;

import PlayerClasses.Player;

public abstract class Item {

    ItemState state; //nincs leirva a dokumentacioba, konsturkornal: state = ItemState.thrownDown

    public void thrownDown(){
        System.out.println("ItemClasses.Item.thrownDown");


        state =  ItemState.thrownDown;
    }
    public void pickedUp(Player Picker){
        System.out.println("ItemClasses.Item.pickedUp");


        state = ItemState.inHand;
    }
    public void diggedUp(){
        System.out.println("ItemClasses.Item.diggedUp");


        state = ItemState.thrownDown;
    }
    public void used(Player p, Activity a){}    // ItemClasses.Activity lett ( enum vs paraméter más a osztály diagrammon)
                                                // igazabol ha bemegy az used if agaban, akkor kene playerWorkpoints--
}
