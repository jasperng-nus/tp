package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CompanyBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW
                 + TAG_DESC_ANALYST;

        // multiple names
        assertParseFailure(parser, NAME_DESC_ADIDAS + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_ADIDAS + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_ADIDAS + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_ADIDAS + EMAIL_DESC_ADIDAS + NAME_DESC_ADIDAS
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));



        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));


    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW + TAG_DESC_ANALYST,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BMW + PHONE_DESC_BMW + VALID_EMAIL_BMW + TAG_DESC_ANALYST,
                expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW + VALID_TAG_ANALYST,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BMW + VALID_PHONE_BMW + VALID_EMAIL_BMW,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BMW + EMAIL_DESC_BMW
                + TAG_DESC_ENGINEER + TAG_DESC_ANALYST, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BMW + INVALID_PHONE_DESC + EMAIL_DESC_BMW
                + TAG_DESC_ENGINEER + TAG_DESC_ANALYST, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BMW + PHONE_DESC_BMW + INVALID_EMAIL_DESC
                + TAG_DESC_ENGINEER + TAG_DESC_ANALYST, Email.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW
                + INVALID_TAG_DESC + VALID_TAG_ANALYST, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BMW + EMAIL_DESC_BMW + TAG_DESC_ANALYST,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW
                 + TAG_DESC_ENGINEER + TAG_DESC_ANALYST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_phoneFieldsMissing_success() {
        //zero phone
        Company expectedCompany = new CompanyBuilder(CITI).withPhone().build();
        assertParseSuccess(parser, NAME_DESC_CITI + EMAIL_DESC_CITI + TAG_DESC_ENGINEER + START_DATE_DESC
                + END_DATE_DESC, new AddCommand(expectedCompany));
    }

    @Test
    public void parse_startDateFieldsMissing_success() {
        //zero phone
        Company expectedCompany = new CompanyBuilder(CITI).withStartDate().build();
        assertParseSuccess(parser, NAME_DESC_CITI + EMAIL_DESC_CITI + TAG_DESC_ENGINEER + PHONE_DESC_CITI
                + END_DATE_DESC, new AddCommand(expectedCompany));
    }

    @Test
    public void parse_endDateFieldsMissing_success() {
        //zero phone
        Company expectedCompany = new CompanyBuilder(CITI).withEndDate().build();
        assertParseSuccess(parser, NAME_DESC_CITI + EMAIL_DESC_CITI + TAG_DESC_ENGINEER + PHONE_DESC_CITI
                + START_DATE_DESC, new AddCommand(expectedCompany));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(BMW).withTags(VALID_TAG_ANALYST).build();
        //whitesapce only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW
                + START_DATE_DESC + END_DATE_DESC + TAG_DESC_ANALYST, new AddCommand(expectedCompany));

        //multiple tags - all accepted
        Company expectCompanyMultipleTags = new CompanyBuilder(BMW).withTags(VALID_TAG_ANALYST, VALID_TAG_ENGINEER)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BMW + PHONE_DESC_BMW + EMAIL_DESC_BMW + START_DATE_DESC + END_DATE_DESC
                        + TAG_DESC_ANALYST + TAG_DESC_ENGINEER, new AddCommand(expectCompanyMultipleTags));
    }

    @Test
    public void parse_startDateLaterThanEndDate_throwsParseException() {
        String args = NAME_DESC_ADIDAS + PHONE_DESC_BMW + EMAIL_DESC_BMW + LATER_START_DATE_DESC
                + EARLIER_END_DATE_DESC + TAG_DESC_ENGINEER;
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
