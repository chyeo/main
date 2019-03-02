package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.planner.DegreePlanner;
import seedu.address.model.planner.UniqueDegreePlannerList;

/**
 * Wraps all data at the planner-list level
 * Duplicates are not allowed (by .isSamePlanner comparison)
 */

public class DegreePlannerList implements ReadOnlyDegreePlannerList {

    private final UniqueDegreePlannerList planners;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    public DegreePlannerList() {
        planners = new UniqueDegreePlannerList();
    }

    /**
     * @param toBeCopied
     */
    public DegreePlannerList(ReadOnlyDegreePlannerList toBeCopied) {
        planners = new UniqueDegreePlannerList();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the planner list with {@code degreePlanners}.
     * {@code degreePlanners} must not contain duplicate degreePlanners.
     */
    public void setPlanners(List<DegreePlanner> degreePlanners) {
        this.planners.setPlanners(degreePlanners);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyDegreePlannerList newData) {
        requireNonNull(newData);
        this.setPlanners(newData.getPlannerList());
    }

    //// planner-level operations

    /**
     * Returns true if an degreePlanner with the same identity as {@code degreePlanner} exists in the address book.
     */
    public boolean hasPlanner(DegreePlanner degreePlanner) {
        requireNonNull(degreePlanner);
        return planners.contains(degreePlanner);
    }

    /**
     * Adds an planner to the address book.
     * The planner must not already exist in the planner list.
     */
    public void addPlanner(DegreePlanner p) {
        planners.add(p);
    }

    /**
     * Replaces the given planner {@code target} in the list with {@code editedDegreePlanner}.
     * {@code target} must exist in the planner list.
     * The identity of {@code editedDegreePlanner} must not be the same as another existing planner in the planner list.
     */
    public void setPlanner(DegreePlanner target, DegreePlanner editedDegreePlanner) {
        requireNonNull(editedDegreePlanner);

        planners.setPlanner(target, editedDegreePlanner);
    }

    /**
     * Removes {@code key} from this {@code DegreePlannerList}.
     * {@code key} must exist in the planner list.
     */
    public void removePlanner(DegreePlanner key) {
        planners.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return planners.asUnmodifiableObservableList().size() + " planners";
    }

    public ObservableList<DegreePlanner> getPlannerList() {
        return planners.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DegreePlannerList// instanceof handles nulls
                && planners.equals(((DegreePlannerList) other).planners));
    }

    @Override
    public int hashCode() {
        return planners.hashCode();
    }

    @Override public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }
}
