/*Purpose: Data Structure and Algorithms Lab 5 Problem 3
* Status: Complete and thoroughly tested
* Last update: 02/18/18
* Submitted:  02/22/18
* Comment: updated to be generic
* @author: Marcus Penate
* @version: 2018.02.01
*/
public interface ListInterface<T>
{
    boolean isEmpty();
    int size();
    void add(int index, T item)
    throws ListIndexOutOfBoundsException;
    T get(int index)
    throws ListIndexOutOfBoundsException;
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
}  // end ListInterface
