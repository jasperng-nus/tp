package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternBook;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.company.Company;
import seedu.address.model.company.Date;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Company[] getSampleCompanies() {
        return new Company[] {
            new Company(new Name("Apple"), new Phone("87438807"), new Email("apple@example.com"), new Date(),
                new Date(), getTagSet("software engineer", "frontend developer")),
            new Company(new Name("Amazon"), new Phone("99272758"), new Email("amazon@example.com"),
                new Date("2024-03-01"), new Date("2024-04-30"), getTagSet("backend developer")),
            new Company(new Name("DBS"), new Phone("91031282"), new Email("dbs@example.com"), new Date(), new Date(),
                getTagSet("security engineer")),
            new Company(new Name("Google"), Phone.getDefaultPhone(), new Email("meta@example.com"),
                    new Date(), new Date(), getTagSet("software engineer")),
            new Company(new Name("Meta"), new Phone("92624417"), new Email("royb@example.com"),
                    new Date(), new Date(), getTagSet("frontend developer")),
            new Company(new Name("OCBC"), Phone.getDefaultPhone(), new Email("ocbc@example.com"),
                    new Date(), new Date(), getTagSet("security engineer"))
        };
    }

    public static ReadOnlyInternBook getSampleAddressBook() {
        InternBook sampleAb = new InternBook();
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
