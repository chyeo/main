package seedu.address.model.requirement;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.requirement.exceptions.DuplicateRequirementException;
import seedu.address.model.requirement.exceptions.RequirementNotFoundException;

/**
 * A list of requirement that enforces uniqueness between its elements and does not allow nulls.
 * A code is considered unique by comparing using {@code Requirement#isSameRequirement(Requirement)}. As such,
 * adding and updating of requirement uses Requirement#isSameRequirement(Requirement) for equality so as to
 * ensure that the requirement being added or updated is unique in terms of identity in the UniqueRequirementList.
 * However, the removal of a requirement uses Requirement#equals(Object) so as to ensure that the requirement with exactly
 * the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Requirement#isSameRequirement(Requirement)
 */
public class UniqueRequirementList implements Iterable<Requirement> {

    private final ObservableList<Requirement> internalList = FXCollections.observableArrayList();
    private final ObservableList<Requirement> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent requirement as the given argument.
     */
    public boolean contains(Requirement toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRequirement);
    }

    /**
     * Adds a requirement to the list.
     * The requirement must not already exist in the list.
     */
    public void add(Requirement toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRequirementException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the requirement {@code target} in the list with {@code editedRequirement}.
     * {@code target} must exist in the list.
     * The requirement identity of {@code editedRequirement} must not be the same as another existing requirement
     * in the list.
     */
    public void setRequirement(Requirement target, Requirement editedRequirement) {
        requireAllNonNull(target, editedRequirement);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RequirementNotFoundException();
        }

        if (!target.isSameRequirement(editedRequirement) && contains(editedRequirement)) {
            throw new DuplicateRequirementException();
        }

        internalList.set(index, editedRequirement);
    }

    /**
     * Removes the equivalent requirement from the list.
     * The requirement module must exist in the list.
     */
    public void remove(Requirement toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RequirementNotFoundException();
        }
    }

    public void setRequirements(UniqueRequirementList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code requirements}.
     * {@code requirements} must not contain duplicate requirements.
     */
    public void setRequirements(List<Requirement> requirements) {
        requireAllNonNull(requirements);
        if (!requirementsAreUnique(requirements)) {
            throw new DuplicateRequirementException();
        }

        internalList.setAll(requirements);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Requirement> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Requirement> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRequirementList // instanceof handles nulls
                && internalList.equals(((UniqueRequirementList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code requirements} contains only unique requirements modules.
     */
    private boolean requirementsAreUnique(List<Requirement> requirements) {
        for (int i = 0; i < requirements.size() - 1; i++) {
            for (int j = i + 1; j < requirements.size(); j++) {
                if (requirements.get(i).isSameRequirement(requirements.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}