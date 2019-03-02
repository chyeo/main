package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.requirement.Requirement;

/**
 * The API of the Model component.
 */
public interface RequirementModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Requirement> PREDICATE_SHOW_ALL_REQUIREMENTS = unused -> true;

    /**
     * Returns the RequirementList
     */
    ReadOnlyRequirementList getRequirementList();

    /**
     * Returns true if a requirement with the code as {@code requirement} exists in the requirement list.
     */
    boolean hasRequirement(Requirement requirement);

    /**
     * Deletes the given requirement.
     * The requirement must exist in the address book.
     */
    void deleteRequirement(Requirement target);

    /**
     * Adds the given requirement.
     * {@code requirement} must not already exist in the requirementList.
     */
    void addRequirement(Requirement requirement);

    /**
     * Replaces the given requirement {@code target} with {@code editedRequirement}.
     * {@code target} must exist in the requirement list.
     * The planner identity of {@code editedRequirement} must not be the same as another existing requirement in the
     * requirement list.
     */
    void setRequirement(Requirement target, Requirement editedRequirement);

    /**
     * Returns an unmodifiable view of the filtered planner list
     */
    ObservableList<Requirement> getFilteredRequirementList();

    /**
     * Updates the filter of the filtered requirement list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRequirementList(Predicate<Requirement> predicate);

    /**
     * Returns true if the model has previous requirement list states to restore.
     */
    boolean canUndoRequirementList();

    /**
     * Returns true if the model has undone requirement list states to restore.
     */
    boolean canRedoRequirementList();

    /**
     * Restores the model's requirement list to its previous state.
     */
    void undoRequirementList();

    /**
     * Restores the model's requirement list to its previously undone state.
     */
    void redoRequirementList();
}