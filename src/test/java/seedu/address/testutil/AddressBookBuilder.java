package seedu.address.testutil;

import seedu.address.model.InternBook;
import seedu.address.model.person.Company;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private InternBook internBook;

    public AddressBookBuilder() {
        internBook = new InternBook();
    }

    public AddressBookBuilder(InternBook internBook) {
        this.internBook = internBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Company company) {
        internBook.addCompany(company);
        return this;
    }

    public InternBook build() {
        return internBook;
    }
}
