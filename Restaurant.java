/*
* Purpose: Data Structure and Algorithms Project
* Status: Complete and thoroughly tested
* Last update: 04/18/18
* Submitted:  04/19/18
* Comment: test suite and sample run attached
* @author: Marcus Penate and Marc Gregory-Dixon
* @version: 2018.02.01
*/

public class Restaurant {
    int npStart, lineLength; //npStart is the index in the tables list where non pet (np) tables start, lineLength is the size of the customerLine

    ListRA<Table> tables; //list of tables in restaurant
    Deq<CustomerParty> customerLine; //waiting line of customers


    /**
    * Constructor for Restauarant Class includes deq for the line of customers.
    *@param tables a resizable array list containing references to table objects
    *@param npStart an int value that splits the array into pet-friendly and non-pet friendly sections
    */
    public Restaurant(ListRA<Table> tables, int npStart) { //receive initial list of tables from outside constructor
        this.tables = tables;
        this.npStart = npStart;

        customerLine = new Deq<CustomerParty>(); //init line
        lineLength = 0; //no one in line yet
    }


    /**
    * Method to add a new party to the line of customers
    * @param party a customer part object to be added to the customer party Deq
    */
    public void addNewParty(CustomerParty party) {
        customerLine.enqueue(party); //add party to line
        lineLength++; //keep track of length
    }

    /**
    *Method to sit a waiting party in the customer line (deq)
    */
    public void seatWaitingParty() {
        if(customerLine.isEmpty()) { //no one in line?
            System.out.println("No customer to seat!"); //say that
            return; //do nothing more
        }

        int skipped = -1; //keeps track of how many people are skipped in line (skipped = moved to back of line)
        while(skipped++ != lineLength-1) { //while there are still people to skip
            CustomerParty party = customerLine.dequeue(); //get the next possible party
            int tableIndex = canSeatParty(party.getSize(), party.getPetOk()); //check if there have a valid table to sit at
            if (tableIndex >= 0) { //value greater than -1 is a valid table!
                lineLength--; //line is now one smaller
                tables.get(tableIndex).addParty(party); //add party to that table
                System.out.println("Serving Customer " + party.toString() + " at table " + tables.get(tableIndex).getName() + " with " + tables.get(tableIndex).getSeatCount() + " seats."); //output the success!
                break; //stop looping
            } else {
                System.out.println("Could not find a table with " + party.getSize() + " seats for customer " + party.getName() + "!"); //say no table could be found
                customerLine.enqueue(party); //add the once possible party to the back of the line
            }
        }

        if (skipped!=lineLength) //if the line was not completely reinserted into itself (the front was put to the back enough times to just get the same line)
            while(skipped-->0) //skipped kept track of how many people were moved to the back of the line
                customerLine.enqueueFirst(customerLine.dequeueLast()); //move those parties to the front of the line
    }

    /**	+  private int canSeatParty(int seatCount, boolean petOk) {
    * Method to see if a party can be seated in one of the tables
    *@param seatCount gives number of seats needed for the party looking to be seated
    *@param petOk if the party is ok with being in the pet section
    *@return returns the first table that can seat the party, or -1 if no such table is avalable
    */
    private int canSeatParty(int seatCount, boolean petOk) {
        int end = (petOk)?npStart:tables.size(); //use petOk to find starting index for loop
        int i = (petOk)?0:npStart; //use petOk to find ending index of the loop
        for(; i < end; i++) //loop over those indexes
            if (!tables.get(i).isTaken() && tables.get(i).canSeat(seatCount)) //if the table is vacant and can seat the party
                return i; //return that index
        return -1; //if no table was found return -1
    }

    /**
    * Method to remove parties from table who have finished being served
    *@param name Name of the party that will be removed from the table
    */
    public void finishServing(String name) {
        boolean found = false; //keep track of if we found the party
        int size = tables.size(); //used for looping
        //can check if this is empty in the driver (keep track of how many parties have been added  and if == 0
        for(int i = 0; i < size; i++) //loop over all tables
        {
            if(tables.get(i).isTaken() && tables.get(i).peekParty().getName().equals(name)) //check for a taken table with the same party name
            {
                found = true; //we found it!
                //do some output
                System.out.print("Table "+tables.get(i).getName()+" has been freed, ");
                System.out.println(tables.get(i).peekParty().toString()+" is leaving the restaurant.");
                tables.get(i).removeParty(); //remove the party from that table
            }
        }
        if(!found) //check for never finding that party and output if we didn't
        {
            System.out.println("Party "+name+" is not being served");
        }
    }

