package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANALYST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ENGINEER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternBook;
import seedu.address.model.company.Company;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class UnsortedTypicalCompanies {

    public static final Company AMAZE = new CompanyBuilder().withName("Amaze")
            .withEmail("amaze@example.com")
            .withPhone("94323490")
            .withTags("Software Engineer").build();

    public static final Company AMAZON = new CompanyBuilder().withName("Amazon")
            .withEmail("amazon@example.com")
            .withPhone("94351253")
            .withTags("Web Developer").build();

    public static final Company APPLE = new CompanyBuilder().withName("Apple")
            .withEmail("apple@example.com")
            .withPhone("94351233")
            .withTags("Software Engineer").build();
    public static final Company BYTEDANCE = new CompanyBuilder().withName("ByteDance")
            .withEmail("bytedance@example.com").withPhone("98765432")
            .withTags("AI Engineer", "UI Designer").build();
    public static final Company CISCO = new CompanyBuilder().withName("Cisco").withPhone("95352563")
            .withEmail("cisco@example.com").withTags("Network Engineer").build();
    public static final Company DELOITTE = new CompanyBuilder().withName("Deloitte").withPhone("87652533")
            .withEmail("deloitte@example.com").withTags("Accounting").build();
    public static final Company EBAY = new CompanyBuilder().withName("eBay").withPhone("9482224")
            .withEmail("ebay@example.com").withTags("UX Designer").build();
    public static final Company FORD = new CompanyBuilder().withName("Ford").withPhone("9482427")
            .withEmail("ford@example.com").withTags("Mechanical Engineer").build();
    public static final Company GOOGLE = new CompanyBuilder().withName("Google").withPhone("9482442")
            .withEmail("google@example.com").withTags("Data Analyst").build();

    // Manually added
    public static final Company HONDA = new CompanyBuilder().withName("Honda").withPhone("8482424")
            .withEmail("honda@example.com").withTags("Mechanical Engineer").build();
    public static final Company IBM = new CompanyBuilder().withName("IBM").withPhone("8482131")
            .withEmail("ibm@example.com").withTags("Electrical Engineer").build();

    // Manually added - Company's details found in {@code CommandTestUtil}
    public static final Company ADIDAS = new CompanyBuilder().withName(VALID_NAME_ADIDAS).withPhone(VALID_PHONE_ADIDAS)
            .withEmail(VALID_EMAIL_ADIDAS).withTags(VALID_TAG_ANALYST).build();
    public static final Company BMW = new CompanyBuilder().withName(VALID_NAME_BMW).withPhone(VALID_PHONE_BMW)
            .withEmail(VALID_EMAIL_BMW).withTags(VALID_TAG_ENGINEER, VALID_TAG_ANALYST)
            .build();

    public static final String KEYWORD_MATCHING_ENGINEER = "Engineer"; // A keyword that matches ENGINEER

    private UnsortedTypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code InternBook} with all the typical companies.
     */
    public static InternBook getUnsortedTypicalAddressBook() {
        InternBook ab = new InternBook();
        for (Company company : getUnsortedTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getUnsortedTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(CISCO, FORD, GOOGLE, BYTEDANCE, AMAZE, DELOITTE, EBAY, AMAZON, APPLE));
    }
}
