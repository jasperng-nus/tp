package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.Company;
import seedu.address.model.company.UniqueCompanyList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class InternBook implements ReadOnlyInternBook {

    private final UniqueCompanyList companies;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        companies = new UniqueCompanyList();
    }

    public InternBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public InternBook(ReadOnlyInternBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the company list with {@code persons}.
     * {@code persons} must not contain duplicate companies.
     */
    public void setCompanies(List<Company> companies) {
        this.companies.setCompany(companies);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternBook newData) {
        requireNonNull(newData);

        setCompanies(newData.getCompanyList());
    }

    //// company-level operations

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companies.contains(company);
    }

    /**
     * Adds a company to the address book.
     * The company must not already exist in the address book.
     */
    public void addCompany(Company p) {
        companies.add(p);
    }

    /**
     * Replaces the given company {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the
     * same as another existing company in the intern book.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireNonNull(editedCompany);

        companies.setCompany(target, editedCompany);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCompany(Company key) {
        companies.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companies", companies)
                .toString();
    }

    @Override
    public ObservableList<Company> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternBook)) {
            return false;
        }

        InternBook otherInternBook = (InternBook) other;
        return companies.equals(otherInternBook.companies);
    }
    /**
     * Sorts the list of companies by their name in ascending order, ignoring case.
     */
    public void sortCompanyListByName() {
        companies.sortCompanyListByName();
    }

    public void sortCompanyListByStartDate() {
        companies.sortCompanyListByStartDate();
    }

    public void sortCompanyListByEndDate() {
        companies.sortCompanyListByEndDate();
    }
    @Override
    public int hashCode() {
        return companies.hashCode();
    }
}