    /**
    *Method to add a table to either the pet or non-pet section of the restaurant
    *@param table reference to a table object to be added to the list of tables
    *@param petOK boolean to signify which section of the restaurant to place the table in
    */
    public void addTable(Table table, boolean petOK) {
        int size = tables.size(); //used for looping
        boolean insertHere = true; //used for ascending order insertions
        if(petOK == true) //pet section?
        {
            if(tables.isEmpty()) { //first add
                tables.add(0,table); //add at 0, increase npStart
                npStart++;
            }

            else if(table.getSeatCount() > tables.get(npStart-1).getSeatCount()) { //does it belong at the end of the pet section?
                tables.add(npStart, table); //add it and increase npStart
                npStart++;
            }
            else {
                for(int i = 0; i < npStart; i++) //loop over the tables in pet section
                {
                    insertHere = true; //default to saying we should insert the table here
                    if(table.getSeatCount() > tables.get(i).getSeatCount()) { //but should we actually insert it here?
                        insertHere = false; //if we shouldnt, keep track of that
                    }
                    if(insertHere) { //if we should, insert it here and increase npstart
                        tables.add(i, table);
                        npStart++;
                        break;
                    }
                }
            }
            System.out.println("Table "+table.getName()+" has been added to the Pet Friendly Section"); //say we added the table
        }
        else
        {
          //SAME AS ABOVE CODE BUT FOR NON PET FRIENDLY SECTION, NPSTART DOES NOT NEED TO BE CHANGED AT ALL FOR THIS SECTION
            if(tables.isEmpty()) {
                tables.add(0,table);
            }

            else if(table.getSeatCount() > tables.get(size-1).getSeatCount()) {
                tables.add(size, table);
            }
            else {
                for(int i = npStart; i < size; i++)
                {
                    insertHere = true;
                    if(table.getSeatCount() > tables.get(i).getSeatCount()) {
                        insertHere = false;
                    }
                    if(insertHere) {
                        tables.add(i, table);
                        break;
                    }
                }
            }
            System.out.println("Table "+table.getName()+" has been added to the No Pets Section");
        }
    }

    /**
    *Method that removes a table from either section of the restaurant
    *@param name gives the name of the table to be removed
    *@param petOK signifies which section to remove the table from
    */
    public boolean rmTable(String name, boolean petOK) {
        int start = (petOK)?0:npStart; //use petOK to find starting index
        int end = (petOK)?npStart:tables.size(); //use petOk to find ending index
        if(petOK) //if pet section, decrement npStart
            npStart--;
        for(; start<end; start++) { //loop over the indexes
            if (tables.get(start).getName().equals(name)) { //find the table with the same name
                if(tables.get(start).isTaken()) { //make sure it isnt taken
                    System.out.println("Cannot remove a table currently in use!"); //output that the table is in use
                    if(petOK) //if we decremented earlier
                        npStart++; //increment, decrementing was a mistake because no table can be removed
                    return false; //unsuccessful removal
                }
                tables.remove(start); //remove ad the current index
                return true; //successful removal
            }
        }
        if(petOK) //if we decremented earlier
            npStart++; //increment, decrementing was a mistake because no table can be removed
        return false; //unsuccessful removal
    }

    /**
    *Method to see the available tables in each of the sections of the restaurant	+    size = tables.size();
    */
    public void getAvailableTables() {
        int size = tables.size(); //used for looping
        System.out.println("The following " + npStart + " tables are available in the pet-friendly section:"); //output pet section header
        for( int i = 0; i < npStart; i++) //loop over pet section
        {
            if(tables.get(i).peekParty() == null) //check for availability
            {
                System.out.println("Table "+tables.get(i).getName()+" with "+tables.get(i).getSeatCount()+" seats"); //output if available
            }
        }

        //AS ABOVE BUT FOR NON PET FRIENDLY SECTION, JUST DIFFERENT INDEXES
        System.out.println("The following " + (tables.size() - npStart) + " tables are available in the non-pet-friendly section:");
        for (int i = npStart; i < size; i++)
        {
            if(tables.get(i).peekParty() == null)
            {
                System.out.println("Table "+ tables.get(i).getName()+" with "+tables.get(i).getSeatCount()+" seats");
            }
        }
    }

    /**
    *Method to get the names of all tables in a section
    *@param petOk if true the petOK section will be searched, opposite if false
    *@return String of the tables names, separated by new line characters
    */
    public String getTableNames(boolean petOK) {
        String out = ""; //initial output
        int start = (petOK)?0:npStart; //use petOK to find starting index
        int end = (petOK)?npStart:tables.size(); //use petOK to find ending index
        for(; start<end; start++) //loop over the indexes
            out += tables.get(start).getName() + "\n"; //add table names to output
        return out; //return all table names
    }

    /**
    *Method to get the waiting customers in line
    *@return returns a string of customers in line waiting to be seated
    */
    public String getWaitingCustomers() {
        String out = customerLine.toString("\n"); //get information from customer line
        return (out.equals(""))?"No customer waiting in line.":out; //returns the line or no one waiting in line
    }

    /**
    *Method to geet the customers that are currently seated at tables
    *@return a string of all of the customers that are at each of the tables of the restaurant
    */
    public String getSeatedCustomers() {
        String out = ""; //for output
        int size = tables.size(); //for looping
        for(int i = 0; i < size; i++) //loop over all tables
            if (tables.get(i).isTaken()) //make sure the table is taken first
                out += tables.get(i).peekParty().toString() + "\n"; //add the party at said table to the output
        if(out.equals("")) //if nothing was added to the output
            out = "No parties seated currently."; //say that
        return out; //return the output
    }
}
