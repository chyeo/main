package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.planner.DegreePlanner;

/**
 * The API of the Model component.
 */
public interface DegreePlannerModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<DegreePlanner> PREDICATE_SHOW_ALL_PLANNERS = unused -> true;

    /**
     * Returns the DegreePlannerList
     */
    ReadOnlyDegreePlannerList getPlannerList();

    /**
     * Returns true if a degreePlanner with the code as {@code degreePlanner} exists in the degreePlanner list.
     */
    boolean hasPlanner(DegreePlanner degreePlanner);

    /**
     * Deletes the given planner.
     * The planner must exist in the address book.
     */
    void deletePlanner(DegreePlanner target);

    /**
     * Adds the given degreePlanner.
     * {@code degreePlanner} must not already exist in the plannerList.
     */
    void addPlanner(DegreePlanner degreePlanner);

    /**
     * Replaces the given planner {@code target} with {@code editedDegreePlanner}.
     * {@code target} must exist in the planner list.
     * The planner identity of {@code editedDegreePlanner} must not be the same as another existing planner in the
     * planner
     * list.
     */
    void setPlanner(DegreePlanner target, DegreePlanner editedDegreePlanner);

    /**
     * Returns an unmodifiable view of the filtered planner list
     */
    ObservableList<DegreePlanner> getFilteredPlannerList();

    /**
     * Updates the filter of the filtered planner list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPlannerList(Predicate<DegreePlanner> predicate);

    /**
     * Returns true if the model has previous planner list states to restore.
     */
    boolean canUndoPlannerList();

    /**
     * Returns true if the model has undone planner list states to restore.
     */
    boolean canRedoPlannerList();

    /**
     * Restores the model's planner list to its previous state.
     */
    void undoPlannerList();

    /**
     * Restores the model's planner list to its previously undone state.
     */
    void redoPlannerList();
}
