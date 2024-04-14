package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternBook;
import seedu.address.model.ReadOnlyInternBook;

/**
 * Represents a storage for {@link InternBook}.
 */
public interface InternBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternBookFilePath();

    /**
     * Returns InternBook data as a {@link ReadOnlyInternBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInternBook> readInternBook() throws DataLoadingException;

    /**
     * @see #getInternBookFilePath()
     */
    Optional<ReadOnlyInternBook> readInternBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternBook} to the storage.
     * @param internBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternBook(ReadOnlyInternBook internBook) throws IOException;

    /**
     * @see #saveInternBook(ReadOnlyInternBook)
     */
    void saveInternBook(ReadOnlyInternBook addressBook, Path filePath) throws IOException;

}
