import java.util.*;

public class CustomerParty
{
    private String name;
    private int size;
    private boolean petOk;
    private String sizeVal;
  
    public CustomerParty(String name,int size, boolean petOk)
    {  
      this.name = name;
      this.size = size;
      this.petOk = petOk;
    }

    public int getSize()
    {
        return size;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getPetOk()
    {
        return petOk;
    }
    
    public String toString()
    {
      String petVal ="";
      if (petOk==true)
      {
       petVal = "Pet";
      }
       else
      {
       petVal = "No Pet";
      }
      String val ="";
      val = val.concat(name+" party of "+Integer.toString(size)+"("+petVal+")");
      return val;
    }
}
