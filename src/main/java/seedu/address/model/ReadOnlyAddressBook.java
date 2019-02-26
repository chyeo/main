package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends Observable {

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

}
