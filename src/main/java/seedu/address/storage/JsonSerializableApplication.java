package seedu.address.storage;

/**
 * A class to access the JsonSerializable files data stored as a json file on the hard disk.
 */
public class JsonSerializableApplication {

    public Class getJsonSerializableAddressBookClass() {
        return JsonSerializableAddressBook.class;
    }

    public Class getJsonSerializableRequirementCategoryListClass() {
        return JsonSerializableRequirementCategoryList.class;
    }

}
