class Commands
{
    private String[] commands = new String[10];
    public Commands()
    {
        commands[0] = "menj";
        commands[1] = "nézd";
        commands[2] = "vedd";
        commands[3] = "tedd";
        commands[4] = "nyisd";
        commands[5] = "húzd";
        commands[6] = "törd";
        commands[7] = "leltár";
        commands[8] = "mentés";
        commands[9] = "betöltés";
    }
    public int CommandReader(String command)
    {
        if(IsCommandKnown(command)==-1)
        {
            throw new MyException();
        }
        else if()
    }
    private int IsCommandKnown(String command)
    {
        String helper="";
        for(int i = 0;i<5;i++)
        {
            helper+=command.charAt(i);
        }
        if(helper.charAt(4)!=' ' || helper.charAt(4)!='d') return -1;
        String xtrahelper="";
        if(helper.charAt(4)==' ')
        {
            for(int i = 0;i<4;i++)
            {
                xtrahelper+=helper.charAt(i);
            }
        }
        else xtrahelper=helper;
        for(int i = 0;i<CountCommands();i++)
        {
            if(xtrahelper==commands[i]) return i;
            else if(i==CountCommands()-1) return -1;
        }
        return -1;
    }
    private int CountCommands()
    {
        int i = 0;
        for(int j = 0;j<commands.length;j++)
        {
            i++;
        }
        return i;
    }
}