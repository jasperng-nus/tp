package seedu.address.testutil;

import seedu.address.model.InternBook;
import seedu.address.model.company.Company;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new InternBookBuilder().withPerson("John", "Doe").build();}
 */
public class InternBookBuilder {

    private final InternBook internBook;

    public InternBookBuilder() {
        internBook = new InternBook();
    }

    public InternBookBuilder(InternBook internBook) {
        this.internBook = internBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public InternBookBuilder withPerson(Company company) {
        internBook.addCompany(company);
        return this;
    }

    public InternBook build() {
        return internBook;
    }
}
