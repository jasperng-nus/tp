package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }
    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "2103-1-1";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }
    @Test
    void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("23-12-11")); // invalid Year
        assertFalse(Date.isValidDate("2023-2-1")); // no leading 0
        assertFalse(Date.isValidDate("2023-13-01")); // invalid month
        assertFalse(Date.isValidDate("2023-12-32")); // invalid day
        assertFalse(Date.isValidDate("phone")); // non-numeric

        // valid phone numbers
        assertTrue(Date.isValidDate("2023-12-31"));
    }

    @Test
    void testToString() {
        Date date = new Date("2024-11-11");
        assertTrue(date.toString().equals("2024-11-11"));
    }
}
