public class OffByN implements CharacterComparator {
    private int n;

    /** OffByN constructor. */
    OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
