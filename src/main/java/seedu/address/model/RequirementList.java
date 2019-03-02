package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.requirement.Requirement;
import seedu.address.model.requirement.UniqueRequirementList;

/**
 * Wraps all data at the requirement-list level
 * Duplicates are not allowed (by .isSameRequirement comparison)
 */

public class RequirementList implements ReadOnlyRequirementList {

    private final UniqueRequirementList requirements;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    public RequirementList() {
        requirements = new UniqueRequirementList();
    }

    /**
     * @param toBeCopied
     */
    public RequirementList(ReadOnlyRequirementList toBeCopied) {
        requirements = new UniqueRequirementList();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the requirement list with {@code requirements}.
     * {@code requirements} must not contain duplicate requirements.
     */
    public void setRequirements(List<Requirement> requirements) {
        this.requirements.setRequirements(requirements);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRequirementList newData) {
        requireNonNull(newData);
        this.setRequirements(newData.getRequirementList());
    }

    /**
     * Returns true if an requirements with the same identity as {@code requirement} exists in the address book.
     */
    public boolean hasRequirement(Requirement requirement) {
        requireNonNull(requirement);
        return requirements.contains(requirement);
    }

    /**
     * Adds an requirement to the address book.
     * The requirement must not already exist in the requirement list.
     */
    public void addRequirement(Requirement p) {
        requirements.add(p);
    }

    /**
     * Replaces the given requirement {@code target} in the list with {@code editedRequirement}.
     * {@code target} must exist in the requirement list.
     * The identity of {@code editedRequirement} must not be the same as another existing requirement in
     * the requirement list.
     */
    public void setRequirement(Requirement target, Requirement editedRequirement) {
        requireNonNull(editedRequirement);

        requirements.setRequirement(target, editedRequirement);
    }

    /**
     * Removes {@code key} from this {@code RequirementList}.
     * {@code key} must exist in the requirement list.
     */
    public void removeRequirement(Requirement key) {
        requirements.remove(key);
    }

    @Override
    public String toString() {
        return requirements.asUnmodifiableObservableList().size() + " planners";
    }

    public ObservableList<Requirement> getRequirementList() {
        return requirements.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RequirementList// instanceof handles nulls
                && requirements.equals(((RequirementList) other).requirements));
    }

    @Override
    public int hashCode() {
        return requirements.hashCode();
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