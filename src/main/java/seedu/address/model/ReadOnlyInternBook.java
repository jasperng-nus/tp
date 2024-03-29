package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.company.Company;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInternBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Company> getCompanyList();

}