public class Table {
  String name;
  int seatCount;
  CustomerParty party;

  public Table(String name, int seatCount) {
    this.name = name;
    this.seatCount = seatCount;
    party = null;
  }

  public String getName() { return name; }

  public int getSeatCount() { return seatCount; }
  public boolean canSeat(int requestedSeats) { return (requestedSeats <= seatCount); }

  public void addParty(CustomerParty party) { this.party = party; }
  public boolean isTaken() { return (party!=null); }
  public CustomerParty removeParty() {
    CustomerParty temp = party;
    party = null;
    return party;
  }
  public CustomerParty peekParty() { return party; }
}
