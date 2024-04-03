package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SETREMINDER;

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

    private static final String DEFAULT_DAY = "0";

    /**
     * Parses the given {@code String} of arguments in the context of the SetReminderCommand
     * and returns a SetReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetReminderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SETREMINDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SETREMINDER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReminderCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SETREMINDER);
        String userInput = argMultimap.getValue(PREFIX_SETREMINDER).get();
        ReminderOnOff reminderOnOff;
        Days days;
        if (isNumber(userInput)) {
            reminderOnOff = ParserUtil.parseReminderOnOff("true");
            days = ParserUtil.parseDays(userInput);
        } else {
            reminderOnOff = ParserUtil.parseReminderOnOff("false");
            days = ParserUtil.parseDays(DEFAULT_DAY);
        }

        Reminder reminder = new Reminder(days, reminderOnOff);

        return new SetReminderCommand(reminder);
    }

    /**
     * Checks if the input string represents a valid integer or the string "off".
     *
     * @param input The input string to be checked.
     * @return {@code true} if the input is a valid integer or the string "off", {@code false} otherwise.
     * @throws ParseException if the input is not a valid integer or "off"
     */
    public boolean isNumber(String input) throws ParseException {
        try {
            int isInt = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            if (input.equalsIgnoreCase("off")) {
                return false;
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SetReminderCommand.MESSAGE_USAGE));
            }
        }
    }


    /**
     * Return true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
