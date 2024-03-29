package seedu.address.model.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "first second";

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different company -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate("Alice Bob");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Not matching start string
        predicate = new NameContainsKeywordsPredicate("Bob Carol");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate("aLIce bOB");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("");
        NameContainsKeywordsPredicate finalPredicate = predicate;
        Throwable th = assertThrows(IllegalArgumentException.class, () ->
                finalPredicate.test(new CompanyBuilder().withName("Alice").build()));

        assertEquals("Word parameter cannot be empty", th.getMessage());
        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        String keywords = "keyword 1 keyword 2";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
