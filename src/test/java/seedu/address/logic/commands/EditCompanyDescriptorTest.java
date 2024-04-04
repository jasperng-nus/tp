package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BMW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCommand.EditCompanyDescriptor(DESC_ADIDAS);
     ;
        assertTrue(DESC_ADIDAS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ADIDAS.equals(DESC_ADIDAS));

        // null -> returns false
        assertFalse(DESC_ADIDAS.equals(null));

        // different types -> returns false
        assertFalse(DESC_ADIDAS.equals(5));

        // different values -> returns false
        assertFalse(DESC_ADIDAS.equals(DESC_BMW));

        // different name -> returns false
        EditCommand.EditCompanyDescriptor editedAmy = new EditCompanyDescriptorBuilder(
                DESC_ADIDAS).withName(VALID_NAME_BMW).build();
        assertFalse(DESC_ADIDAS.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_ADIDAS).withPhone(VALID_PHONE_BMW).build();
        assertFalse(DESC_ADIDAS.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_ADIDAS).withEmail(VALID_EMAIL_BMW).build();
        assertFalse(DESC_ADIDAS.equals(editedAmy));



        // different tags -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_ADIDAS).withTags(VALID_TAG_ENGINEER).build();
        assertFalse(DESC_ADIDAS.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        String expected = EditCommand.EditCompanyDescriptor.class.getCanonicalName() + "{name="
                + editCompanyDescriptor.getName().orElse(null) + ", phone="
                + editCompanyDescriptor.getPhone().orElse(null) + ", email="
                + editCompanyDescriptor.getStartDate().orElse(null) + ", startDate="
                + editCompanyDescriptor.getEndDate().orElse(null) + ", endDate="
                + editCompanyDescriptor.getEmail().orElse(null) + ", tags="
                + editCompanyDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editCompanyDescriptor.toString());
    }
}
