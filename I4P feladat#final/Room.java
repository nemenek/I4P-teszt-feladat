import java.io.Serializable;

class Room implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    public Room(String name,String description)
    {
        this.name = name;
        this. description=description;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String newDescription)
    {
        description=newDescription;
    }
}