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
            + "Parameters: PREF (must be a single char in [a, s, e])\n"
            + "Example: " + COMMAND_WORD + " a";

    public static final String MESSAGE_ALPHABETICAL = "in alphabetical order";
    public static final String MESSAGE_END_DATE = "in order of ending date";
    public static final String MESSAGE_START_DATE = "in order of starting date";

    public static final String MESSAGE_SUCCESS = "Sorted all entries ";
    private static String outMessage;
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
            outMessage = MESSAGE_SUCCESS + MESSAGE_ALPHABETICAL;
            break;
        case STARTDATE_ASCENDING:
            model.sortCompanyListByStartDate();
            outMessage = MESSAGE_SUCCESS + MESSAGE_START_DATE;
            break;
        case ENDDATE_ASCENDING:
            model.sortCompanyListByEndDate();
            outMessage = MESSAGE_SUCCESS + MESSAGE_END_DATE;
            break;
        default:
            throw new CommandException("Invalid sorting preference.");
        }
        return new CommandResult(String.format(outMessage, sortType.name()));
    }
}
