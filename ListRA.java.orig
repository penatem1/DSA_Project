/*Purpose: Data Structure and Algorithms Lab 5 Problem 3
* Status: Complete and thoroughly tested
* Last update: 02/18/18
* Submitted:  02/22/18
* Comment: updated to be generic
* @author: Marcus Penate
* @version: 2018.02.01
*/

public class ListRA<T> extends ListArrayBased<T> {
    public void add(int index, T item)
    throws ListIndexOutOfBoundsException
    {
        if (index < 0 || index > items.length) //check for the correct index range
            throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on add");

        try {
            super.add(index, item);    //try to have the super class add it itself
        }
        catch (ListException e) {
            resizeAndAdd(index, item);    //if we got a range mismatch from the super class, resize and add it ourselves. (MORE EFFICIENT)
        }
    }

    public void reverse() {
        for(int i = 0; i < numItems/2; i++) { //loop through halfway
            T temp = items[i]; //temp for when array[i] is overwritten
            items[i] = items[numItems-i-1]; //set opposite of the middle index to current index
            items[numItems-i-1] = temp; //set opposite of middle to temporary
        }
    }

    public String toString() {
        String out = "";
        for(int i = numItems-1; i >= 0; i--) //loop through collection, use toString() to get into string format for output
            out += items[i].toString() + " ";
        return out;
    }

    private void resizeAndAdd(int index, T item) {
        T[] temp = (T[]) new Object[3*items.length/2 + 1]; //make an array with 5 more space, not too many not too much (20 bytes)
        for(int i = 0; i < index; i++) //loop until we insert the new object
            temp[i] = items[i]; //copy over elements
        temp[index] = item; //insert new object
        for(int i = index+1; i < numItems+1; i++) //loop until we are out of objects
            temp[i] = items[i-1]; //copy over elements
        items = temp; //overwrite super collection with new resized collection
		numItems++;
    }
}
