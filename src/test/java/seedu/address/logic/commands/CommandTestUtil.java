package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternBook;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ADIDAS = "Adidas";
    public static final String VALID_NAME_BMW = "BMW";
    public static final String VALID_NAME_CITI = "Citibank";
    public static final String VALID_PHONE_ADIDAS = "11111111";
    public static final String VALID_PHONE_BMW = "22222222";
    public static final String VALID_PHONE_CITI = "33333333";
    public static final String VALID_EMAIL_ADIDAS = "adidas@example.com";
    public static final String VALID_EMAIL_BMW = "bmw@example.com";
    public static final String VALID_EMAIL_CITI = "citibank@example.com";
    public static final String VALID_TAG_ENGINEER = "Engineer";
    public static final String VALID_TAG_ANALYST = "Analyst";
    public static final String VALID_START_DATE = "2024-01-01"; // YYYY-MM-DD
    public static final String VALID_END_DATE = "2024-06-01"; // YYYY-MM-DD
    public static final String LATER_START_DATE = "2024-03-03"; // YYYY-MM-DD
    public static final String EARLIER_END_DATE = "2024-02-02"; // YYYY-MM-DD

    public static final String NAME_DESC_ADIDAS = " " + PREFIX_NAME + VALID_NAME_ADIDAS;
    public static final String NAME_DESC_BMW = " " + PREFIX_NAME + VALID_NAME_BMW;
    public static final String NAME_DESC_CITI = " " + PREFIX_NAME + VALID_NAME_CITI;
    public static final String PHONE_DESC_ADIDAS = " " + PREFIX_PHONE + VALID_PHONE_ADIDAS;
    public static final String PHONE_DESC_BMW = " " + PREFIX_PHONE + VALID_PHONE_BMW;
    public static final String PHONE_DESC_CITI = " " + PREFIX_PHONE + VALID_PHONE_CITI;
    public static final String EMAIL_DESC_ADIDAS = " " + PREFIX_EMAIL + VALID_EMAIL_ADIDAS;
    public static final String EMAIL_DESC_BMW = " " + PREFIX_EMAIL + VALID_EMAIL_BMW;
    public static final String EMAIL_DESC_CITI = " " + PREFIX_EMAIL + VALID_EMAIL_CITI;

    public static final String END_DATE_DESC = " " + PREFIX_ENDDATE + VALID_END_DATE;
    public static final String START_DATE_DESC = " " + PREFIX_STARTDATE + VALID_START_DATE;

    public static final String TAG_DESC_ANALYST = " " + PREFIX_TAG + VALID_TAG_ANALYST;
    public static final String TAG_DESC_ENGINEER = " " + PREFIX_TAG + VALID_TAG_ENGINEER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "dbs&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "dbs!yahoo"; // missing '@' symbol

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "engineer*"; // '*' not allowed in tags

    public static final String EARLIER_END_DATE_DESC = " " + PREFIX_ENDDATE + EARLIER_END_DATE;
    public static final String LATER_START_DATE_DESC = " " + PREFIX_STARTDATE + LATER_START_DATE;
    public static final String INVALID_STARTDATE_DESC = " " + PREFIX_STARTDATE + "2024-13-21"; //month cannot be past 12
    public static final String INVALID_ENDDATE_DESC = " " + PREFIX_ENDDATE + "2024-11-21@"; //@ not allowed in dates

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCompanyDescriptor DESC_ADIDAS;
    public static final EditCommand.EditCompanyDescriptor DESC_BMW;

    static {
        DESC_ADIDAS = new EditCompanyDescriptorBuilder().withName(VALID_NAME_ADIDAS)
                .withPhone(VALID_PHONE_ADIDAS).withEmail(VALID_EMAIL_ADIDAS)
                .withTags(VALID_TAG_ANALYST).build();
        DESC_BMW = new EditCompanyDescriptorBuilder().withName(VALID_NAME_BMW)
                .withPhone(VALID_PHONE_BMW).withEmail(VALID_EMAIL_BMW)
                .withTags(VALID_TAG_ENGINEER, VALID_TAG_ANALYST)
                .withStartDate(VALID_START_DATE).withEndDate(VALID_END_DATE)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered company list and selected company in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternBook expectedInternBook = new InternBook(actualModel.getInternBook());
        List<Company> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCompanyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternBook, actualModel.getInternBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCompanyList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the company at the given {@code targetIndex} in the
     * {@code model}'s intern book.
     */
    public static void showCompanyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyList().size());

        Company company = model.getFilteredCompanyList().get(targetIndex.getZeroBased());
        final String[] splitName = company.getName().fullName.split("\\s+");
        model.updateFilteredCompanyList(new NameContainsKeywordsPredicate(splitName[0]));

        assertEquals(1, model.getFilteredCompanyList().size());
    }

}
