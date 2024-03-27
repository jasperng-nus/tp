package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Date startDate;
    private final Date deadline;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Company(Name name, Phone phone, Email email, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.startDate = new Date("2024-01-01");
        this.deadline = new Date("2024-01-02");
    }

    /**
     * This constructor is used for integration with date. Remove this comment and old constructor after completion.
     */
    public Company(Name name, Phone phone, Email email, Date startDate, Date deadline, Set<Tag> tags) {
        requireAllNonNull(name, phone, startDate, deadline, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.startDate = startDate;
        this.deadline = deadline;
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
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both companies have the same name, email, phone, startDate, deadline and tags.
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
                && deadline.equals(otherCompany.deadline)
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
                && deadline.equals(otherCompany.deadline)
                && tags.equals(otherCompany.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, startDate, deadline, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("startDate", startDate)
                .add("deadline", deadline)
                .add("tags", tags)
                .toString();
    }

}
