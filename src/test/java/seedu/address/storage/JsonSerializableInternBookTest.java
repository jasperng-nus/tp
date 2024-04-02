package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableInternBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternBookTest");
    private static final Path TYPICAL_COMPANIES_FILE = TEST_DATA_FOLDER.resolve("typicalCompaniesInternBook.json");
    private static final Path INVALID_COMPANY_FILE = TEST_DATA_FOLDER.resolve("invalidCompanyInternBook.json");
    private static final Path DUPLICATE_COMPANIES_FILE = TEST_DATA_FOLDER.resolve("duplicateCompanyInternBook.json");

    @Test
    public void toModelType_invalidCompanyFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternBook dataFromFile = JsonUtil.readJsonFile(INVALID_COMPANY_FILE,
                JsonSerializableInternBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
