package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Company's application date in the intern book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format YYYY-MM-DD, and must be valid.";
    private static final LocalDate DEFAULT_DATE = LocalDate.parse("0000-01-01");
    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs an empty {@code Date}
     */
    public Date() {
        this.date = DEFAULT_DATE;
    }

    /**
     * Returns true if given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Compares this date with another date.
     * @return 0 if dates are equal.
     *     Positive value if this date is later than otherDate.
     *     Negative value if this date is earlier than otherDate.
     */
    public int compareTo(Date otherDate) {
        return this.date.compareTo(otherDate.date);
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    public LocalDate getDate() {
        return isDatePresent() ? date : LocalDate.MAX;
    }

    public final boolean isDatePresent() {
        return !date.equals(DEFAULT_DATE);
    }
}
