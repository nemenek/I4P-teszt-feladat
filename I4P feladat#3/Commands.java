class Commands
{
    private String name;
    public Commands()
    {

    }
    public Commands(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void RunCommand(int type, String command, int help)
    {
        if(type==0)
        {

        }
        else if(type==1)
        {
            Szabaduloszoba variable = new Szabaduloszoba(1);
            System.out.println(variable.rooms[variable.player.getPosition()]);
        }
    }
}