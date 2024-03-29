package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Reminder {
    private final Days days;
    private final ReminderOnOff reminderOnOff;

    public Reminder(Days days, ReminderOnOff reminderOnOff) {
        requireAllNonNull(days, reminderOnOff);
        this.days = days;
        this.reminderOnOff = reminderOnOff;
    }

    public Days getDays() {
        return this.days;
    }

    public ReminderOnOff getReminderOnOff() {
        return reminderOnOff;
    }
}
