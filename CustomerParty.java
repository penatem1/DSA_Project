/*
* Purpose: Data Structure and Algorithms Project
* Status: Complete and thoroughly tested
* Last update: 04/18/18
* Submitted:  04/19/18
* Comment: test suite and sample run attached
* @author: Marc Gregory-Dixon
* @version: 2018.02.01
*/

import java.util.*;

public class CustomerParty
{

    private String name; //party name
    private int size; //size of party
    private boolean petOk; //whether of not the party is pet friendly
    /**
    * Constructor for the CustomerParty class
    * @param name stores the name of the customer party
    * @param size int that stores the number of seats needed for the customer party
    * @param petOk boolean that signifies pet or non-pet section of the restaurant
    */
    public CustomerParty(String name,int size, boolean petOk) //default full-filler constructor
    {
        this.name = name;
        this.size = size;
        this.petOk = petOk;
    }

    /**
    * Method for getting size of the customer party
    *@return returns the size of the customer party
    */
    public int getSize()
    {
        return size;
    }

    /**
    * Method for getting name of the customer party
    *@return returns the name of the customer party
    */
    public String getName()
    {
        return name;
    }

    /**
    * Method for getting section for the customer party
    *@return returns the section for the customer party
    */
    public boolean getPetOk()
    {
        return petOk;
    }

    /**
    *Method to return information about a customerParty object
    *@return string with information about the customerParty
    */

    public String toString()
    {
        String petVal =""; //to be filled with pet or no pet depending on petok
        if (petOk==true)
        {
            petVal = "Pet";
        }
        else
        {
            petVal = "No Pet";
        }
        String val ="";
        val = val.concat(name+" party of "+Integer.toString(size)+"("+petVal+")"); //make full party string
        return val; //return that string
    }
}
