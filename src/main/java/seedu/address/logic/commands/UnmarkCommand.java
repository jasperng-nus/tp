package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Company;

/**
 * Unmarks a company as applied using it's displayed index from the InternBook.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks a company as applied.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_COMPANY_SUCCESS = "Company unmarked as applied: %1$s";
    public static final String MESSAGE_NUMBER_INVALID = "The index provided is invalid";
    public static final String MESSAGE_COMPANY_ALREADY_UNMARKED = "This company has not been marked as applied yet";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToUnmark = lastShownList.get(targetIndex.getZeroBased());
        if (!model.isCompanyMarked(companyToUnmark)) {
            throw new CommandException(MESSAGE_COMPANY_ALREADY_UNMARKED);
        }

        model.unmarkCompany(companyToUnmark);
        return new CommandResult(String.format(MESSAGE_UNMARK_COMPANY_SUCCESS, Messages.format(companyToUnmark)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        UnmarkCommand otherUnmarkCommand = (UnmarkCommand) other;
        return targetIndex.equals(otherUnmarkCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
