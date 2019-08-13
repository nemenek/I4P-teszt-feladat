import java.util.Scanner;
class Szabaduloszoba
{
    Commands[] commands;
    Commands commandvariable = new Commands();
    Room[] rooms = new Room[2];
    String[] directions= new String [4];
    Player player = new Player();
    Objects[] objects = new Objects[8];
    public Szabaduloszoba()
    {
        directions[0] = "észak";
        directions[1] = "dél";
        directions[2] = "nyugat";
        directions[3] = "kelet";
        commands[0] = new Commands("menj");
        commands[1] = new Commands("nézd");
        commands[2] = new Commands("vedd");
        commands[3] = new Commands("tedd");
        commands[4] = new Commands("nyisd");
        commands[5] = new Commands("húzd");
        commands[6] = new Commands("törd");
        commands[7] = new Commands("leltár");
        commands[8] = new Commands("mentés");
        commands[9] = new Commands("betöltés");
        objects[0] = new Objects(false, 0, "szekrény", true, true);
        objects[1] = new Objects(false, 0, "ágy", true, false);
        objects[2] = new Objects(false, 1, "kád", false, false);
        objects[3] = new Objects(false, -1, "ajtó", false,true);
        objects[4] = new Objects(false, 0, "ablak", false, true);
        objects[5] = new Objects(true, 0, "doboz", true, true);
        objects[6] = new Objects(true, 0, "kulcs", false, false);
        objects[7] = new Objects(true, 1, "feszítővas", false, false);
        rooms[0] = new Room("nappali", "A nappaliban vagy. Itt található egy szekrény. Nyugatra látsz egy ajtót.");
        rooms[1] = new Room("fürdőszoba", "A fürdőszobában vagy. Itt található egy kád.");
    }
    private void Writeline()
    {
        //System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("A lehetséges parancsok:\nmenj 'irány'\nnézd\nvedd fel 'tárgy'\ntedd le 'tárgy'\nnyisd 'tárgy' 'tárgy'\nhúzd 'tárgy\ntörd 'tárgy'\n\nleltár\nmentés\nbetöltés");
    }    
    public void Playground()
    {
        Scanner reader = new Scanner(System.in);
        String input;
        do
        {
            Writeline();
            input = reader.nextLine();
            CommandReader(input);
        }while(Over());
    }
    private Boolean Over()
    {
        return false;
    }
    public void CommandReader(String command)
    {
        int l;
        int k = IsCommandKnown(command);
        if(k==-1)
        {
            throw new BadCommandException();
        }
        else if (k==0) 
        {
            if(!DirectionChecker(command))
            {
                throw new BadDirectionException();
            }
            else commandvariable.RunCommand(k, command,-1);
        }
        else if(k==1)
        {
            commandvariable.RunCommand(k, command,-1);
        }
        else if (k==2)
        {
            l = ObjectChecker1(command);
            if(l==0)
            {
                throw new BadCommandException();
            }
            else if (l==-1)
            {
                throw new BadObjectException();
            }
            else if (l==-2)
            {
                throw new NotPickableObjectException();
            }
            else
            {
                commandvariable.RunCommand(k, command, l);
            }
        }
        else if(k==3)
        {
            l=ObjectChecker2(command);
            if(l==0)
            {
                throw new BadCommandException();
            }
            else if(l==-1)
            {
                throw new ObjectNotInInventoryException();
            }
            else if(l==-2)
            {
                throw new BadObjectException();
            }
            else commandvariable.RunCommand(k, command, l);
        }
        else if(k==4)
        {
            l=Objectchecker3(command);
            if(l==0)
            {
                throw new ObjectAlreadyOpenedException();
            }
            else if(l==-1)
            {
                throw new BadObjectException();
            }
            else if (l==-2)
            {
                throw new ObjectIsNotOpenableAndDoesntOpenAnythingException();
            }
            else if(l==-3)
            {
                throw new ObjectCantBeOpenedWithBareHandsException();
            }
            else if(l==-4)
            {
                throw new ObjectIsNotOpenableException();
            }
            else if(l==-5)
            {
                throw new BoxCantBeUsedToOpenAnythingException();
            }
            else if(l==-6)
            {
                throw new ObjectNotInInventoryException();
            }
            else if(l==-7)
            {
                throw new ObjectsDoesntInteractWithEachOtherException();
            }
            else if(l==-8)
            {
                throw new PlayerIsNotInTheRoomException();
            }
            else if(l==10)
            {
                commandvariable.RunCommand(k, command, l);
            }
            else if(l==11)
            {
                commandvariable.RunCommand(k, command, l);
            }
            else if(l==12)
            {
                commandvariable.RunCommand(k, command, l);
            }
            else if(l==14)
            {
                commandvariable.RunCommand(k, command, 14);
            }
        }
        else if (k==5)
        {
            l = Objectchecker4(command);
            if(l==0)
            {
                throw new ObjectAlreadyMovedException();
            }
            else if(l==-1)
            {
                throw new ObjectIsNotMoveableException();
            }
            else if(l==-2)
            {
                throw new BadObjectException();
            }
            else commandvariable.RunCommand(k, command, l);
        }
        else if(k==6)
        {
            commandvariable.RunCommand(6, command, -1);
        }
        else if (k==7)
        {
            commandvariable.RunCommand(k, command, -1);
        }
        else if (k==8)
        {
            commandvariable.RunCommand(k, command, -1);
        }
    }
    private int ObjectChecker1(String command)
    {
        String helper = "";
        for(int i = 5;i<9;i++)
        {
            helper+=command.charAt(i);
        }
        if(helper!="fel ") 
        {
            return 0;
        }
        else 
        {
            helper = "";
            for(int i = 9;i<command.length();i++)
            {
                helper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper==objects[i].getName())
                {
                    if(objects[i].getPickable()) return i;
                    else return -2;
                }
                else if(i==objects.length-1) return -1;
            }
        }
        return 0;
    }
    private int ObjectChecker2(String command)
    {
        String helper = "";
        for(int i = 5;i<8;i++)
        {
            helper+=command.charAt(i);
        }
        if(helper!="le ") return 0;
        else
        {
            helper = "";
            for(int i = 8;i<command.length();i++)
            {
                helper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper==objects[i].getName())
                {
                    for(int j = 0;j<player.getInventory().length;j++)
                    {
                        if(helper==player.getInventory()[j].getName())
                        {
                            return i;
                        }
                        else return -1;
                    }
                }
                else if(i==objects.length) return -2;
            }
        }
        return 0;
    }
    private int Objectchecker3(String command)
    {
        int mightbegood = 0;
        String helper = "";
        int k=-1;
        for(int i = 6;i<command.length();i++)
        {
            if(command.charAt(i)!=' ')
            {
                helper+=command.charAt(i);
            }
            else 
            {
                k=i;
                i=command.length();
            }
        }
        String xtrahelper = helper;
        helper = "";
        if(k!=-1)
        {
            for(int i = k+1;i<command.length();i++)
            {
                helper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(xtrahelper==objects[i].getName())
                {   
                    if(objects[i].getOpenable())
                    {
                        if(!objects[i].getIsOpen())
                        {
                            mightbegood = 1;
                        }
                        else return 0;
                    }
                    else if (objects[i].getPickable())
                    {
                        if(objects[i].getIsInInventory())
                        {
                            if(objects[i].getName()!="doboz")
                            {
                                mightbegood=2;
                            }
                            else return -5;
                        }
                        else return -6;
                    }
                    else return -2;
                }
                else if(i==objects.length-1) return -1;
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper==objects[i].getName())
                {
                    if(mightbegood==1)
                    {
                        if(objects[i].getPickable())
                        {
                            if(objects[i].getIsInInventory())
                            {
                                if(xtrahelper=="ajtó" && helper == "kulcs") return 10;
                                else if(xtrahelper == "ablak" && helper =="feszítővas") return 11;
                                else if(xtrahelper=="szekrény")
                                {
                                    System.out.println("A szekrényt nem tudtad kinyitni, de miközben próbáltad, rájöttél, hogy nincs is bezárva, így kinyitottad.")
                                    return 12;
                                }
                                else if(xtrahelper=="doboz")
                                {
                                    return 14;
                                }
                                else return -7;
                            }
                            else return -6;
                        }
                        else return -7;
                    }
                    else if(mightbegood==2)
                    {
                        if(object[i].getOpenable)
                        {
                            if(!objects[i].getIsOpen())
                            {
                                if(player.getPosition()==objects[i].getPosition())
                                {
                                    if(xtrahelper=="kulcs"&&helper=="ajtó") return 10;
                                    else if(xtrahelper == "feszítővas"&&helper=="ablak") return 11;
                                    else if (helper =="szekrény")
                                    {
                                        System.out.println("A szekrényt nem tudtad kinyitni, de miközben próbáltad, rájöttél, hogy nincs is bezárva, így kinyitottad.")
                                        return 12;
                                    }
                                    else if(helper=="doboz")
                                    {
                                        return 14;
                                    }
                                    else return -7;
                                }
                                else return -8;
                            }
                            else return 0;
                        }
                        return -4;
                    }
                }
            }
        }
        else
        {
            for(int i = 0;i<objects.length;i++)
            {
                if(xtrahelper==objects[i].getName())
                {
                    if(objects[i].getOpenable())
                    {
                        if(!objects[i].getIsOpen())
                        {
                            if(objects[i].getName()=="szekrény" )
                            {
                                return 12;
                            }
                            if(objects[i].getName=="doboz")
                            {
                                return 14;
                            }
                            else return -3;
                        }
                        return 0;
                    }
                    return -4;
                }
                else if(i==objects.length-1) return -1;
            }
        }
    }
    private int Objectchecker4(String command)
    {
        String helper = "";
        for(int i = 5;i<command.length();i++)
        {
            helper+=command.charAt(i);
        }
        for(int i = 0;i<objects.length;i++)
        {
            if(helper == objects[i].getName())
            {
                if(objects[i].getMoveable())
                {
                    if(!objects[i].getIsmoved()) return i;
                    else return 0;
                }
                else return -1;
            }
            else if(i==objects.length-1) return -2;
        }
        return 0;
    }
    private boolean DirectionChecker(String command)
    {
        String helper = "";
        for(int i = 5;i<command.length();i++)
        {
            helper+=command.charAt(i);
        }
        for(int i = 0;i<directions.length;i++)
        {
            if(helper==directions[i]) return true;
            else if(i==directions.length-1) return false;
        }
        return false;
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
        for(int i = 0;i<commands.length;i++)
        {
            if(xtrahelper==commands[i].getName()) return i;
            else if(i==commands.length-1) return -1;
        }
        return -1;
    }
}