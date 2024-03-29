package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ReminderOnOff {
    public static final String MESSAGE_CONSTRAINTS =
            "Setting of reminder should only contain true or false, " +
                    "true to turn on reminder and false to turn off reminder.";

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

    public ReminderOnOff() {
        setReminderBoolean = false;
    }

    public static boolean isValidSetReminder(String test) {
        String caseInsensitive = test.toLowerCase();
        return caseInsensitive.equals("true") || caseInsensitive.equals("false");
    }

    public boolean getReminder() {
        return this.setReminderBoolean;
    }

    @Override
    public String toString() {
        return setReminderBoolean ? "Reminder is turned on" : "Reminder is turned off";
    }
}
