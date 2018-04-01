public class Queue<T> implements QueueInterface<T> {
	protected int numItems, back, front; //book keeping variables
	protected T[] items; //the actual items
	
	public Queue() {
		back = front = numItems = 0; //defaults
		items = (T[]) new Object[3]; //default 3 cell array
	}
	
	public boolean isEmpty() { return numItems==0; }
	
	protected void resize() {
		T[] newItems = (T[]) new Object[3*items.length/2+1]; //make new array of 1.5 times +1 space
		
		for(int i = 0; i < numItems; i++) //loop over current array
			newItems[i] = items[(i+front)%items.length]; //set current array to new array, use front as offset to i, use modulo for circular array access
		
		front = 0; //set front back to the beginning
		back = numItems; //set back to the current number of items
		items = newItems; //overwrite items
	}
	
	public void enqueue(T newItem) throws QueueException {
		if (numItems == items.length) //need to resize?
			resize(); //do it!
		
		items[back] = newItem; //set backs current value to the new object reference
		back = (back+1)%items.length; //circularly adjust back
		numItems++; //keep track of item count
	}
	
	public T dequeue() throws QueueException {
		if (numItems == 0) //no items to dequeue, error out
			throw new QueueException("QueueException on dequeue. Stack is empty.");
		
		T out = items[front]; //get current item there for output later
		
		items[front] = null; //prevent memory leaks
		front = (front+1)%items.length; //circularly increment front
		numItems--; //keep track of number of items
		
		return out; //return previously stored reference
	}
	
	public void dequeueAll() {
		items = (T[]) new Object[3]; //set back to default
		back = front = numItems = 0; //set back to defaults
	}
	
	public T peek() throws QueueException {
		if (numItems == 0) //no items, we cant peek, throw exception
			throw new QueueException("QueueException on peek. Stack is empty.");
	
		return items[front]; //peek front item == return front item
	}
	
	public String toString() {
		String out = ""; //start with empty string
		for(int i = 0; i < numItems; i++) //loop over all items
			out += items[(i+front)%items.length].toString() + " "; //to string + spaces
		return out;	//return that string
	}
}