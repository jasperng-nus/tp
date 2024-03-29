package seedu.address.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} or {@code Tag} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Company> {
    private final String keyword;

    public NameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Company company) {
        System.out.println(keyword);
        return ((StringUtil.containsStartSubstringIgnoreCase(company.getName().fullName, keyword))
                || company.getTags().stream().anyMatch(
                        tag -> StringUtil.containsStartSubstringIgnoreCase(tag.getTagName(), keyword))
                        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
