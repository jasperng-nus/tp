package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
class MarkCommandTest {
    private Model model = new ModelManager(getTypicalInternBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Company companyToMark = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_COMPANY);
        // Unmark the company first
        model.unmarkCompany(companyToMark);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_COMPANY_SUCCESS,
                Messages.format(companyToMark));

        ModelManager expectedModel = new ModelManager(model.getInternBook(), new UserPrefs());
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyMarked_throwsCommandException() {
        Company companyToMark = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_COMPANY);
        model.markCompany(companyToMark);

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_COMPANY_ALREADY_MARKED);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_COMPANY);
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_COMPANY);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_COMPANY);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));

        // different index -> returns false
        assertFalse(markFirstCommand.equals(new MarkCommand(INDEX_SECOND_COMPANY)));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(targetIndex);
        String expected = MarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, markCommand.toString());
    }
}
