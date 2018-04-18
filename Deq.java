public class Deq<T> extends Queue<T> implements ExtendedQueueInterface<T> {

    public Deq() {
        super(); //use super constructor
    }

    public void enqueueFirst(T newItem) throws ExtendedQueueException {
        if (numItems == items.length) //need to resize?
            resize(); //do it!

        front = (front-1+items.length)%items.length; //circularly decrement front, adding items.length handles 0 case
        items[front] = newItem; //put new item in front
        numItems++; //keep track of count
    }

    public T dequeueLast() throws ExtendedQueueException {
        if (numItems == 0) //no items to remove, throw an error
            throw new ExtendedQueueException("ExtendedQueueException on dequeueLast. Stack is empty.");

        back = (back-1+items.length)%items.length; //circularly decrement back, adding items.length handles 0 case
        T out = items[back]; //for returning later
        items[back] = null; //for preventing memory leaks

        numItems--; //for keeping track of count

        return out; //returnung previously stored reference
    }

    public T peekLast() throws ExtendedQueueException {
        if (numItems == 0) //nothing to peek? throw an error
            throw new ExtendedQueueException("ExtendedQueueException on peekLast. Stack is empty.");

        return items[(back-1+items.length)%items.length]; //circularly look backwards and output that reference
    }

}