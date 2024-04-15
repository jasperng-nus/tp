package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.company.Company;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getInternBook()
     */
    ReadOnlyInternBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of companies */
    ObservableList<Company> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of companies for reminders */
    ObservableList<Company> getFilteredCompaniesRemindersList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Reminder settings
     */
    ReminderSettings getReminderSettings();

    /**
     * Set the user prefs' Reminder settings.
     */
    void setReminderSettings(ReminderSettings reminderSettings);

    /**
     * Returns the user prefs' reminder status
     * @return true for reminder switched on and false for reminder switched off.
     */
    boolean getReminderStatus();
}
