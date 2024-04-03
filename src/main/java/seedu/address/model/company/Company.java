package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Date startDate;
    private final Date endDate;
    private final Set<Tag> tags = new HashSet<>();
    private boolean isMarked;

    // GUI fields
    private final BooleanProperty checkboxIsMarked = new SimpleBooleanProperty();

    /**
     * Every field must be present and not null.
     */
    public Company(Name name, Phone phone, Email email, Date startDate, Date endDate, Set<Tag> tags) {
        requireAllNonNull(name, phone, startDate, endDate, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.startDate = startDate;
        this.endDate = endDate;
        this.isMarked = false;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both companies have the same name, email, phone, startDate, endDate and tags.
     */
    public boolean isSameCompany(Company otherCompany) {
        if (otherCompany == null) {
            return false;
        }
        if (otherCompany == this) {
            return true;
        }
        return name.equals(otherCompany.name)
                && phone.equals(otherCompany.phone)
                && email.equals(otherCompany.email)
                && startDate.equals(otherCompany.startDate)
                && endDate.equals(otherCompany.endDate)
                && tags.equals(otherCompany.tags);

    }

    /**
     * Returns true if both companies have the same identity and data fields
     * This defines a stronger notion of equality between two companies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return name.equals(otherCompany.name)
                && phone.equals(otherCompany.phone)
                && email.equals(otherCompany.email)
                && startDate.equals(otherCompany.startDate)
                && endDate.equals(otherCompany.endDate)
                && tags.equals(otherCompany.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, startDate, endDate, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("tags", tags)
                .toString();
    }

    /**
     * Sets the application status of the company to marked.
     */
    public void mark() {
        isMarked = true;
        checkboxIsMarked.set(true);
    }

    /**
     * Sets the application status of the company to unmarked.
     */
    public void unmark() {
        isMarked = false;
        checkboxIsMarked.set(false);
    }

    /**
     * Returns the application status of the company.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Returns the application status of the company as a BooleanProperty.
     * This is used to bind the application status of the company to the GUI.
     */
    public BooleanProperty checkboxIsMarked() {
        return checkboxIsMarked;
    }
}
