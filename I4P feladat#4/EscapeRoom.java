import java.util.Scanner;
class Szabaduloszoba
{
    String[] commands = new String[10];
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
        objects[0] = new Objects(false, 0, "szekrény", true, true);
        objects[1] = new Objects(false, 0, "kád", false, false);
        objects[2] = new Objects(false, 1, "láda", true, false);
        objects[3] = new Objects(false, -1, "ajtó", false,true);
        objects[4] = new Objects(false, 0, "ablak", false, true);
        objects[5] = new Objects(true, 0, "doboz", true, true);
        objects[6] = new Objects(true, 0, "kulcs", false, false);
        objects[7] = new Objects(true, 1, "feszítővas", false, false);
        rooms[0] = new Room("nappali", "\nA nappaliban vagy. Északra található egy szekrény. Nyugatra látsz egy ajtót.\n");
        rooms[1] = new Room("fürdőszoba", "\nA fürdőszobában vagy. Itt található egy kád. Keletre egy nyitott ajtó van.\n");
    }
    private void Writeline()
    {
        System.out.println( "A lehetséges parancsok:\nmenj 'irány'\nnézd\nvedd fel 'tárgy'\ntedd le 'tárgy'\nnyisd 'tárgy' 'tárgy'\nhúzd 'tárgy\ntörd 'tárgy'\n\nleltár\nmentés\nbetöltés\n");
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
        return player.getPosition()!=-1;
    }
    private void CommandReader(String command)
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
                l=DirectionChecker(command);
                if(l==-1)
                {
                    throw new BadDirectionException();
                }
                else RunCommand(k, command,l);
            }
            else if(k==1)
            {
                RunCommand(k, command,-1);
            }
            else if (k==2)
            {
                l = ObjectChecker1(command);
                if(l==-5)
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
                    RunCommand(k, command, l);
                }
            }
            else if(k==3)
            {
                l=ObjectChecker2(command);
                if(l==-3)
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
                else RunCommand(k, command, l);
            }
            else if(k==4)
            {
                l=Objectchecker3(command);
                if(l==-9)
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
                    RunCommand(k, command, l);
                }
                else if(l==11)
                {
                    RunCommand(k, command, l);
                }
                else if(l==12)
                {
                    RunCommand(k, command, l);
                }
                else if(l==14)
                {
                    RunCommand(k, command, l);
                }
            }
            else if (k==5)
            {
                l = Objectchecker4(command);
                if(l==-4)
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
                else RunCommand(k, command, l);
            }
            else if(k==6)
            {
                l=Objectchecker5(command);
                if(l==-4)
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
                RunCommand(k, command, -1);
            }
            else if (k==8)
            {
                RunCommand(k, command, -1);
            }
            else if(k==9)
            {
                RunCommand(k, command, -1);
            }
        }
        catch(BadCommandException e)
        {
            System.out.println( "\nA megadott parancs ismeretlen!\n");
        }
        catch(BadDirectionException e)
        {
            System.out.println( "\nNem adott meg, vagy rosszul adta meg az irányt!\n");
        }
        catch(BadObjectException e)
        {
            System.out.println( "\nNem írt be, vagy rosszul írta be a tárgyat!\n");
        }
        catch(NotPickableObjectException e)
        {
            System.out.println(  "\nA megadott tárgy nem felvehető!\n");
        }
        catch (ObjectNotInInventoryException e)
        {
            System.out.println(  "\nA letenni kívánt tárgy nincs nálad!\n");
        }
        catch(ObjectAlreadyMovedException e)
        {
            System.out.println(  "\nNem tudod mozdítani a tárgyat, mert már egyszer elmozdítottad!\n");
        }
        catch(ObjectIsNotMoveableException e)
        {
            System.out.println(  "\nA megmozdítani kívánt tárgy nem mozdítható!\n");
        }
        catch(ObjectAlreadyOpenedException e)
        {
            System.out.println(  "\nEz a tárgy már ki van nyitva!\n");
        }
        catch(ObjectIsNotOpenableAndDoesntOpenAnythingException e)
        {
            System.out.println(  "\nAz elsőnek beírt tárgy nem nyit semmit és nem is nyitható!\n");
        }
        catch(ObjectIsNotOpenableException e)
        {
            System.out.println(  "\nAz első tárggyal ki lehet nyitni egy másikat, de a második beírt tárgy nem kinyitható!\n");
        }
        catch(ObjectCantBeOpenedWithBareHandsException e)
        {
            System.out.println(  "\nA beírt tárgyat nem lehet kinyitni puszta kézzel!\n");
        }
        catch(ObjectsDoesntInteractWithEachOtherException e)
        {
            System.out.println(  "\nA két beírt tárgy nem interaktál egymással!\n");
        }
        catch(PlayerIsNotInTheRoomException e)
        {
            System.out.println(  "\nA tárgy amellyen interakcióba akarsz lépni, nem található a szobában!\n");
        }
        catch(ObjectAlreadyPickedUpException e)
        {
            System.out.println(  "\nEz a tárgy már nálad van!\n");
        }
        catch(ObjectIsNotBrakeableException e)
        {
            System.out.println(  "\nA beírt tárgyat nem lehet eltörni!\n");
        }
        catch(ObjectCantBeBrokeWithBareHandsException e)
        {
            System.out.println(  "\nAz ablakot nem törheted be puszta kézzel, mert megvágnád magad!\n");
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
            return -5;
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
        return -5;
    }
    private int ObjectChecker2(String command)
    {
        String helper = "";
        for(int i = 5;i<8;i++)
        {
            helper+=command.charAt(i);
        }
        if(!helper.equals("le ")) return -3;
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
        return -3;
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
                            if(player.getPosition()==objects[i].getPosition() || objects[i].getPosition()==-1)
                            {
                                mightbegood = 1;
                                i=objects.length;
                            }
                        }
                        else return -9;
                    }
                    else if (objects[i].getPickable())
                    {
                        if(objects[i].getIsInInventory())
                        {
                            mightbegood=2;
                            i=objects.length;
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
                                else if(xtrahelper.equals("ablak") && helper.equals("feszítővas") && objects[3].getIsmoved()) return 11;
                                else if(xtrahelper.equals("szekrény"))
                                {
                                    System.out.println("A szekrényt nem tudtad kinyitni az adott tárggyal, de miközben próbáltad, rájöttél, hogy nincs is bezárva, így kinyitottad.");
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
                                if(player.getPosition()==objects[i].getPosition() || objects[i].getPosition()==-1)
                                {
                                    if(xtrahelper.equals("kulcs")&&helper.equals("ajtó")) return 10;
                                    else if(xtrahelper.equals("feszítővas")&&helper.equals("ablak") && objects[3].getIsmoved()) return 11;
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
                            else return -9;
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
                        return -9;
                    }
                    return -4;
                }
                else if(i==objects.length-1) return -1;
            }
        }
        return -9;
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
                    else return -4;
                }
                else return -1;
            }
            else if(i==objects.length-1) return -2;
        }
        return -4;
    }
    private int Objectchecker5 (String command)
    {
        String helper = "";
        Boolean needmore = false;
        int k=0;
        for(int i = 5;i<command.length();i++)
        {
            if(command.charAt(i)==' ')
            {
                needmore = true;
                k=i;
                i=command.length();
            }
            else helper+=command.charAt(i);
        }
        if(needmore)
        {
            String xtrahelper = "";
            for(int i = k;i<command.length();i++)
            {
                xtrahelper+=command.charAt(i);
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(xtrahelper.equals(objects[i].getName())) i = objects.length;
                else if(i==objects.length-1) return -4;
            }
            for(int i = 0;i<objects.length;i++)
            {
                if(helper.equals(objects[i].getName())) i= objects.length;
                else if(i==objects.length-1) return -4;
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
                else if(i==objects.length-1) return -4;
            }
        }
        return -4;
    }
    private int DirectionChecker(String command)
    {
        String helper = "";
        for(int i = 5;i<command.length();i++)
        {
            helper+=command.charAt(i);
        }
        for(int i = 0;i<directions.length;i++)
        {
            if(helper.equals(directions[i])) return i;
            else if(i==directions.length-1) return -1;
        }
        return -1;
    }
    private int IsCommandKnown(String command)
    {
        String helper="";
        for(int i = 0;i<command.length();i++)
        {
            if(command.charAt(i)!=' ')
            {
                helper+=command.charAt(i);
            }
            else i=command.length();
        }
        if(helper.length()==5) return 4;
        else if(helper.length()==4)
        {
            for(int i = 0;i<commands.length;i++)
            {
                if(helper.equals(commands[i]))
                {
                    return i;
                }
                else if(i==commands.length-1) return -1;
            }
        }
        else if(helper.length()==8) return 9;
        else if(helper.equals("leltár")) return 7;
        else if(helper.equals("mentés")) return 8;
        return -1;
    }
    private void RunCommand(int type, String command, int help)
    {
        if(type == 0)
        {
            if(player.getPosition()==0)
            {
                if(help==2)
                {
                    if(objects[3].getIsOpen())
                    {
                        player.setPosition(1);
                        System.out.println("\nÁtmentél a fürdőszobába.\n");
                        for(int i = 0;i<player.getInventory().length && player.getInventory()[i]!=null;i++)
                        {
                            player.getInventory()[i].setPosition(1);
                        }
                    }
                    else System.out.println("\nAz ajtó kulcsra van zárva, nem tudsz átmenni rajt.\n");
                }
                else System.out.println("\nEbbe az irányba nem található átjáró!\n");
            }
            else if(player.getPosition()==1)
            {
                if(help==3)
                {
                    player.setPosition(0);
                    System.out.println("\nÁtmentél a nappaliba.\n");
                    for(int i = 0;i<player.getInventory().length && player.getInventory()[i]!=null;i++)
                    {
                        player.getInventory()[i].setPosition(0);
                    }
                }
                else if(help==0)
                {
                    if(objects[4].getIsOpen())
                    {
                        player.setPosition(-1);
                        System.out.println("\nKijutottál!\n");
                    }
                    else System.out.println("Nem tudsz átmenni az ablakon. Obviously.");
                }
                else System.out.println("\nEbbe az irányba nem található átjáró!\n");
            }
        }
        else if(type==1)
        {
            System.out.println(rooms[player.getPosition()].getDescription());
        }
        else if(type==2)
        {
            if(help == 5)
            {
                if(objects[0].getIsOpen())
                {
                    objects[5].setIsInInventory(true);
                    player.setInventory(objects[5]);
                    System.out.println("\nA doboz most már nálad van.\n");
                    rooms[0].setDescription("\nA nappaliban vagy. Északra található egy nyitott szekrény. Nyugatra látsz egy ajtót.\n");
                }
            }
            else if(help==6)
            {
                if(objects[5].getIsOpen())
                {
                    objects[6].setIsInInventory(true);
                    player.setInventory(objects[6]);
                    System.out.println("\nA kulcs most már nálad van.\n");
                }
            }
            else if(help==7)
            {
                objects[7].setIsInInventory(true);
                {
                    player.setInventory(objects[7]);
                    System.out.println("\nA feszítővas most már nálad van.\n");
                }
            }
        }
        else if(type==3)
        {
            objects[help].setIsInInventory(false);
            player.setInventory(objects[help]);
            System.out.println("\nA " + objects[help].getName() + " nevű tárgyat sikeresen eltávolítottad a leltáradból.\n");
        }
        else if(type==4)
        {
            if(help==10)
            {
                objects[3].setIsOpen(true);
                System.out.println("\nAz ajtó kinyílt.\n");
            }
            else if(help==11)
            {
                objects[4].setIsOpen(true);
                System.out.println("\nFelfeszítetted az ablakot a feszítővassal.\n");
                rooms[0].setDescription("\nA nappaliban vagy. Északra található egy szekrény. Mellette egy nyitott ablak van, amin friss levegő áramlik be. Nyugatra látsz egy ajtót.\n");
            }
            else if(help==12)
            {
                objects[0].setIsOpen(true);
                System.out.println("\nKinyitottad a szekrényt, egy dobozt látsz benne.\n");
            }
            else if(help==14)
            {
                objects[5].setIsOpen(true);
                System.out.println("\nKinyitottad a dobozt. A dobozban egy kulcs van.\n");
            }
        }
        else if(type==5)
        {
            if(help==0)
            {
                objects[help].setIsmoved(true);
                System.out.println("\nArrébhúztad a szekrényt. Mögötte egy ablakot találsz (északra).\n");
                rooms[0].setDescription("\nA nappaliban vagy. Itt található egy szekrény. Nyugatra látsz egy ajtót.\n");
            }
            else if(help==1)
            {
                objects[help].setIsmoved(true);
                System.out.println("\nArrébtoltad az ágyat, de nem volt semmi mögötte.\n");
            }
            else if(help==6)
            {
                objects[help].setIsmoved(true);
                System.out.println("\nArrébraktad a dobozt, de nem értél el vele semmit.\n");
            }
        }
        else if(type==6)
        {
            if(player.getPosition()==objects[4].getPosition())
            {
                objects[4].setIsOpen(true);
                System.out.println("\nBetörted az ablakot a feszítővassal. Bár akár fel is feszíthetted volna...\n");
            }
        }
        else if(type==7)
        {
            for(int i = 0;i<player.getInventory().length && player.getInventory()[i]!=null;i++)
            {
                System.out.println("\n" + player.getInventory()[i].getName() + "\n");
            }
        }
    }
}