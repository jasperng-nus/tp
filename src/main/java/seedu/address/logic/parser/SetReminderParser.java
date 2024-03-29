package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETREMINDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDERDAYS;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.Days;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderOnOff;

/**
 * Parses input arguments and creates a new SetReminder object
 */
public class SetReminderParser implements Parser<SetReminderCommand> {
    public SetReminderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SETREMINDER, PREFIX_REMINDERDAYS);

        if (!arePrefixesPresent(argMultimap, PREFIX_SETREMINDER, PREFIX_REMINDERDAYS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReminderCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SETREMINDER, PREFIX_REMINDERDAYS);
        Days days = ParserUtil.parseDays(argMultimap.getValue(PREFIX_REMINDERDAYS).get());
        ReminderOnOff reminderOnOff = ParserUtil.parseReminderOnOff(argMultimap.getValue(PREFIX_SETREMINDER).get());
        Reminder reminder = new Reminder(days, reminderOnOff);

        return new SetReminderCommand(reminder);
    }


    /**
     * Return true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
