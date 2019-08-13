class Player
{
    private int position;
    private Objects[] inventory = new Objects[3];
    public Player()
    {
        position = 0;
    }
    public int getPosition()
    {
        return position;
    }
    public Objects[] getInventory()
    {
        return inventory;
    }
}