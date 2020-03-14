public abstract class Player {
    protected int BodyHeat;
    protected int ID;
    protected int workPoints;
    protected boolean inWater;
    protected Item inHand;
    protected Item wearing;

    public void startRound();
    public void fallInWater();
    public void ateFood();
    public void changeBodyHeat(int thisMuch); // signed helyett( error volt nekem)
    public void wear(DivingSuit suit);
    protected boolean hasEnoughWorkPoints(int cost);
}
