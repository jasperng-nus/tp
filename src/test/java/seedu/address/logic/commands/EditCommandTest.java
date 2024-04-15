package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BMW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternBook(), new UserPrefs());
    @Test
    public void execute_validEditedCompany_successful() throws Exception {

        Company johnson = new CompanyBuilder()
                .withName("JJ")
                .withPhone()
                .withEmail("JandJ@gmail.com")
                .withTags("Analyst").build();

        Company newJohnson = new CompanyBuilder()
                .withName("JJ")
                .withPhone("91234567")
                .withEmail("JandJ@gmail.com")
                .withTags("Analyst").build();

        ModelStubWithCompany modelStub = new ModelStubWithCompany(johnson);
        Index index = Index.fromZeroBased(0);

        EditCompanyDescriptor johnsonDescriptor =
                new EditCompanyDescriptorBuilder(johnson).withPhone("91234567").build();
        EditCommand editCommand = new EditCommand(index, johnsonDescriptor);
        CommandResult commandResult = editCommand.execute(modelStub);

        assertEquals(
                String.format(EditCommand.MESSAGE_EDIT_COMPANY_SUCCESS, Messages.format(newJohnson)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.hasCompany(newJohnson));
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(VALID_NAME_BMW).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of internbook
     */
    @Test
    public void execute_invalidCompanyIndexFilteredList_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);
        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of internbook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternBook().getCompanyList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditCompanyDescriptorBuilder().withName(VALID_NAME_BMW).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_COMPANY, DESC_ADIDAS);

        // same values -> returns true
        EditCompanyDescriptor copyDescriptor = new EditCompanyDescriptor(DESC_ADIDAS);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_COMPANY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_COMPANY, DESC_ADIDAS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_COMPANY, DESC_BMW)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        EditCommand editCommand = new EditCommand(index, editCompanyDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editCompanyDescriptor="
                + editCompanyDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReminderSettings getReminderSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReminderSettings(ReminderSettings reminderSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getReminderStatus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternBook(ReadOnlyInternBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternBook getInternBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompaniesRemindersList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortCompanyListByName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortCompanyListByStartDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortCompanyListByEndDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isCompanyMarked(Company company) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single company.
     */
    private class ModelStubWithCompany extends EditCommandTest.ModelStub {
        private ObservableList<Company> companies = FXCollections.observableArrayList();

        ModelStubWithCompany(Company company) {
            requireNonNull(company);
            this.companies.add(company);
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            requireAllNonNull(target, editedCompany);
            this.companies.set(0, editedCompany);
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            return companies;
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return this.companies.get(0).isSameCompany(company);
        }
    }
}
