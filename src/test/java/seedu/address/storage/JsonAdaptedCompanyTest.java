package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.BYTEDANCE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;

public class JsonAdaptedCompanyTest {
    private static final String INVALID_NAME = "@mazon";
    private static final String INVALID_PHONE = "+651234";

    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#software engineer";

    private static final String VALID_NAME = BYTEDANCE.getName().toString();
    private static final String VALID_PHONE = BYTEDANCE.getPhone().toString();
    private static final String VALID_EMAIL = BYTEDANCE.getEmail().toString();
    private static final String VALID_START_DATE = BYTEDANCE.getStartDate().toString();
    private static final String VALID_END_DATE = BYTEDANCE.getEndDate().toString();
    private static final boolean VALID_IS_MARKED = BYTEDANCE.isMarked();

    private static final List<JsonAdaptedTag> VALID_TAGS = BYTEDANCE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(BYTEDANCE);
        assertEquals(BYTEDANCE, company.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_START_DATE, VALID_END_DATE,
                        VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(null, VALID_PHONE, VALID_EMAIL, VALID_START_DATE,
                VALID_END_DATE, VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_START_DATE, VALID_END_DATE,
                        VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_NAME, null, VALID_EMAIL, VALID_START_DATE,
                VALID_END_DATE, VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_START_DATE, VALID_END_DATE,
                        VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, null, VALID_START_DATE,
                VALID_END_DATE, VALID_TAGS, VALID_IS_MARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_START_DATE, VALID_END_DATE,
                        invalidTags, VALID_IS_MARKED);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

}
