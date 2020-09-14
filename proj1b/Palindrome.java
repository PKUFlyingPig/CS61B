public class Palindrome {
    /** convert a word to dq. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        int len = word.length();
        for (int i = 0; i < len; i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    /** decide if the given word is palindrome. */
    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /** overloaded isPalindrome, decide if the given word is palindrome.
     * according to the given CharacterComparator
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
