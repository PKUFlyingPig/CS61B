/** This interface defines a method for determining equality of characters. */
public interface CharacterComparator {
    /** Returns true if characters are equal by rules of implementing class. */
    boolean equalChars(char x, char y);
}
