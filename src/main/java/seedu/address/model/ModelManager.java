package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.model.company.Company;
import seedu.address.model.reminder.Reminder;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternBook internBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Company> filteredCompanies;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.internBook = new InternBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCompanies = new FilteredList<>(this.internBook.getCompanyList());
    }

    public ModelManager() {
        this(new InternBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public ReminderSettings getReminderSettings() {
        return userPrefs.getReminderSettings();
    }

    @Override
    public void setReminderSettings(ReminderSettings reminderSettings) {
        requireNonNull(reminderSettings);
        userPrefs.setReminderSettings(reminderSettings);
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyInternBook addressBook) {
        this.internBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyInternBook getAddressBook() {
        return internBook;
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return internBook.hasCompany(company);
    }

    @Override
    public void deleteCompany(Company target) {
        internBook.removeCompany(target);
    }

    @Override
    public void addCompany(Company company) {
        internBook.addCompany(company);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setCompany(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);

        internBook.setCompany(target, editedCompany);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        return filteredCompanies;
    }

    @Override
    public void updateFilteredCompanyList(Predicate<Company> predicate) {
        requireNonNull(predicate);
        filteredCompanies.setPredicate(predicate);
    }

    @Override
    public void sortCompanyList() {
        internBook.sortCompanyList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return internBook.equals(otherModelManager.internBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCompanies.equals(otherModelManager.filteredCompanies);
    }

}
