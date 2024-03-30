package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.UnsortedTypicalCompanies.getUnsortedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.sorttype.SortType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getUnsortedTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortCompanyList() {
        SortCommand command = new SortCommand(SortType.ALPHABETICAL_ASCENDING);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        model.sortCompanyListByName();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList(), expectedModel.getFilteredCompanyList());
    }
}
