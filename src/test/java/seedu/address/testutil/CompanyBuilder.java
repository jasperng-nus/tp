package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.company.Company;
import seedu.address.model.company.Date;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TAG = "SoftwareEngineer";
    public static final String DEFAULT_STARTDATE = "2024-07-07";
    public static final String DEFAULT_ENDDATE = "2024-08-08";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private Date startDate;
    private Date endDate;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        startDate = new Date(DEFAULT_STARTDATE);
        endDate = new Date(DEFAULT_ENDDATE);
        tags = new HashSet<>(Arrays.asList(new Tag(DEFAULT_TAG)));
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code personToCopy}.
     */
    public CompanyBuilder(Company companyToCopy) {
        name = companyToCopy.getName();
        phone = companyToCopy.getPhone();
        email = companyToCopy.getEmail();
        startDate = companyToCopy.getStartDate();
        endDate = companyToCopy.getEndDate();
        tags = new HashSet<>(companyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public CompanyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building
     * to DEFAULT_NUMBER
     */
    public CompanyBuilder withPhone() {
        this.phone = new Phone();
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Company build() {
        return new Company(name, phone, email, startDate, endDate, tags);
    }
}
