package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;


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
    public void test_keywordIsEmpty_throwsException() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("");

        Throwable th = assertThrows(IllegalArgumentException.class, () ->
                predicate.test(new CompanyBuilder().withName("Alice").build()));

        assertEquals("Word parameter cannot be empty", th.getMessage());
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // One keyword
        predicate = new NameContainsKeywordsPredicate("Bob");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate("Alice Bob");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));


        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate("aLIce bOB");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        NameContainsKeywordsPredicate predicate;
        // Not matching start string
        predicate = new NameContainsKeywordsPredicate("Bob Carol");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Carol").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Prof");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").withTags("Professor").build()));

        // Tags with spaces
        predicate = new NameContainsKeywordsPredicate("Engineer");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").withTags("Software Engineer").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate("Software Eng");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").withTags("Software Engineer").build()));


        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate("pROf");
        assertTrue(predicate.test(new CompanyBuilder().withName("Alice Bob").withTags("Professor").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {

        NameContainsKeywordsPredicate predicate;
        // Not matching start string
        predicate = new NameContainsKeywordsPredicate("Software Engineer");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Carol").withTags("Staff Engineer").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Engineer");
        assertFalse(predicate.test(new CompanyBuilder().withName("Alice Bob").withTags("Teacher", "Doctor").build()));

        // Keywords match phone, email and address, but does not match tag
        predicate = new NameContainsKeywordsPredicate("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new CompanyBuilder().withPhone("12345")
                .withEmail("alice@email.com").withTags("Doctor").build()));
    }


    @Test
    public void toStringMethod() {
        String keywords = "keyword 1 keyword 2";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
