package ItemClasses;

import PlayerClasses.Player;

public abstract class Item {
    ItemState state;

    Item() {
        state = ItemState.frozen;
    }

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
    public ItemState getState(){
        return state;
    }
    public void used(Player p, Activity a){ }
}
