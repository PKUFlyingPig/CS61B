// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T retValue = rb[first];
        fillCount--;
        first = (first + 1) % capacity;
        return retValue;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        private int curNum;
        public ArrayRingBufferIterator() {
            pos = first;
            curNum = 0;
        }

        public boolean hasNext() {
            return curNum < fillCount;
        }

        public T next() {
            T retValue = rb[pos];
            pos = (pos + 1) % capacity;
            curNum++;
            return retValue;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
