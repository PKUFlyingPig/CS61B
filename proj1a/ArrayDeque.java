/** second part of project1A.
 * deque implemented by array
 * @author FlyingPig
 */
public class ArrayDeque<T> {

    /** array to save data.*/
    private T[] array;
    /** size of the deque. */
    private int size;

    /** size of the array. */
    private int length;

    /** front index. */
    private int front;

    /** last index. */
    private int last;

    /** constructor for ArrayDeque. */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    /** decide if the deque is empty.
     * @return true if the deque is empty, vice versa.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the size of the deque. */
    public int size() {
        return size;
    }

    /** return the "index - 1".
     * @param index index
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /** return the "index + 1".
     * @param index index
     */
    private int plusOne(int index) {
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }

    /** add one item at the front of the deque.
     * @param item the item we want to add
     */
    public void addFirst(T item) {
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    /** add one item at the end of the deque.
     * @param item item we want to add
     */
    public void addLast(T item) {
        array[last] = item;
        last = plusOne(last);
        size++;
    }

    /** remove the first item.
     * @return the removed first item
    */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = array[front];
        front = plusOne(front);
        size--;
        return ret;
    }

    /** remove the last item.
     * @return the removed last item
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }

    /** return the item indexed at index.
     * @param index index
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr);
        }
        return array[ptr];
    }

    /** print the entire deque from front to end. */
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr);
        }
    }

}
