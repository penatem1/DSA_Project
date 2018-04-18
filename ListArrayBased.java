/*Purpose: Data Structure and Algorithms Lab 5 Problem 3
* Status: Complete and thoroughly tested
* Last update: 02/18/18
* Submitted:  02/22/18
* Comment: updated to be generic
* @author: Marcus Penate
* @version: 2018.02.01
*/

public class ListArrayBased<T> implements ListInterface<T>
{

    private static final int MAX_LIST = 3;
    protected T []items;  // an array of list items
    protected int numItems;  // number of items in list

    public ListArrayBased()
    {
        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    }  // end default constructor
/**
*Method for seeing if the collection is empty
*@return returns true if the collection is empty and false otherwise
*/
    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty
/**
*Method for getting the size of the collection
*@return returns integer that is the size of the collection
*/
    public int size()
    {
        return numItems;
    }  // end size
/**
*Method for removing all items in the collection
*/
    public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (T[]) new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll
/**
*Method for adding an item in a specific index of the collection
*@param index is an integer that represents where in the collection to place the item
*@param item is a reference to the object to be placed in the collection
*/
    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length) //fixes implementation errors //fixes programming style
        {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add
/**
*Method for getting an item in a specified index of the collection
*@param takes an int that is the index of the collection to be returned
*@return returns the item that is in the specified index
*/
    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get
/**
*Method for removing an item in an index from the collection
*@param int that represents the index to be removed
*/
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos-1] = items[pos];
            }  // end for
            items[--numItems] = null; //fixes memory leak
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove
}
