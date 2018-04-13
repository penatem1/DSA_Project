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

          break;
        case 1:

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

  private static final String promptInput(Scanner scan, String prompt) { //used for slipstreaming the input process
    System.out.print(prompt);
    String out = scan.nextLine();
    System.out.println(out);
    return out;
  }
}
