package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.AMAZON;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.model.company.Company;
import seedu.address.model.company.exceptions.DuplicateCompanyException;
import seedu.address.testutil.CompanyBuilder;

public class InternBookTest {

    private final InternBook internBook = new InternBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internBook.getCompanyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        InternBook newData = getTypicalInternBook();
        internBook.resetData(newData);
        assertEquals(newData, internBook);
    }

    @Test
    public void resetData_withDuplicateCompanies_throwsDuplicateCompanyException() {
        // Two companies with the same identity fields and data fields
        Company editedAlice = new CompanyBuilder(AMAZON).build();
        List<Company> newCompanies = Arrays.asList(AMAZON, editedAlice);
        InternBookStub newData = new InternBookStub(newCompanies);

        assertThrows(DuplicateCompanyException.class, () -> internBook.resetData(newData));
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internBook.hasCompany(null));
    }

    @Test
    public void hasCompany_companyNotInAddressBook_returnsFalse() {
        assertFalse(internBook.hasCompany(AMAZON));
    }

    @Test
    public void hasCompany_companyInAddressBook_returnsTrue() {
        internBook.addCompany(AMAZON);
        assertTrue(internBook.hasCompany(AMAZON));
    }

    @Test
    public void hasCompany_companyWithSameIdentityFieldsInAddressBook_returnsTrue() {
        internBook.addCompany(AMAZON);
        Company editedAlice = new CompanyBuilder(AMAZON).build();
        assertTrue(internBook.hasCompany(editedAlice));
    }

    @Test
    public void getCompanyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internBook.getCompanyList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternBook.class.getCanonicalName() + "{companies=" + internBook.getCompanyList() + "}";
        assertEquals(expected, internBook.toString());
    }

    /**
     * A stub ReadOnlyInternBook whose companies list can violate interface constraints.
     */
    private static class InternBookStub implements ReadOnlyInternBook {
        private final ObservableList<Company> companies = FXCollections.observableArrayList();

        InternBookStub(Collection<Company> companies) {
            this.companies.setAll(companies);
        }

        @Override
        public ObservableList<Company> getCompanyList() {
            return companies;
        }

        @Override
        public ObservableList<Company> getReminderList(ReminderSettings reminderSettings) {
            return companies;
        }
    }

}
