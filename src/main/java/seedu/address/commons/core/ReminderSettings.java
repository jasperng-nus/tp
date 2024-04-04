package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.reminder.Days;
import seedu.address.model.reminder.ReminderOnOff;

/**
 * Represents the settings for reminders about application deadlines.
 */
public class ReminderSettings implements Serializable {
    private static final boolean DEFAULT_REMINDER = false;
    private static final long DEFAULT_NUMOFDAYS = 0;
    private boolean isReminderOn;
    private long numOfDays;

    /**
     * Constructs a {@code ReminderSettings} with the default reminder setting and number of days.
     */
    public ReminderSettings() {
        isReminderOn = DEFAULT_REMINDER;
        numOfDays = DEFAULT_NUMOFDAYS;
    }

    /**
     * Constructs a {@Code ReminderSettings} with the specified reminder setting, to on or off reminder,
     * and the number of days left till application end date.
     */
    public ReminderSettings(ReminderOnOff isReminderOn, Days numOfDays) {
        this.isReminderOn = isReminderOn.getReminder();
        this.numOfDays = numOfDays.getNumOfDays();
    }

    /**
     * Returns the reminder setting.
     *
     * @return True if the reminder is turned on, false otherwise.
     */
    public boolean getReminderOn() {
        return this.isReminderOn;
    }

    /**
     * Returns the number of days left till application end date.
     *
     * @return The number of days left.
     */
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
        return isReminderOn == otherReminderSettings.isReminderOn
                && numOfDays == otherReminderSettings.numOfDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isReminderOn, numOfDays);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("reminderSettings", isReminderOn)
                .add("numOfDays", numOfDays)
                .toString();
    }
}
