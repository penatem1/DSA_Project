public class Restaurant {
  int npStart, lineLength;

  ListRA<Table> tables;
  Deq<CustomerParty> customerLine;

  public Restaurant(ListRA<Table> tables, int npStart) {
    this.tables = tables;
    this.npStart = npStart;

    customerLine = new Deq<CustomerParty>();
    lineLength = 0;
  }

  public void addNewParty(CustomerParty party) {
    customerLine.enqueue(party);
    lineLength++;
  }

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

  private int canSeat(int seatCount) {
    int size = tables.size();
    for(int i = 0; i < size; i++)
      if (tables.get(i).canSeat(seatCount))
        return i;
    return -1;
  }

  public void finishServing(String name) {
  booolean found = false;
  int size = tables.size();
    //can check if this is empty in the driver (keep track of how many parties have been added  and if == 0 
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
  public void rmTable(String name, boolean petOK) {
    int start = (petOK)?0:npStart;
    int end = (petOK)?npStart-1:tables.size();
    for(; start<end; start++)
      if (tables.get(start).getName().equals(name)) {
        tables.remove(start);
        break;
      }
  }

  public String getAvailableTables() {

  }

  public String getWaitingCustomers() {
    return customerLine.toString();
  }

  public String getSeatedCustomers() {
    String out = "";
    int size = tables.size();
    for(int i = 0; i < size; i++)
      if (tables.get(i).isTaken())
          out += tables.get(i).peekParty().toString() + "\n";

    return out;
  }
}
