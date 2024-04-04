package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETREMINDER;

import seedu.address.commons.core.ReminderSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Represents a command for setting reminders to the application.
 * Usage: setReminder -r numOfDays/off
 * Example: setReminder -r 7
 */
public class SetReminderCommand extends Command {
    public static final String COMMAND_WORD = "setReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets reminders to the applications"
            + "\nParameters: "
            + PREFIX_SETREMINDER + " off or number of days from the end date of internship application "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_SETREMINDER + " 10 ";

    public static final String MESSAGE_SUCCESS = "Reminder settings changed";
    private final Reminder toUpdateReminder;

    /**
     * Creates a SetReminderCommand to update the reminder settings
     * @param reminder The reminder settings to be updated.
     */
    public SetReminderCommand(Reminder reminder) {
        requireNonNull(reminder);
        toUpdateReminder = reminder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Assertion to ensure that the target index is not null
        assert toUpdateReminder != null : "Reminder settings cannot be null";

        model.setReminderSettings(new ReminderSettings(toUpdateReminder.getReminderOnOff(),
                toUpdateReminder.getDays()));
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
