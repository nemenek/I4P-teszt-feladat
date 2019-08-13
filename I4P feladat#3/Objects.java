

class Objects
{
    private Boolean pickable;
    private int position;
    private String name;
    private Boolean moveable;
    private Boolean openable;
    private Boolean isopen;
    private Boolean ismoved;
    private Boolean isininventory;
    private Boolean isavailable = false;
    public Objects(Boolean pickable,int position,String name,Boolean moveable,Boolean openable)
    {
        isopen = false;
        ismoved=false;
        isininventory= false;
        this.name = name;
        this.position = position;
        this.pickable = pickable;
        this.moveable=moveable;
    }
    public void setIsAvailable(Boolean value)
    {
        isavailable=value;
    }
    public Boolean getIsAvailable()
    {
        return isavailable;
    }
    public Boolean getPickable()
    {
        return pickable;
    }
    public int getPosition()
    {
        return position;
    }
    public String getName()
    {
        return name;
    }
    public Boolean getMoveable()
    {
        return moveable;
    }
    public Boolean getOpenable()
    {
        return openable;
    }
    public Boolean getIsOpen()
    {
        return isopen;
    }
    public void setIsOpen(Boolean value)
    {
        isopen = value;
    }
    public Boolean getIsmoved()
    {
        return ismoved;
    }
    public Boolean getIsInInventory()
    {
        return isininventory;
    }
    public void setIsmoved(Boolean value)
    {
        ismoved=value;
    }
    public void setIsInInventory(Boolean value)
    {
        isininventory=value;
    }
}