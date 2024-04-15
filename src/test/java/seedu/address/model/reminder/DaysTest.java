package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.reminder.Days.isValidDays;

import org.junit.jupiter.api.Test;

class DaysTest {

    @Test
    public void nullDays_throwsException() {
        assertThrows(NullPointerException.class, () -> new Days(null));
    }
    @Test
    void test_getNumOfDays() {
        Days d = new Days("26");
        Days emptyDays = new Days();
        assertEquals(d.getNumOfDays(), 26);
        assertEquals(emptyDays.getNumOfDays(), 0);
    }
    @Test
    void test_isValidDays() {
        assertTrue(isValidDays("2")); // one digit is allowed
        assertTrue(isValidDays("26")); // two digits allowed
        assertTrue(isValidDays("0")); // zero is allowed since it is a single digit

        assertFalse(isValidDays("-1")); // negative number of days not allowed
        assertFalse(isValidDays("3.5")); // floating points not allowed
        assertFalse(isValidDays("Test")); // Strings are not valid
    }
    @Test
    void testToString() {
        Days d = new Days("26");
        Days defaultDays = new Days();
        assertEquals(d.toString(), "26");
        assertEquals(defaultDays.toString(), "0");
    }
}
