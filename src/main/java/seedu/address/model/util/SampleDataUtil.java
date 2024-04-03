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
            new Company(new Name("Amazon"), new Phone("87438807"), new Email("hr@amazon.com"),
                    new Date("2024-04-04"), new Date("2024-07-24"), getTagSet("Software Engineer")),
            new Company(new Name("ByteDance"), new Phone("85647321"), new Email("hr@barclays.com"),
                    new Date("2024-03-07"), new Date("2024-07-26"), getTagSet("Software Engineer")),
            new Company(new Name("Capcom"), new Phone("98717234"), new Email("hr@capcom.com"),
                    new Date("2024-05-17"), new Date("2024-08-17"), getTagSet("Game Developer")),
            new Company(new Name("Dyson"), new Phone("95423456"), new Email("hr@dyson.com"),
                    new Date("2024-03-01"), new Date("2024-06-27"), getTagSet("Data Scientist")),
            new Company(new Name("Netflix"), new Phone("92784160"), new Email("hr@netflix.com"),
                    new Date("2024-06-13"), new Date("2024-09-29"), getTagSet("Machine Learning Engineer")),
            new Company(new Name("Jane Street"), new Phone("98097645"), new Email("hr@janestreet.com"),
                    new Date("2024-02-01"), new Date("2024-05-13"), getTagSet("Quantitative Researcher")),
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
