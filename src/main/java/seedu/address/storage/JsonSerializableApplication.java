package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access the JsonSerializable files data stored as a json file on the hard disk.
 */
public class JsonSerializableApplication {

    private static final Logger logger = LogsCenter.getLogger(JsonSerializableApplication.class);

    /**
     * @param filePath location of the data.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * @param filePath location of the data. Creates a new file if specified file is missing
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    /**
     * @param filePath location of the data.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readRequirementCategoryList(Path filePath)
            throws DataConversionException {

        Optional<JsonSerializableAddressBook> jsonRequirementCategoryList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonRequirementCategoryList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRequirementCategoryList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * @param filePath location of the data. Creates a new file if specified file is missing
     */
    public void saveRequirementCategoryList(ReadOnlyAddressBook requirementCategoryList, Path filePath)
            throws IOException {
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRequirementCategoryList(requirementCategoryList), filePath);
    }
}
