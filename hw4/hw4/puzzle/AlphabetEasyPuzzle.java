package hw4.puzzle;

import java.util.Set;
import java.util.HashSet;

/** Ultra simple puzzle useful for debugging. The puzzle has a state equal to a single
  * alphabetical lower case character. The goal is 'z'. Neighbors are the characters
  * immediately to the left and right of the character in the alphabet. */
public class AlphabetEasyPuzzle implements WorldState {
    char character;

    /**
     * Creates a new AlphabetEasyPuzzle.
     */
    public AlphabetEasyPuzzle(char c) {
        character = c;
    }

    /** The neighbors of the WorldState are the characters before and after
      * character. So if character == 'f', the neighbors will be 'e' and 'g'. */
    @Override
    public Iterable<WorldState> neighbors() {
        Set<WorldState> neighbs = new HashSet<>();
        if (character - 1 >= 'a') {
            neighbs.add(new AlphabetEasyPuzzle((char) (character - 1)));
        }
        if (character + 1 <= 'z') {
            neighbs.add(new AlphabetEasyPuzzle((char) (character + 1)));
        }
        return neighbs;
    }

    /** The goal is 'z', so distance will just be 'z' - character. */
    @Override
    public int estimatedDistanceToGoal() {
        return 'z' - character;
    }

    @Override
    public String toString() {
        return Character.toString(character);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlphabetEasyPuzzle word1 = (AlphabetEasyPuzzle) o;
        return word1.character == character;
    }

    @Override
    public int hashCode() {
        return character;
    }
}
