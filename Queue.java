public class Queue<T> implements QueueInterface<T> {
    protected int numItems, back, front; //book keeping variables
    protected T[] items; //the actual items
    
    public Queue() {
        back = front = numItems = 0; //defaults
        items = (T[]) new Object[3]; //default 3 cell array
    }

/**
*Method for returning if there are any elements in the queue
*@return returns true if the queue is empty and false otherwise
*/
    public boolean isEmpty() {
        return numItems==0;
    }
/**
*Method for resizing the queue if necessary
*/
    protected void resize() {
        T[] newItems = (T[]) new Object[3*items.length/2+1]; //make new array of 1.5 times +1 space

        for(int i = 0; i < numItems; i++) //loop over current array
            newItems[i] = items[(i+front)%items.length]; //set current array to new array, use front as offset to i, use modulo for circular array access

        front = 0; //set front back to the beginning
        back = numItems; //set back to the current number of items
        items = newItems; //overwrite items
    }
/**
* Method for adding an item to the end of the queue
* @param newItem is the item to be added to the end of queue
*/
    public void enqueue(T newItem) throws QueueException {
        if (numItems == items.length) //need to resize?
            resize(); //do it!

        items[back] = newItem; //set backs current value to the new object reference
        back = (back+1)%items.length; //circularly adjust back
        numItems++; //keep track of item count
    }
/**
* Removes item from the beginning of the queue
*@return returns item that is being removed from the queue 
*/
    public T dequeue() throws QueueException {
        if (numItems == 0) //no items to dequeue, error out
            throw new QueueException("QueueException on dequeue. Stack is empty.");

        T out = items[front]; //get current item there for output later

        items[front] = null; //prevent memory leaks
        front = (front+1)%items.length; //circularly increment front
        numItems--; //keep track of number of items

        return out; //return previously stored reference
    }
/**
* Method that removes all items from the queue
*/
    public void dequeueAll() {
        items = (T[]) new Object[3]; //set back to default
        back = front = numItems = 0; //set back to defaults
    }
/**
*Method for seeing the very first item in the queue
*@return returns the first item in the queue
*/
    public T peek() throws QueueException {
        if (numItems == 0) //no items, we cant peek, throw exception
            throw new QueueException("QueueException on peek. Stack is empty.");

        return items[front]; //peek front item == return front item
    }
/**
*Method for getting the string representation of all items in the queue
*@return returns a string that displays the content of the queue
*/
    public String toString(String delim) {
        String out = ""; //start with empty string
        for(int i = 0; i < numItems; i++) //loop over all items
            out += items[(i+front)%items.length].toString() + delim; //to string + spaces
        return out;	//return that string
    }
}
