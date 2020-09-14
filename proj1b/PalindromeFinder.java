/** This class outputs all palindromes in the words file in current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc1 = new OffByOne();
        CharacterComparator ccN = new OffByN(3);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, ccN)) {
                System.out.println(word);
            }
        }
    }
}
