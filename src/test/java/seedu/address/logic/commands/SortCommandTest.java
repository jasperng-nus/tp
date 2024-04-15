package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;
import static seedu.address.testutil.UnsortedTypicalCompanies.getUnsortedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.sorttype.SortType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getUnsortedTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternBook(), new UserPrefs());

    @Test
    public void execute_sortCompanyListAlphabetical() {
        SortCommand command = new SortCommand(SortType.ALPHANUMERICAL_ASCENDING);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS + SortCommand.MESSAGE_ALPHANUMERICAL;
        model.sortCompanyListByName();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList(), expectedModel.getFilteredCompanyList());
    }

    @Test
    public void execute_sortCompanyListStartingDate() {
        SortCommand command = new SortCommand(SortType.STARTDATE_ASCENDING);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS + SortCommand.MESSAGE_START_DATE;
        model.sortCompanyListByName();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList(), expectedModel.getFilteredCompanyList());
    }

    @Test
    public void execute_sortCompanyListEndDate() {
        SortCommand command = new SortCommand(SortType.ENDDATE_ASCENDING);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS + SortCommand.MESSAGE_END_DATE;
        model.sortCompanyListByName();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList(), expectedModel.getFilteredCompanyList());
    }
}
