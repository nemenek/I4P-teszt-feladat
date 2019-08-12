class Objects
{
    private boolean pickable;
    private int position;
    private String name;
    private boolean moveable;
    private boolean openable;
    public Objects(boolean pickable,int position,String name,boolean moveable,boolean openable)
    {
        this.name = name;
        this.position = position;
        this.pickable = pickable;
        this.moveable=moveable;
        
    }
}