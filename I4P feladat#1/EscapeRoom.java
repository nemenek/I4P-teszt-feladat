class Szabaduloszoba
{
    private String[] inventory = new String[3];
    public Szabaduloszoba()
    {
        
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
        }while(Over());
    }
    private string Choose(string input)
    {

    }
    private Boolean Over()
    {
        return false;
    }
}