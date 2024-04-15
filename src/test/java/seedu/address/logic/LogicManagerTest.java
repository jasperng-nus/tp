package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ADIDAS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ADIDAS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ADIDAS;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.storage.JsonInternBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.CompanyBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternBookStorage internBookStorage =
                new JsonInternBookStorage(temporaryFolder.resolve("internBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getInternBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an InternBookStorage that throws the IOException e when saving
        JsonInternBookStorage internBookStorage = new JsonInternBookStorage(prefPath) {
            @Override
            public void saveInternBook(ReadOnlyInternBook internBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveInternBook method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_ADIDAS + PHONE_DESC_ADIDAS
                + EMAIL_DESC_ADIDAS;
        Company expectedCompany = new CompanyBuilder(ADIDAS).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCompany(expectedCompany);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getAddressBookFilePath_returnsCorrectPath() {
        Path expectedPath = Path.of("sample/path/internBook.json");

        Model model = new ModelManager();
        model.setInternBookFilePath(expectedPath);

        Logic logic = new LogicManager(model, null);

        Path actualPath = logic.getAddressBookFilePath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void getGuiSettings_returnsModelGuiSettings() {
        // Create a sample GuiSettings
        GuiSettings expectedGuiSettings = new GuiSettings(1000, 600, 200, 100);

        // Create a mock model that returns this GuiSettings
        Model model = new ModelManager();
        model.setGuiSettings(expectedGuiSettings);

        // Set up LogicManager with the model
        Logic logic = new LogicManager(model, null);

        // Ensure the returned GuiSettings matches the expected GuiSettings
        GuiSettings actualGuiSettings = logic.getGuiSettings();
        assertEquals(expectedGuiSettings, actualGuiSettings);
    }

    @Test
    public void setGuiSettings_setsModelGuiSettings() {
        // Create a sample GuiSettings
        GuiSettings guiSettings = new GuiSettings(1000, 600, 200, 100);

        // Create a mock model
        Model model = new ModelManager();

        // Set up LogicManager with the model
        Logic logic = new LogicManager(model, null);

        // Set the GuiSettings via LogicManager
        logic.setGuiSettings(guiSettings);

        // Ensure the model's GuiSettings have been updated
        GuiSettings modelGuiSettings = model.getGuiSettings();
        assertEquals(guiSettings, modelGuiSettings);
    }
}
