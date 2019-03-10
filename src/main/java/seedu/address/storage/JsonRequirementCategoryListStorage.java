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
 * A class to access RequirementCategoryList data stored as a json file on the hard disk.
 */
public class JsonRequirementCategoryListStorage implements RequirementCategoryListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonRequirementCategoryListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRequirementCategoryListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readRequirementCategoryList() throws DataConversionException {
        return readRequirementCategoryList(filePath);
    }

    /**
     * Similar to {@link #readRequirementCategoryList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readRequirementCategoryList(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRequirementCategoryList> jsonRequirementCategoryList = JsonUtil.readJsonFile(
                filePath, JsonSerializableRequirementCategoryList.class);
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
     * Similar to {@link #saveRequirementCategoryList(ReadOnlyAddressBook)}.
     *
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
