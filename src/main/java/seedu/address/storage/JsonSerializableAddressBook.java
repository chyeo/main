package seedu.address.storage;

/**
 * A class to access the JsonSerializable files data stored as a json file on the hard disk.
 */
public class JsonSerializableAddressBook {

    public Class getJsonSerializableAddressBookClass() {
        return JsonSerializableModuleList.class;
    }

    public Class getJsonSerializableRequirementCategoryListClass() {
        return JsonSerializableRequirementCategoryList.class;
    }

}
