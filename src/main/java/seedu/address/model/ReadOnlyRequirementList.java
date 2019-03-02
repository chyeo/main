package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.requirement.Requirement;

/**
 * Unmodifiable view of planner list
 */
public interface ReadOnlyRequirementList extends Observable {

    /**
     * Returns an unmodifiable view of the degreePlanner list.
     * This list will not contain any duplicate degreePlanner.
     */
    ObservableList<Requirement> getRequirementList();

}