package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to call JsonSerializableApplication methods
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private JsonSerializableApplication application = new JsonSerializableApplication();

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    public Path getRequirementCategoryListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        return application.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);
        application.saveAddressBook(addressBook, filePath);
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
        return application.readRequirementCategoryList(filePath);
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
        application.saveRequirementCategoryList(requirementCategoryList, filePath);
    }

}
