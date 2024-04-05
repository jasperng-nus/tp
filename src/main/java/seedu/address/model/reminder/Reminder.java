package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a reminder configuration for application deadlines.
 * A reminder consists of the number of days before the deadline to trigger the reminder,
 * and whether the reminder is turned on or off.
 */
public class Reminder {
    private final Days days;
    private final ReminderOnOff reminderOnOff;

    /**
     * Constructs a Reminder with the specified number of days and reminder status.
     *
     * @param days The number of days before the deadline to trigger the reminder
     * @param reminderOnOff The status of the reminder (on or off)
     */
    public Reminder(Days days, ReminderOnOff reminderOnOff) {
        requireAllNonNull(days, reminderOnOff);
        this.days = days;
        this.reminderOnOff = reminderOnOff;
    }

    /**
     * Gets the number of days before the deadline to trigger the reminder.
     *
     * @return The number of days.
     */
    public Days getDays() {
        assert days != null;
        return this.days;
    }

    /**
     * Gets the status of the reminder (on or off).
     *
     * @return The reminder status
     */
    public ReminderOnOff getReminderOnOff() {
        assert reminderOnOff != null;
        return reminderOnOff;
    }
}
