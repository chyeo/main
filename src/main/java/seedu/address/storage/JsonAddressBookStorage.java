package seedu.address.storage;

import static java.util.Objects.requireNonNull;

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
 * A class to call JsonSerializableAddressBook methods
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSerializableAddressBook.class);

    private JsonSerializableAddressBook application = new JsonSerializableAddressBook();

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleListFilePath() {
        return filePath;
    }

    public Path getRequirementCategoryListFilePath() {
        return filePath;
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveModuleList(addressBook, getModuleListFilePath());
        saveRequirementCategoryList(addressBook, getRequirementCategoryListFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readModuleList() throws DataConversionException {
        return readModuleList(filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public Optional<ReadOnlyAddressBook> readModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableModuleList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, application.getJsonSerializableAddressBookClass());
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

    @Override
    public void saveModuleList(ReadOnlyAddressBook addressBook) throws IOException {
        saveModuleList(addressBook, filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModuleList(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(addressBook), filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readRequirementCategoryList() throws DataConversionException {
        return readRequirementCategoryList(filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public Optional<ReadOnlyAddressBook> readRequirementCategoryList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableRequirementCategoryList> jsonRequirementCategoryList = JsonUtil.readJsonFile(
                filePath, application.getJsonSerializableRequirementCategoryListClass());
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

    @Override
    public void saveRequirementCategoryList(ReadOnlyAddressBook requirementCategoryList)
            throws IOException {
        saveRequirementCategoryList(requirementCategoryList, filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRequirementCategoryList(ReadOnlyAddressBook requirementCategoryList, Path filePath)
            throws IOException {
        requireNonNull(requirementCategoryList);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRequirementCategoryList(requirementCategoryList), filePath);
    }

}
