package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyInternBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternBookStorage internBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternBookStorage internBookStorage, UserPrefsStorage userPrefsStorage) {
        this.internBookStorage = internBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getInternBookFilePath() {
        return internBookStorage.getInternBookFilePath();
    }

    @Override
    public Optional<ReadOnlyInternBook> readInternBook() throws DataLoadingException {
        return readInternBook(internBookStorage.getInternBookFilePath());
    }

    @Override
    public Optional<ReadOnlyInternBook> readInternBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internBookStorage.readInternBook(filePath);
    }

    @Override
    public void saveInternBook(ReadOnlyInternBook internBook) throws IOException {
        saveInternBook(internBook, internBookStorage.getInternBookFilePath());
    }

    @Override
    public void saveInternBook(ReadOnlyInternBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internBookStorage.saveInternBook(addressBook, filePath);
    }

}
