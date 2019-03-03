package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.requirementCategory.RequirementCategory;

/**
 * The API of the Model component.
 */
public interface RequirementCategoryModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<RequirementCategory> PREDICATE_SHOW_ALL_REQUIREMENTCATEGORY = unused -> true;

    /**
     * Returns the RequirementCategoryList
     */
    ReadOnlyRequirementCategoryList getRequirementCategoryList();

    /**
     * Returns true if a requirementCategory with the code as {@code requirementCategory} exists in the
     * requirementCategory list.
     */
    boolean hasRequirementCategory(RequirementCategory requirementCategory);

    /**
     * Deletes the given requirementCategory.
     * The requirementCategory must exist in the address book.
     */
    void deleteRequirementCategory(RequirementCategory target);

    /**
     * Adds the given requirementCategory.
     * {@code requirementCategory} must not already exist in the requirementCategory.
     */
    void addRequirementCategory(RequirementCategory requirementCategory);

    /**
     * Replaces the given requirementCategory {@code target} with {@code editedRequirementCategory}.
     * {@code target} must exist in the requirementCategory list.
     * The planner identity of {@code editedRequirementCategory} must not be the same as another existing
     * requirementCategory in the
     * requirementCategoryList.
     */
    void setRequirementCategory(RequirementCategory target, RequirementCategory editedRequirementCategory);

    /**
     * Returns an unmodifiable view of the filtered requirementCategory list
     */
    ObservableList<RequirementCategory> getFilteredRequirementCategoryList();

    /**
     * Updates the filter of the filtered requirementCategory list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRequirementCategoryList(Predicate<RequirementCategory> predicate);

    /**
     * Returns true if the model has previous requirementCategory list states to restore.
     */
    boolean canUndoRequirementCategoryList();

    /**
     * Returns true if the model has undone requirementCategory list states to restore.
     */
    boolean canRedoRequirementCategoryList();

    /**
     * Restores the model's requirementCategory list to its previous state.
     */
    void undoRequirementCategoryList();

    /**
     * Restores the model's requirementCategory list to its previously undone state.
     */
    void redoRequirementCategoryList();

    /**
     * Saves the current requirementCategory list state for undo/redo.
     */
    void commitRequirementCategoryList();
}
