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

  }

  public void seatWaitingParty() {
    int skipped = -1, size = tables.size();
    while(skipped++ != size)
      if (canSeat(customerLine.peek))
  }

  private boolean canSeat(int seatCount) {
    int size = tables.size();
    for(int i = 0; i < size; i++)
      if (tables.get(i).canSeat(seatCount))
        return true;
    return false;
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
