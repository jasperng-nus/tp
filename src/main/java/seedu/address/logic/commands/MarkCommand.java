package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;

/**
 * Marks a company as applied using it's displayed index from the InternBook.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a company as applied.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_COMPANY_SUCCESS = "Company marked as applied: %1$s";
    public static final String MESSAGE_COMPANY_ALREADY_MARKED = "This company has already been marked as applied";

    private final Index targetIndex;

    /**
     * Creates a MarkCommand to mark the specified {@code Company}
     * @param targetIndex
     */
    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToMark = lastShownList.get(targetIndex.getZeroBased());
        if (model.isCompanyMarked(companyToMark)) {
            throw new CommandException(MESSAGE_COMPANY_ALREADY_MARKED);
        }

        model.markCompany(companyToMark);
        return new CommandResult(String.format(MESSAGE_MARK_COMPANY_SUCCESS, Messages.format(companyToMark)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return targetIndex.equals(otherMarkCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
