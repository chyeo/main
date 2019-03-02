package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRequirementList;

    /**
     * Represents a storage for {@link seedu.address.model.RequirementList}.
     */
    public interface RequirementListStorage {
        /**
         * Returns the file path of the data file.
         */
        Path getRequirementListFilePath();

        /**
         * Returns RequirementList data as a {@link ReadOnlyRequirementList}.
         * Returns {@code Optional.empty()} if storage file is not found.
         *
         * @throws DataConversionException if the data in storage is not in the expected format.
         * @throws IOException             if there was any problem when reading from the storage.
         */
        Optional<ReadOnlyRequirementList> readRequirementList() throws DataConversionException, IOException;

        /**
         * @see #getRequirementListFilePath() ()
         */
        Optional<ReadOnlyRequirementList> readRequirementList(Path filePath)
                throws DataConversionException, IOException;

        /**
         * Saves the given {@link ReadOnlyRequirementList} to the storage.
         *
         * @param requirementList cannot be null.
         * @throws IOException if there was any problem writing to the file.
         */
        void saveRequirementList(ReadOnlyRequirementList requirementList) throws IOException;

        /**
         * @see #saveRequirementList(ReadOnlyRequirementList)
         */
        void saveRequirementList(ReadOnlyRequirementList addressBook, Path filePath) throws IOException;

}
