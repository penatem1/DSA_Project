/*
* Purpose: Data Structure and Algorithms Lab 8 Problems 2
* Status: Complete and thoroughly tested
* Last update: 03/28/18
* Submitted:  03/29/18
* Comment: test suite and sample run attached
* @author: Marcus Penate
* @version: 2018.02.01
*/

import java.util.Scanner;

public class Driver {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in); //scanner for input

    Restaurant rest = initRestaurant(scan);

    String p_name, p_size;
    boolean petOk;
    int in;
    System.out.println("\nSelect from the following menu: \n1. Insert item to list.\n2. Remove item from list.\n3. Get item from list.\n4. Search for a specified item in the list.\n5. Clear list.\n6. Print size and content of list.\n7. Exit program.");
    do {
      in = Integer.parseInt(promptInput(scan, "\nMake your menu selection now: ")); //prompt and parse input for switching
      try {
        switch(in) {
        case 8:

          break;
        case 7:

          break;
        case 6:

          break;
        case 5:

          break;
        case 4:

          break;
        case 3:

          break;
        case 2:
          rest.seatWaitingParty();
          break;
        case 1:
          while(true) {
          p_name = promptInput(scan, "Enter party name: ");
          if (rest.getWaitingCustomers().contains(p_name) || rest.getSeatedCustomers().contains(p_name))
            System.out.print("Party name already exists. Pick a different name.");
          else
            break;
          }

          p_size = Integer.parseInt(promptInput(scan, "Enter party size: "));

          petOk = (promptInput(scan, "Does your party have pets (Y/N)?").charAt(0) == 'Y');

          rest.addNewParty(new CustomerParty(p_name, p_size, petOk));

          break;
        case 0:
          System.out.println("Restaurant is closing. Goodbye.");
          break;
        default:
            System.out.println("Invalid selection.");
        }
      } catch (Exception e) {
          System.out.println("Something went very worng.");
      }
    } while (in != 0);
  }

  private static Restaurant initRestaurant(Scanner scan) {
    ListRA<Table> tables = new ListRA<Table>();
    int petFriendly = Integer.parseInt(promptInput(scan, "Enter your restaurant configuration:\nHow many tables does your pet-friendly section have?"));
    String tableName;
    int seatCount;
    for(int i = 0; i < petFriendly; i++) {
      tableName = promptInput(scan, "Enter table name: ");

      for(int j = 0; j < i; j++)
        if(tables.get(j).getName().equals(tableName)) {
          System.out.println("Name already in use, please use a different name.");
          i--;
          break;
        }

      seatCount = Integer.parseInt(promptInput(scan, "Enter number of seats: "));

      tables.add(i, new Table(tableName, seatCount));
    }

    int npetFriendly = Integer.parseInt(promptInput(scan, "How many tables does your non-pet-friendly section have? "));

    for(int i = 0; i < npetFriendly; i++) {
      tableName = promptInput(scan, "Enter table name: ");

      for(int j = 0; j < i; j++)
        if(tables.get(petFriendly+j).getName().equals(tableName)) {
          System.out.println("Name already in use, please use a different name.");
          i--;
          break;
        }

      seatCount = Integer.parseInt(promptInput(scan, "Enter number of seats: "));

      tables.add(petFriendly + i, new Table(tableName, seatCount));
    }

    Restaurant out = new Restaurant(tables, petFriendly);

    return out;
  }

  private static final String promptInput(Scanner scan, String prompt) { //used for slipstreaming the input process
    System.out.print(prompt);
    String out = scan.nextLine();
    System.out.println(out);
    return out;
  }
}
