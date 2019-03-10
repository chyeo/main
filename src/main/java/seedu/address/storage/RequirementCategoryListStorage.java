package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.ReadOnlyAddressBook}.
 */
public interface RequirementCategoryListStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getRequirementCategoryListFilePath();

    /**
     * Returns RequirementList data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readRequirementCategoryList() throws DataConversionException, IOException;

    /**
     * @see #getRequirementCategoryListFilePath() ()
     */
    Optional<ReadOnlyAddressBook> readRequirementCategoryList(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     *
     * @param requirementCategoryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRequirementCategoryList(ReadOnlyAddressBook requirementCategoryList) throws IOException;

    /**
     * @see #saveRequirementCategoryList(ReadOnlyAddressBook)
     */
    void saveRequirementCategoryList(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}
