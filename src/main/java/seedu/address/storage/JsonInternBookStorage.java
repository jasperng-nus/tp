package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyInternBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonInternBookStorage implements InternBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternBookStorage.class);

    private Path filePath;

    public JsonInternBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternBook> readInternBook() throws DataLoadingException {
        return readInternBook(filePath);
    }

    /**
     * Similar to {@link #readInternBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyInternBook> readInternBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveInternBook(ReadOnlyInternBook internBook) throws IOException {
        saveInternBook(internBook, filePath);
    }

    /**
     * Similar to {@link #saveInternBook(ReadOnlyInternBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternBook(ReadOnlyInternBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternBook(addressBook), filePath);
    }

}
