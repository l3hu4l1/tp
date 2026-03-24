package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} can be parsed as an integer.
     *
     */
    public static boolean isValidInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the length of the longest contiguous substring common to both strings a and b.
     *
     * @param a the first string to compare.
     * @param b the second string to compare.
     * @return the length of the longest contiguous substring common to both strings.
     */
    public static int longestContiguousMatch(String a, String b) {
        String aLower = a.toLowerCase();
        String bLower = b.toLowerCase();

        int[] prev = new int[bLower.length() + 1];
        int[] curr = new int[bLower.length() + 1];
        int best = 0;

        for (int i = 1; i <= aLower.length(); i++) {
            for (int j = 1; j <= bLower.length(); j++) {
                if (aLower.charAt(i - 1) == bLower.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                    best = Math.max(best, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            // Swap and clear for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;
            Arrays.fill(curr, 0);
        }

        return best;
    }
}
