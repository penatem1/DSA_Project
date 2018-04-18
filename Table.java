public class Table {
    String name;
    int seatCount;
    CustomerParty party;
    /**
    *Constructor for table class
    *@param name holds name of the table
    *@param seatCount int which holds the number of seats available at a table
    */
    public Table(String name, int seatCount) {
        this.name = name;
        this.seatCount = seatCount;
        party = null;
    }

    /**
    *Method for getting name of a table
    *@return returns the name of the table
    */
    public String getName() {
        return name;
    }

    /**
    *Method for getting seatCount of a table
    *@return returns the number of seats at a table
    */
    public int getSeatCount() {
        return seatCount;
    }

    /**
    *Method to see if the a table has enough seats for requested amount
    *@param requestedSeats number of seats that are needed
    *@return returns boolean of whether the requested number <= nummber of seats available at the table
    */
    public boolean canSeat(int requestedSeats) {
        return (requestedSeats <= seatCount);
    }

    /**
    *Adds a customerParty object to a table
    *@param party customerParty object to be added to the table
    */
    public void addParty(CustomerParty party) {
        this.party = party;
    }

    /**
    * Method for whether a table already has a customerParty object assigned to it
    * @return returns if the table is already occupied by a party
    */
    public boolean isTaken() {
        return (party!=null);
    }

    /**
    *Method to remove a party from a table
    *@return returns party that has been removed from the table
    */
    public CustomerParty removeParty() {
        CustomerParty temp = party;
        party = null;
        return temp;
    }

    /**
    *Method to peek at the CustomerParty currently occupying a table
    *@return returns the party that is being served at the table
    */
    public CustomerParty peekParty() {
        return party;
    }
}
