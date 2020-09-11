import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

class ArrayDequeTest {

    @Test
    public static void testaddsizeempty(){
        ArrayDeque<String>dq = new ArrayDeque<>();
        assertEquals(true, dq.isEmpty());

        dq.addFirst("first");
        assertEquals(1, dq.size());

        dq.addLast("last");
        assertEquals(2, dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);

        String last = dq.removeLast();
        assertEquals("last", last);

        assertEquals(0, dq.size());
    }

    public static void main(String[] args) {
        testaddsizeempty();
    }
}