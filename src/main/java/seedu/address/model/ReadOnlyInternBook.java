package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.model.company.Company;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInternBook {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

    /**
     * Returns an unmodifiable view of the companies list to remind users.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getReminderList(ReminderSettings reminderSettings);
}
