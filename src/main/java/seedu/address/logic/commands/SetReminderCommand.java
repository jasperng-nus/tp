package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETREMINDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDERDAYS;

import seedu.address.commons.core.ReminderSettings;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

public class SetReminderCommand extends Command {
    public static final String COMMAND_WORD = "setReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets reminders to the applications"
            + "Parameters: "
            + PREFIX_SETREMINDER + " true or false "
            + PREFIX_REMINDERDAYS + " number of days from the end date of internship applications "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_SETREMINDER + " true "
            + PREFIX_REMINDERDAYS + " 7 ";

    public static final String MESSGAE_SUCCESS = "Reminder turned on";
    private final Reminder toUpdateReminder;

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
        return new CommandResult(String.format(MESSGAE_SUCCESS));
    }
}
