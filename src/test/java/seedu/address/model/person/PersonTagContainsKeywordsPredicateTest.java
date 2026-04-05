package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTagContainsKeywordsPredicateTest {

    @Test
    public void constructor_nullKeywords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonTagContainsKeywordsPredicate(null));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("vip");
        List<String> secondPredicateKeywordList = Arrays.asList("vip", "lead");

        PersonTagContainsKeywordsPredicate firstPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonTagContainsKeywordsPredicate secondPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));

        PersonTagContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        PersonTagContainsKeywordsPredicate predicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("vip"));
        assertTrue(predicate.test(new PersonBuilder().withTags("vip", "lead").build()));

        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("partner", "lead"));
        assertTrue(predicate.test(new PersonBuilder().withTags("vip", "lead").build()));

        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("ViP", "LeAd"));
        assertTrue(predicate.test(new PersonBuilder().withTags("vip", "lead").build()));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("vip").build()));

        predicate = new PersonTagContainsKeywordsPredicate(Collections.singletonList("partner"));
        assertFalse(predicate.test(new PersonBuilder().withTags("vip", "lead").build()));

        predicate = new PersonTagContainsKeywordsPredicate(Collections.singletonList("vip"));
        assertFalse(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(keywords);

        String expected = PersonTagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
