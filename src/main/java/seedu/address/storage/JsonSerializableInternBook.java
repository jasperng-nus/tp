package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternBook;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.company.Company;

/**
 * An Immutable InternBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableInternBook {

    public static final String MESSAGE_DUPLICATE_COMPANY = "Companies list contains duplicate company(s).";

    private final List<JsonAdaptedCompany> companies = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableInternBook(@JsonProperty("companies") List<JsonAdaptedCompany> companies) {
        this.companies.addAll(companies);
    }

    /**
     * Converts a given {@code ReadOnlyInternBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableInternBook(ReadOnlyInternBook source) {
        companies.addAll(source.getCompanyList().stream().map(JsonAdaptedCompany::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternBook toModelType() throws IllegalValueException {
        InternBook internBook = new InternBook();
        for (JsonAdaptedCompany jsonAdaptedCompany : companies) {
            Company company = jsonAdaptedCompany.toModelType();
            if (internBook.hasCompany(company)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMPANY);
            }
            internBook.addCompany(company);
        }
        return internBook;
    }

}
