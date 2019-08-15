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
    public void setPosition(int value)
    {
        position=value;
    }
    public void setInventory(Objects value, Boolean inout)
    {
        if(inout)
        {
            for(int i = 0;i<inventory.length;i++)
            {
                if(inventory[i]==null)
                {
                    inventory[i]=value;
                    i=inventory.length;
                }
            }
        }
        else
        {
            for(int i = 0;i<inventory.length;i++)
            {
                if(inventory[i]==value)
                {
                    inventory[i]=null;
                    i=inventory.length;
                }
            }
        }
    }
}