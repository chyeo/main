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
import seedu.address.model.ReadOnlyRequirementList;

    /**
     * A class to access RequirementList data stored as a json file on the hard disk.
     */
    public class JsonRequirementListStorage implements RequirementListStorage {

        private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

        private Path filePath;

        public JsonRequirementListStorage(Path filePath) {
            this.filePath = filePath;
        }

        public Path getRequirementListFilePath() {
            return filePath;
        }

        @Override
        public Optional<ReadOnlyRequirementList> readRequirementList() throws DataConversionException {
            return readRequirementList(filePath);
        }

        /**
         * Similar to {@link #readRequirementList()}.
         *
         * @param filePath location of the data. Cannot be null.
         * @throws DataConversionException if the file is not in the correct format.
         */
        public Optional<ReadOnlyRequirementList> readRequirementList(Path filePath) throws DataConversionException {
            requireNonNull(filePath);

            Optional<JsonSerializableRequirementList> jsonRequirementList = JsonUtil.readJsonFile(
                    filePath, JsonSerializableRequirementList.class);
            if (!jsonRequirementList.isPresent()) {
                return Optional.empty();
            }

            try {
                return Optional.of(jsonRequirementList.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }

        @Override
        public void saveRequirementList(ReadOnlyRequirementList requirementList) throws IOException {
            saveRequirementList(requirementList, filePath);
        }

        /**
         * Similar to {@link #saveRequirementList(ReadOnlyRequirementList)}.
         *
         * @param filePath location of the data. Cannot be null.
         */
        public void saveRequirementList(ReadOnlyRequirementList requirementList, Path filePath) throws IOException {
            requireNonNull(requirementList);
            requireNonNull(filePath);

            FileUtil.createIfMissing(filePath);
            JsonUtil.saveJsonFile(new JsonSerializableRequirementList(requirementList), filePath);
        }
}
