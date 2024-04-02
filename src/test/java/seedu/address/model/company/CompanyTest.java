package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ENGINEER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.AMAZON;
import static seedu.address.testutil.TypicalCompanies.BMW;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Company company = new CompanyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> company.getTags().remove(0));
    }

    @Test
    public void isSameCompany() {
        // same object -> returns true
        assertTrue(AMAZON.isSameCompany(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.isSameCompany(null));

        // same name, all other attributes different -> returns true
        Company editedAlice = new CompanyBuilder(AMAZON).withPhone(VALID_PHONE_BMW).withEmail(VALID_EMAIL_BMW)
                .withTags(VALID_TAG_ENGINEER).build();
        assertFalse(AMAZON.isSameCompany(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CompanyBuilder(AMAZON).withName(VALID_NAME_BMW).build();
        assertFalse(AMAZON.isSameCompany(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Company editedBob = new CompanyBuilder(BMW).withName(VALID_NAME_BMW.toLowerCase()).build();
        assertFalse(BMW.isSameCompany(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BMW + " ";
        editedBob = new CompanyBuilder(BMW).withName(nameWithTrailingSpaces).build();
        assertFalse(BMW.isSameCompany(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Company aliceCopy = new CompanyBuilder(AMAZON).build();
        assertTrue(AMAZON.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AMAZON.equals(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.equals(null));

        // different type -> returns false
        assertFalse(AMAZON.equals(5));

        // different company -> returns false
        assertFalse(AMAZON.equals(BMW));

        // different name -> returns false
        Company editedAlice = new CompanyBuilder(AMAZON).withName(VALID_NAME_BMW).build();
        assertFalse(AMAZON.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CompanyBuilder(AMAZON).withPhone(VALID_PHONE_BMW).build();
        assertFalse(AMAZON.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CompanyBuilder(AMAZON).withEmail(VALID_EMAIL_BMW).build();
        assertFalse(AMAZON.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CompanyBuilder(AMAZON).withTags(VALID_TAG_ENGINEER).build();
        assertFalse(AMAZON.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Company.class.getCanonicalName()
                + "{name=" + AMAZON.getName()
                + ", phone=" + AMAZON.getPhone()
                + ", email=" + AMAZON.getEmail()
                + ", startDate=" + AMAZON.getStartDate()
                + ", endDate=" + AMAZON.getEndDate()
                + ", tags=" + AMAZON.getTags()
                + "}";
        System.out.println(expected);
        System.out.println(AMAZON.toString());
        assertEquals(expected, AMAZON.toString());
    }
}
