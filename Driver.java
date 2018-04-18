/*
* Purpose: Data Structure and Algorithms Project
* Status: Complete and thoroughly tested
* Last update: 04/18/18
* Submitted:  04/19/18
* Comment: test suite and sample run attached
* @author: Marcus Penate and Marc Gregory-Dixon
* @version: 2018.02.01
*/

import java.util.Scanner;

public class Driver {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in); //scanner for input

        Restaurant rest = initRestaurant(scan); //make initial tables in restaurant

        //variables for use of input
        String p_name, t_name, petVal, name="";
        boolean petOk;
        int in, seatNum, p_size;
        //output the menu
        System.out.println("\nSelect from the following menu: \n0. Close the restaurant.\n1. Customer party enters the restaurant.\n2. Customer party is seated and served.\n3. Customer party leaves the restaurant.\n4. Add a table.\n5. Remove a table.\n6  Display available tables.\n7. Display info about waiting customer parties.\n8. Display info about customer parties being served.");
        do {
            //get menu selection
            in = Integer.parseInt(promptInput(scan, "\nMake your menu selection now: ")); //prompt and parse input for switching
            try {
                switch(in) {
                case 8:
                    System.out.println(rest.getSeatedCustomers()); //output seated customers
                    break;
                case 7:
                    System.out.println(rest.getWaitingCustomers()); //output waiting customers
                    break;
                case 6:
                    rest.getAvailableTables(); //outputs available tables
                    break;
                case 5:
                    //prompt and check for pet friendliness
                    petOk = (promptInput(scan, "From which section would you like to remove this table?(P/N):").charAt(0) == 'P');

                    //get table name and check for its existance
                    t_name = promptInput(scan, "Enter table name: ");
                    if(!rest.getTableNames(petOk).contains(t_name))
                        System.out.println("Table " + t_name + " was not found, try again.");

                    //the removal will return true or false so we know what happened in the restaurant
                    boolean success = rest.rmTable(t_name, petOk);
                    if(success)
                        System.out.println("Removed table " + t_name + ".");
                    else
                        System.out.println("Could not remove table " + t_name + ".");
                    break;
                case 4:
                    System.out.println("You are now adding a table.");
                    petVal = promptInput(scan,"To which section would you like to add this tabe?(P/N): ");
                    boolean petOK = false;
                    boolean difName = false;
                    if (petVal.equalsIgnoreCase("P")) //checks for whether the pet friendly section will be accessed or not
                    {
                        petOK = true;
                    }
                    while(!difName) //keep looping until a unique name is found
                    {
                        name = promptInput(scan, "Enter table name: ");

                        if(!rest.getTableNames(petOK).contains(name)) //if the name does not already exists
                        {
                            difName = true; // we can stop looping, name is found
                        }
                        else
                        {
                            System.out.print("This table already exists in this section!"); //tell them to do it again
                        }

                    }
                    seatNum = Integer.parseInt(promptInput(scan, "Enter number of seats: ")); //get seat count
                    Table newTable = new Table(name, seatNum); //make the table
                    rest.addTable(newTable, petOK); //add the table

                    break;
                case 3:
                    if(rest.getSeatedCustomers().equals("No parties seated currently.")) { //check for no parties
                        System.out.println("No parties seated currently."); //say no parties
                        break;
                    }
                    rest.finishServing(promptInput(scan, "Enter the name of the customer that wants to leave: ")); //prompt for name and finish serving
                    break;
                case 2:
                    rest.seatWaitingParty(); //just seat a party
                    break;
                case 1:
                    while(true) { //keep looping until valid name
                        p_name = promptInput(scan, "Enter party name: "); //get name
                        if (rest.getWaitingCustomers().contains(p_name) || rest.getSeatedCustomers().contains(p_name)) //f the name exists at all in the restaurant
                            System.out.print("Party name already exists. Pick a different name."); //tell them to pick again
                        else
                            break; //stop looping
                    }

                    p_size = Integer.parseInt(promptInput(scan, "Enter party size: ")); //get party size

                    petOk = (promptInput(scan, "Does your party have pets (Y/N)?").charAt(0) == 'Y'); //check pet friendliness

                    rest.addNewParty(new CustomerParty(p_name, p_size, petOk)); //add the new party to the line

                    break;
                case 0:
                    System.out.println("Restaurant is closing. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid selection.");
                }
            } catch (Exception e) {
                System.out.println("Something went very wrong."); //hopefully this never happens
            }
        } while (in != 0); //keep the menu going until a 0 is entered
    }

    private static Restaurant initRestaurant(Scanner scan) {
        ListRA<Table> tables = new ListRA<Table>(); //make new list oftables
        int petFriendly = Integer.parseInt(promptInput(scan, "Enter your restaurant configuration:\nHow many tables does your pet-friendly section have?")); //get number of pet tables
        String tableName; //used for input
        int seatCount; //used for input
        for(int i = 0; i < petFriendly; i++) { //loop for every table to make
            tableName = promptInput(scan, "Enter table name: "); //get name

            boolean foundMatch = false; //for checking for uniqueness
            for(int j = 0; j < i; j++) //loop over already created tables
                if(tables.get(j).getName().equals(tableName)) { //if the names are the same
                    System.out.println("Name already in use, please use a different name."); //tell the user that
                    i--; //decrement to make the loop run on the same iteration
                    foundMatch = true; //for making the loop skip over asking for seat count and adding the table
                    break;
                }

            if(foundMatch)
                continue; //dont do the next operations if the name isnt valid

            seatCount = Integer.parseInt(promptInput(scan, "Enter number of seats: ")); //get seat count

            int pos = -1; //-1 means end of list
            for(int j = 0; j < i; j++) { //loop over already created tables
                if(tables.get(j).getSeatCount() < seatCount) //if the table doesnt belong here
                    continue; //keep going
                else { //if it does save this position and stop looping
                    pos = j;
                    break;
                }
            }
            if(pos==-1) //is no position was found
                pos = i; //it goes at the end of the current tables
            tables.add(pos, new Table(tableName, seatCount)); //add the table in ascending order
        }
        //ALL CODE BELOW IS AS ABOVE EXCEPT FOR NON PET FRIENDLY, SAME EXACT OPERATIONS, EXECPT NOW POSITION IN TABLE LISTRA IS OFFSET BY THE NUMBER OF TABLES THAT ALREADY EXIST (petFriendly)
        int npetFriendly = Integer.parseInt(promptInput(scan, "How many tables does your non-pet-friendly section have? "));

        for(int i = 0; i < npetFriendly; i++) {
            tableName = promptInput(scan, "Enter table name: ");

            boolean foundMatch = false;
            for(int j = 0; j < i; j++)
                if(tables.get(petFriendly+j).getName().equals(tableName)) {
                    System.out.println("Name already in use, please use a different name.");
                    i--;
                    foundMatch = true;
                    break;
                }

            if(foundMatch)
                continue;

            seatCount = Integer.parseInt(promptInput(scan, "Enter number of seats: "));

            int pos = -1;
            for(int j = 0; j < i; j++) {
                if(tables.get(petFriendly + j).getSeatCount() < seatCount)
                    continue;
                else {
                    pos = j;
                    break;
                }
            }
            if(pos==-1)
                pos = i;

            tables.add(petFriendly + pos, new Table(tableName, seatCount));
        }

        Restaurant out = new Restaurant(tables, petFriendly); //make the new restaurant with the tables created, petFriendly indicated when in the array the non pet friendly tables start

        return out;
    }

    private static final String promptInput(Scanner scan, String prompt) { //used for slipstreaming the input process, prompts and outputs input and returns input
        System.out.print(prompt);
        String out = scan.nextLine().trim();
        System.out.println(out);
        return out;
    }
}
