class Directions
{
    String[] directions = new String[4];
    public Directions()
    {
        directions[0] = "észak";
        directions[1] = "dél";
        directions[2] = "kelet";
        directions[3] = "nyugat";
    }
    public String[] getDirections()
    {
        return directions;
    }
}