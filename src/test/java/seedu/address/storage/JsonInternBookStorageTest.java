package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.AMAZON;
import static seedu.address.testutil.TypicalCompanies.HONDA;
import static seedu.address.testutil.TypicalCompanies.IBM;
import static seedu.address.testutil.TypicalCompanies.getTypicalInternBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternBook;
import seedu.address.model.ReadOnlyInternBook;

public class JsonInternBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternBook(null));
    }

    private java.util.Optional<ReadOnlyInternBook> readInternBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readInternBook("notJsonFormatInternBook.json"));
    }

    @Test
    public void readInternBook_invalidCompanyInternBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readInternBook("invalidCompanyInternBook.json"));
    }

    @Test
    public void readInternBook_invalidAndValidCompanyInternBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readInternBook("invalidAndValidCompanyInternBook.json"));
    }

    @Test
    public void readAndSaveInternBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        InternBook original = getTypicalInternBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyInternBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new InternBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCompany(HONDA);
        original.removeCompany(AMAZON);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new InternBook(readBack));

        // Save and read without specifying file path
        original.addCompany(IBM);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new InternBook(readBack));

    }

    @Test
    public void saveInternBook_nullInternBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveInternBook(ReadOnlyInternBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternBook(new InternBook(), null));
    }
}
