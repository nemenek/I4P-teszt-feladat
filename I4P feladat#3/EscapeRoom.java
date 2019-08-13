import java.util.Scanner;
class Szabaduloszoba
{
    Commands[] commands = new Commands[10];
    Commands commandvariable = new Commands();
    Room[] rooms = new Room[2];
    String[] directions= new String [4];
    Player player = new Player();
    Objects[] objects = new Objects[8];
    public Szabaduloszoba(int i)
    {

    }
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
        Scanner reader = new Scanner(System.in,"CP852");
        String input;
            do
            {
                Writeline();
                input = reader.nextLine();
                CommandReader(input);
                
            }while(Over());
            reader.close();
    }
    private Boolean Over()
    {
        return false;
    }
    public void CommandReader(String command)
    {
        try
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
                else if(l==-3)
                {
                    throw new PlayerIsNotInTheRoomException();
                }
                else if (l==-4)
                {
                    throw new ObjectAlreadyPickedUpException();
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
                else if(l==-3)
                {
                    throw new PlayerIsNotInTheRoomException();
                }
                else commandvariable.RunCommand(k, command, l);
            }
            else if(k==6)
            {
                l=Objectchecker5(command);
                if(l==0)
                {
                    throw new BadObjectException();
                }
                else if(l==-1)
                {
                    throw new ObjectsDoesntInteractWithEachOtherException();
                }
                else if(l==-2)
                {
                    throw new ObjectCantBeBrokeWithBareHandsException();
                }
                else if(l==-3)
                {
                    throw new ObjectIsNotBrakeableException();
                }
            }
            else if (k==7)
            {
                commandvariable.RunCommand(k, command, -1);
            }
            else if (k==8)
            {
                commandvariable.RunCommand(k, command, -1);
            }
            else if(k==9)
            {
                commandvariable.RunCommand(k, command, -1);
            }
        }
        catch(BadCommandException e)
        {
            System.out.println("A megadott parancs ismeretlen!");
        }
        catch(BadDirectionException e)
        {
            System.out.println("Nem adott meg, vagy rosszul adta meg az irányt!");
        }
        catch(BadObjectException e)
        {
            System.out.println("Nem írt be, vagy rosszul írta be a tárgyat!");
        }
        catch(NotPickableObjectException e)
        {
            System.out.println("A megadott tárgy nem felvehető!");
        }
        catch (ObjectNotInInventoryException e)
        {
            System.out.println("A letenni kívánt tárgy nincs nálad!");
        }
        catch(ObjectAlreadyMovedException e)
        {
            System.out.println("Nem tudod mozdítani a tárgyat, mert már egyszer elmozdítottad!");
        }
        catch(ObjectIsNotMoveableException e)
        {
            System.out.println("A megmozdítani kívánt tárgy nem mozdítható!");
        }
        catch(ObjectAlreadyOpenedException e)
        {
            System.out.println("Ez a tárgy már ki van nyitva!");
        }
        catch(ObjectIsNotOpenableAndDoesntOpenAnythingException e)
        {
            System.out.println("Az elsőnek beírt tárgy nem nyit semmit és nem is nyitható!");
        }
        catch(ObjectIsNotOpenableException e)
        {
            System.out.println("Az első tárggyal ki lehet nyitni egy másikat, de a második beírt tárgy nem kinyitható!");
        }
        catch(ObjectCantBeOpenedWithBareHandsException e)
        {
            System.out.println("A beírt tárgyat nem lehet kinyitni puszta kézzel!");
        }
        catch(ObjectsDoesntInteractWithEachOtherException e)
        {
            System.out.println("A két beírt tárgy nem interaktál egymással!");
        }
        catch(PlayerIsNotInTheRoomException e)
        {
            System.out.println("A tárgy amellyen interakcióba akarsz lépni, nem található a szobában!");
        }
        catch(ObjectAlreadyPickedUpException e)
        {
            System.out.println("Ez a tárgy már nálad van!");
        }
        catch(ObjectIsNotBrakeableException e)
        {
            System.out.println("A beírt tárgyat nem lehet eltörni!");
        }
        catch(ObjectCantBeBrokeWithBareHandsException e)
        {
            System.out.println("Az ablakot nem törheted be puszta kézzel, mert megvágnád magad!");
        }
        finally
        {
            
        }
    }
    private int ObjectChecker1(String command)
    {
        String helper = "";
        for(int i = 5;i<9;i++)
        {
            helper+=command.charAt(i);
        }
        if(!helper.equals("fel ")) 
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
                if(helper.equals(objects[i].getName()))
                {
                    if(objects[i].getPickable())
                    {
                        if(!objects[i].getIsInInventory())
                        {
                            if(objects[i].getPosition()==player.getPosition()) return i;
                            else return -3;
                        }
                        else return -4;
                    }
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
        if(!helper.equals("le ")) return 0;
        else
        {
            helper = "";
            for(int i = 8;i<command.length();i++)
            {
                helper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper.equals(objects[i].getName()))
                {
                    if(objects[i].getIsInInventory()) return i;
                    else return -1;
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
                if(xtrahelper.equals(objects[i].getName()))
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
                            if(!objects[i].getName().equals("doboz"))
                            {
                                mightbegood=2;
                            }
                            else return 14;
                        }
                        else return -6;
                    }
                    else return -2;
                }
                else if(i==objects.length-1) return -1;
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper.equals(objects[i].getName()))
                {
                    if(mightbegood==1)
                    {
                        if(objects[i].getPickable())
                        {
                            if(objects[i].getIsInInventory())
                            {
                                if(xtrahelper.equals("ajtó") && helper.equals("kulcs")) return 10;
                                else if(xtrahelper.equals("ablak") && helper.equals("feszítővas")) return 11;
                                else if(xtrahelper.equals("szekrény"))
                                {
                                    System.out.println("A szekrényt nem tudtad kinyitni, de miközben próbáltad, rájöttél, hogy nincs is bezárva, így kinyitottad.");
                                    return 12;
                                }
                                else return -7;
                            }
                            else return -6;
                        }
                        else return -7;
                    }
                    else if(mightbegood==2)
                    {
                        if(objects[i].getOpenable())
                        {
                            if(!objects[i].getIsOpen())
                            {
                                if(player.getPosition()==objects[i].getPosition())
                                {
                                    if(xtrahelper.equals("kulcs")&&helper.equals("ajtó")) return 10;
                                    else if(xtrahelper.equals("feszítővas")&&helper.equals("ablak")) return 11;
                                    else if (helper.equals("szekrény"))
                                    {
                                        System.out.println("A szekrényt nem tudtad kinyitni, de miközben próbáltad, rájöttél, hogy nincs is bezárva, így kinyitottad.");
                                        return 12;
                                    }
                                    else if(helper.equals("doboz"))
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
                if(xtrahelper.equals(objects[i].getName()))
                {
                    if(objects[i].getOpenable())
                    {
                        if(!objects[i].getIsOpen())
                        {
                            if(objects[i].getName().equals("szekrény") )
                            {
                                return 12;
                            }
                            if(objects[i].getName().equals("doboz"))
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
        return 0;
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
            if(helper.equals(objects[i].getName()))
            {
                if(objects[i].getMoveable())
                {
                    if(!objects[i].getIsmoved())
                    {
                        if(objects[i].getPosition()==player.getPosition()) return i;
                        else return -3;
                    }
                    else return 0;
                }
                else return -1;
            }
            else if(i==objects.length-1) return -2;
        }
        return 0;
    }
    private int Objectchecker5 (String command)
    {
        String helper = "";
        Boolean needmore = false;
        for(int i = 5;i<command.length();i++)
        {
            if(command.charAt(i)==' ')
            {
                needmore = true;
            }
            else
            helper+=command.charAt(i);
        }
        if(needmore)
        {
            String xtrahelper = "";
            for(int i = 0;i<command.length();i++)
            {
                xtrahelper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(xtrahelper.equals(objects[i].getName())) i = objects.length;
                else if(i==objects.length-1) return 0;
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper.equals(objects[i].getName())) i= objects.length;
                else if(i==objects.length-1) return 0;
            }
            if(helper.equals("ablak") || helper.equals("feszítővas"))
            {
                if(xtrahelper.equals("ablak") || xtrahelper.equals("feszítővas"))
                {
                    if(!helper.equals(xtrahelper)) return 1;
                    else return -1;
                }
                else return -1;
            }
            else return -1;
        }
        else
        {
            for(int i = 0;i<objects.length;i++)
            {
                if(helper.equals(objects[i].getName()))
                {
                    if(objects[i].getName().equals("ablak"))
                    {
                        return -2;
                    }
                    else return -3;
                }
                else if(i==objects.length-1) return 0;
            }
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
            if(helper.equals(directions[i])) return true;
            else if(i==directions.length-1) return false;
        }
        return false;
    }
    private int IsCommandKnown(String command)
    {
        String helper="";
        for(int i = 0;i<command.length() && command.charAt(i)!=' ';i++)
        {
            helper+=command.charAt(i);
        }
        if(helper.length()==5) return 4;
        else if(helper.length()==4)
        {
            for(int i = 0;i<commands.length;i++)
            {
                if(helper.equals(commands[i].getName()))
                {
                    return i;
                }
                else if(i==commands.length-1) return -1;
            }
        }
        return -1;
    }
}