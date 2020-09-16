// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /**Constants. Do not change. In case you're curious, the keyword final means
     *the values cannot be changed at runtime. We'll discuss this
     * and other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing
     * one iteration of the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double dqValue = buffer.dequeue();
        buffer.enqueue(DECAY * (dqValue + buffer.peek()) * 0.5);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }

}
