package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.requirementCategory.RequirementCategory;

/**
 * Unmodifiable view of requirementCategory list
 */
public interface ReadOnlyRequirementCategoryList extends Observable {

    /**
     * Returns an unmodifiable view of the requirementCategory list.
     * This list will not contain any duplicate requirementCategory.
     */
    ObservableList<RequirementCategory> getRequirementCategoryList();

}
