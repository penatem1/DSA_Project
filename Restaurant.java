public class Restaurant {
  int npStart, lineLength;

  ListRA<Table> tables;
  Deq<CustomerParty> customerLine;
  
/**
* Constructor for Restauarant Class includes deq for the line of customers.
*@param tables a resizable array list containing references to table objects
*@param npStart an int value that splits the array into pet-friendly and non-pet friendly sections
*/
  public Restaurant(ListRA<Table> tables, int npStart) {
    this.tables = tables;
    this.npStart = npStart;

    customerLine = new Deq<CustomerParty>();
    lineLength = 0;
  }
  
/**
* Method to add a new party to the line of customers
* @param party a customer part object to be added to the customer party Deq
*/
  public void addNewParty(CustomerParty party) {
    customerLine.enqueue(party);
    lineLength++;
  }
  
/**
*Method to sit a waiting party in the customer line (deq)
*/
  public void seatWaitingParty() {
    int skipped = -1;
    while(skipped++ != lineLength) {
      CustomerParty party = customerLine.dequeue();
      int tableIndex = canSeat(party.getSize());
      if (tableIndex >= 0) {
        lineLength--;
        tables.get(tableIndex).addParty(party);
        break;
      } else {
        customerLine.enqueue(party);
      }
    }

    if (skipped!=lineLength)
      while(skipped-->0)
        customerLine.enqueueFirst(customerLine.dequeueLast());
  }
  
/**
* Method to see if a party can be seated in one of the tables
*@param seatCount gives number of seats needed for the party looking to be seated
*@return returns the first table that can seat the party, or -1 if no such table is avalable
*/
  private int canSeat(int seatCount) {
    int size = tables.size();
    for(int i = 0; i < size; i++)
      if (tables.get(i).canSeat(seatCount))
        return i;
    return -1;
  }
  
/**
* Method to remove parties from table who have finished being served
*@param name Name of the party that will be removed from the table
*/
  public void finishServing(String name) {
  booolean found = false;
  int size = tables.size();
    //can check if this is empty in the driver (keep track of how many parties have been added  and if == 0)
    for(int i = 0, i < size; i++)
      {
        if(tables.get(i).peekParty().getName().equals(name))
        {         
         found = true;
         System.out.print("Table "+tables.get(i).getName()+" has been freed");
         System.out.print(tables.get(i).peekParty().toString()+" is leaving the restaurant");
         tables.get(i).removeParty();
        }
      }
    if(!found)
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
  int size = tables.size();
  boolean insertHere = true;
  if(petOK == true)
  {
    if(tables.isEmpty()) {
          tables.add(0,table);
          npStart++;  }
    
    else if(table.getSeatcount() > tables.get(npStart-1).getSeatcount() {
           tables.add(npStart, table);
           npStart++;  }
    else{
         for(int i = 0; i < npStart;i++)
        {
           if(table.getSeatCount() > tables.get(i).getSeatCount()){
                insertHere = false;
                }
           if(insertHere){
                tables.add(i, table)
                npStart++;
                break;  }                       
         }     
  }
    System.out.println("Table "+table.getName()+" has been added to the Pet Friendly Section");
  }
  else 
  {
    if(tables.isEmpty()) {
          tables.add(0,table);
           }
    
    else if(table.getSeatcount() > tables.get(size-1).getSeatcount() {
           tables.add(size, table);
            }
    else{
         for(int i = npStart; i < size; i++)
        {
           if(table.getSeatCount() > tables.get(i).getSeatCount()){
                insertHere = false;
                }
           if(insertHere){
                tables.add(i, table)
                break;  }                       
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
  public void rmTable(String name, boolean petOK) {
    int start = (petOK)?0:npStart;
    int end = (petOK)?npStart-1:tables.size();
    for(; start<end; start++)
      if (tables.get(start).getName().equals(name)) {
        tables.remove(start);
        break;
      }
  }
            
/**
*Method to see the available tables in each of the sections of the restaurant
*/
  public void getAvailableTables() {
    size = tables.size(); 
    System.out.prinln("The following tables are available in the pet-friendly section:");
    for( int i = 0; i < npStart; i++)
    {
    if(tables.get(i).peekParty().equals(null))
    {
     System.out.println("Table "+tables.get(i).getName()+" with "+tables.get(i).getSeatCount()+" seats"
    }
    }
    System.put.println("The following tables are available in the non-pet-friendly section:")
    for (int i = npStart; i < size; i++)
    {
      if(tables.get(i).peekParty().equals(null))
      {
      System.out.println("Table "+ tables.get(i).getName()+" with "+tables.get(i).getSeatCount()+" seats"
      }  
    }     
}
                         
 /**
 *Method to get the waiting customers in line
 *@return returns a string of customers in line waiting to be seated 
 */
  public String getWaitingCustomers() {
    return customerLine.toString();
  }
                         
/**
*Method to geet the customers that are currently seated at tables
*@return a string of all of the customers that are at each of the tables of the restaurant
*/
  public String getSeatedCustomers() {
    String out = "";
    int size = tables.size();
    for(int i = 0; i < size; i++)
      if (tables.get(i).isTaken())
          out += tables.get(i).peekParty().toString() + "\n";

    return out;
  }
}
