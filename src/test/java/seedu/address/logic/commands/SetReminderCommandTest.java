package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.Days;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderOnOff;

class SetReminderCommandTest {
    private Model model = new ModelManager(getTypicalInternBook(), new UserPrefs());

    @Test
    void execute_setReminderValidParameters_commandSuccessful() throws Exception {
        Reminder reminder = new Reminder(new Days("1"), new ReminderOnOff("true"));
        SetReminderCommand setReminder = new SetReminderCommand(reminder);
        CommandResult commandResult = setReminder.execute(model);
        assertEquals(String.format(SetReminderCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }
}
