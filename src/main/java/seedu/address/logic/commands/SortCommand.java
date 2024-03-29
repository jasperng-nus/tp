package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.sorttype.SortType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the companies in the list in alphabetical order.
 * Sorting is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the companies by the user's preference.\n"
            + "Parameters: PREF (must be a single char)\n"
            + "Example: " + COMMAND_WORD + " a";
    public static final String MESSAGE_SUCCESS = "Sorted all companies alphabetically";

    public final SortType sortType;

    public SortCommand(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (sortType == null) {
            throw new CommandException("Invalid sorting preference.");
        }
        switch (sortType) {
        case ALPHABETICAL_ASCENDING:
            model.sortCompanyListByName();
            break;
        case STARTDATE_ASCENDING:
            model.sortCompanyListByStartDate();
            break;
        case ENDDATE_ASCENDING:
            model.sortCompanyListByEndDate();
            break;
        default:
            throw new CommandException("Invalid sorting preference.");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortType.name()));
    }
}
