package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the setting of a reminder, either on or off
 */
public class ReminderOnOff {
    public static final String MESSAGE_CONSTRAINTS =
            "Setting of reminder should only contain true or false, "
                    + "true to turn on reminder and false to turn off reminder.";

    public final boolean setReminderBoolean;

    /**
     * Constructs a {@code ReminderOnOff}
     *
     * @param setReminder A valid value
     */
    public ReminderOnOff(String setReminder) {
        requireNonNull(setReminder);
        checkArgument(isValidSetReminder(setReminder), MESSAGE_CONSTRAINTS);
        setReminderBoolean = setReminder.equals("true");
    }

    /**
     * Constructs a {@code ReminderOnOff} object with the reminder turned off by default
     */
    public ReminderOnOff() {
        setReminderBoolean = false;
    }

    /**
     * Returns true if the given string represents a valid reminder setting.
     *
     * @param test The string to test
     * @return True if the string is "true" or "false", case-insensitively
     */
    public static boolean isValidSetReminder(String test) {
        assert test != null;
        String caseInsensitive = test.toLowerCase();
        return caseInsensitive.equals("true") || caseInsensitive.equals("false");
    }

    /**
     * Returns the boolean value representing the reminder setting.
     *
     * @return True if the reminder is turned on, false otherwise.
     */
    public boolean getReminder() {
        return this.setReminderBoolean;
    }

    /**
     * Returns a string representation of the reminder setting.
     *
     * @return "Reminder is turned on" if the reminder is on, "Reminder is turned off" otherwise.
     */
    @Override
    public String toString() {
        return setReminderBoolean ? "Reminder is turned on" : "Reminder is turned off";
    }
}
