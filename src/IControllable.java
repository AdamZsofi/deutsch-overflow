public interface IControllable {

    public void step(Direction dir);
    public void pickUp(Item i);
    public void clearSnown();
    public void digItemUp();
    public void savePlayers(Direction dir);
    public void putSignalTogether(SignalFlare sg);
    public void passRound();
}
