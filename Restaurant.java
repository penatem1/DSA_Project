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
        lineLenth--;
        tables.get(i).addParty(party);
        break;
      } else {
        customerLine.enqueue(party);
      }
    }

    if (skipped!=size)
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

  }

  public void addTable(Table table, boolean petOK) {

  }

  public void rmTable(String name, boolean petOK) {
    int start = (petOK)?0:npStart;
    int end = (petOK)?npStart-1:tables.length();
    for(start; start<end; start++)
      if (tables.get(i).getName().equals(name)) {
        tables.remove(i);
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
