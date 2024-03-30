package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.reminder.Days;
import seedu.address.model.reminder.ReminderOnOff;

public class ReminderSettings implements Serializable {
    private static final boolean DEFAULT_REMINDER = false;
    private static final long DEFAULT_NUMOFDAYS = 0;
    private boolean reminder;
    private long numOfDays;

    /**
     * Constructs a {@code ReminderSettings} with the default reminder setting and number of days.
     */
    public ReminderSettings() {
        reminder = DEFAULT_REMINDER;
        numOfDays = DEFAULT_NUMOFDAYS;
    }

    /**
     * Constructs a {@Code ReminderSettings} with the specified reminder setting, to on or off reminder,
     * and the number of days left till application end date.
     */
    public ReminderSettings(ReminderOnOff reminder, Days numOfDays) {
        this.reminder = reminder.getReminder();
        this.numOfDays = numOfDays.getNumOfDays();
    }

    public boolean getReminder() {
        return this.reminder;
    }

    public long getNumOfDays() {
        return this.numOfDays;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderSettings)) {
            return false;
        }

        ReminderSettings otherReminderSettings = (ReminderSettings) other;
        return reminder == otherReminderSettings.reminder
                && numOfDays == otherReminderSettings.numOfDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminder, numOfDays);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("reminderSettings", reminder)
                .add("numOfDays", numOfDays)
                .toString();
    }
}
