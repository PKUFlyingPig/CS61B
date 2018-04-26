import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBinaryTrie {
/* Uncomment when you are ready. 
    @Test
    public void bestPrefixMatchWithExactMatches() {
        System.out.println("Testing that bestPrefixMatch works with exact matches.");
        Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
        frequencyTable.put('a', 1);
        frequencyTable.put('b', 2);
        frequencyTable.put('c', 4);
        frequencyTable.put('d', 5);
        frequencyTable.put('e', 6);
        BinaryTrie trie = new BinaryTrie(frequencyTable);

        BitSequence shouldBeA = new BitSequence("000");
        Match m = trie.longestPrefixMatch(shouldBeA);
        assertEquals((char) 'a', (char) m.getSymbol()); 
        assertEquals(shouldBeA, m.getSequence());

        BitSequence shouldBeE = new BitSequence("11");
        m = trie.longestPrefixMatch(shouldBeE);
        assertEquals((char) 'e', (char) m.getSymbol()); 
        assertEquals(shouldBeE, m.getSequence());
    }

    @Test
    public void bestPrefixMatchWithLongerBitSequences() {
        System.out.println("Testing that bestPrefixMatch works with long string matches.");        
        Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
        frequencyTable.put('a', 1);
        frequencyTable.put('b', 2);
        frequencyTable.put('c', 4);
        frequencyTable.put('d', 5);
        frequencyTable.put('e', 6);
        BinaryTrie trie = new BinaryTrie(frequencyTable);

        BitSequence shouldBeA = new BitSequence("0001010001");
        Match m = trie.longestPrefixMatch(shouldBeA);
        assertEquals((char) 'a', (char) m.getSymbol()); 
        BitSequence justA = new BitSequence("000");
        assertEquals(justA, m.getSequence());

        BitSequence shouldBeE = new BitSequence("11000110001");
        m = trie.longestPrefixMatch(shouldBeE);
        assertEquals((char) 'e', (char) m.getSymbol()); 
        BitSequence justE = new BitSequence("11");
        assertEquals(justE, m.getSequence());

        BitSequence shouldBeAE = new BitSequence("00011");
        m = trie.longestPrefixMatch(shouldBeA);
        assertEquals((char) 'a', (char) m.getSymbol()); 
        assertEquals(justA, m.getSequence());
        
        BitSequence remainingBits = shouldBeAE.allButFirstNBits(m.getSequence().length());
        m = trie.longestPrefixMatch(remainingBits);
        assertEquals((char) 'e', (char) m.getSymbol());
        assertEquals(justE, m.getSequence());
    }

    @Test
    public void testYourLookupTable() {   
        System.out.println("Testing that your code outputs the right lookup table."); 
        Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
        frequencyTable.put('a', 1);
        frequencyTable.put('b', 2);
        frequencyTable.put('c', 4);
        frequencyTable.put('d', 5);
        frequencyTable.put('e', 6);        
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        Map<Character, BitSequence> yourTable = trie.buildLookupTable();

        HashMap<Character, BitSequence> expected = new HashMap<Character, BitSequence>();
        expected.put('a', new BitSequence("000"));
        expected.put('b', new BitSequence("001"));
        expected.put('c', new BitSequence("01"));
        expected.put('d', new BitSequence("10"));
        expected.put('e', new BitSequence("11"));
        assertEquals(expected, yourTable);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestBinaryTrie.class);
    }    
    */
} 
