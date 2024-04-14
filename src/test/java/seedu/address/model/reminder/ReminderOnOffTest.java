package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReminderOnOffTest {

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderOnOff(null));
    }

    @Test
    void constructor_emptySetReminder_throwsIllegalArgumentException() {
        String emptySetReminder = "";
        assertThrows(IllegalArgumentException.class, () -> new ReminderOnOff(emptySetReminder));
    }

    @Test
    void constructor_invalidSetReminder_throwsIllegalArgumentException() {
        String invalidSetReminder = "truee";
        assertThrows(IllegalArgumentException.class, () -> new ReminderOnOff(invalidSetReminder));
    }

    @Test
    void constructor_defaultReminder() {
        // default reminder is false
        ReminderOnOff defaultReminder = new ReminderOnOff();
        assertFalse(defaultReminder.getReminder());
    }

    @Test
    void isValidSetReminder() {
        // invalid set reminder
        assertFalse(ReminderOnOff.isValidSetReminder("")); // empty string
        assertFalse(ReminderOnOff.isValidSetReminder(" ")); // spaces only
        assertFalse(ReminderOnOff.isValidSetReminder("truee")); // incorrect spelling
        assertFalse(ReminderOnOff.isValidSetReminder("falsee")); // incorrect spelling
        assertFalse(ReminderOnOff.isValidSetReminder("phone")); // non-boolean
        assertFalse(ReminderOnOff.isValidSetReminder("1")); // non-boolean

        // valid set reminder
        assertTrue(ReminderOnOff.isValidSetReminder("true")); // true
        assertTrue(ReminderOnOff.isValidSetReminder("false")); // false
    }

    @Test
    void getReminder() {
        // default reminder is false
        ReminderOnOff reminderOnOff = new ReminderOnOff();
        assertFalse(reminderOnOff.getReminder());

        // set reminder to true
        reminderOnOff = new ReminderOnOff("true");
        assertTrue(reminderOnOff.getReminder());

        // set reminder to false
        reminderOnOff = new ReminderOnOff("false");
        assertFalse(reminderOnOff.getReminder());
    }

    @Test
    void testToString() {
        // set reminder to true
        ReminderOnOff reminderOnOff = new ReminderOnOff("true");
        assertEquals("Reminder is turned on", reminderOnOff.toString());

        // set reminder to false
        reminderOnOff = new ReminderOnOff("false");
        assertEquals("Reminder is turned off", reminderOnOff.toString());
    }
}
