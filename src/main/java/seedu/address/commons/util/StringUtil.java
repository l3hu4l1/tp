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
     * Returns the length of the longest contiguous substring common to both strings,
     * ignoring case differences.
     *
     * @param a The first string to compare.
     * @param b The second string to compare.
     * @return The length of the longest common contiguous substring.
     */
    public static int longestContiguousMatch(String a, String b) {
        String normalizedA = a.toLowerCase();
        String normalizedB = b.toLowerCase();

        int lengthA = normalizedA.length();
        int lengthB = normalizedB.length();

        // Two rows is enough because each cell depends only on the row above,
        // keeping memory proportional to input length rather than input area.
        int[] previousRow = new int[lengthB + 1];
        int[] currentRow = new int[lengthB + 1];

        int longestMatch = 0;

        for (int i = 1; i <= lengthA; i++) {
            for (int j = 1; j <= lengthB; j++) {
                boolean charsMatch = normalizedA.charAt(i - 1) == normalizedB.charAt(j - 1);
                currentRow[j] = charsMatch ? previousRow[j - 1] + 1 : 0;
                longestMatch = Math.max(longestMatch, currentRow[j]);
            }

            // Reuse the older row's array for the next iteration rather than allocating a new one
            int[] temp = previousRow;
            previousRow = currentRow;
            currentRow = temp;
            Arrays.fill(currentRow, 0);
        }

        return longestMatch;
    }

}
