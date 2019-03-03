package seedu.address.model.requirementCategory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.requirementCategory.exceptions.DuplicateRequirementCategoryException;
import seedu.address.model.requirementCategory.exceptions.RequirementCategoryNotFoundException;

/**
 * A list of requirement that enforces uniqueness between its elements and does not allow nulls.
 * A code is considered unique by comparing using {@code RequirementCategory#isSameRequirementCategory
 * (RequirementCategory)}. As such,
 * adding and updating of requirementCategory uses RequirementCategory#isSameRequirementCategory(RequirementCategory)
 * for equality so as to
 * ensure that the requirementCategory being added or updated is unique in terms of identity in the
 * UniqueRequirementCategoryList.
 * However, the removal of a requirementCategory uses RequirementCategory#equals(Object) so as to ensure that the
 * requirementCategory with
 * exactly
 * the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see RequirementCategory#isSameRequirementCategory(RequirementCategory)
 */
public class UniqueRequirementCategoryList implements Iterable<RequirementCategory> {

    private final ObservableList<RequirementCategory> internalList = FXCollections.observableArrayList();
    private final ObservableList<RequirementCategory> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent requirementCategory as the given argument.
     */
    public boolean contains(RequirementCategory toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRequirementCategory);
    }

    /**
     * Adds a requirementCategory to the list.
     * The requirementCategory must not already exist in the list.
     */
    public void add(RequirementCategory toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRequirementCategoryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the requirementCategory {@code target} in the list with {@code editedRequirementCategory}.
     * {@code target} must exist in the list.
     * The requirementCategory identity of {@code editedRequirementCategory} must not be the same as another existing
     * requirementCategory
     * in the list.
     */
    public void setRequirementCategory(RequirementCategory target, RequirementCategory editedRequirementCategory) {
        requireAllNonNull(target, editedRequirementCategory);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RequirementCategoryNotFoundException();
        }

        if (!target.isSameRequirementCategory(editedRequirementCategory) && contains(editedRequirementCategory)) {
            throw new DuplicateRequirementCategoryException();
        }

        internalList.set(index, editedRequirementCategory);
    }

    /**
     * Removes the equivalent requirementCategory from the list.
     * The requirementCategory module must exist in the list.
     */
    public void remove(RequirementCategory toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RequirementCategoryNotFoundException();
        }
    }

    public void setRequirementCategories(UniqueRequirementCategoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code requirementCategory}.
     * {@code requirementCategory} must not contain duplicate requirementCategory.
     */
    public void setRequirementCategories(List<RequirementCategory> requirementCategories) {
        requireAllNonNull(requirementCategories);
        if (!requirementCategoriesAreUnique(requirementCategories)) {
            throw new DuplicateRequirementCategoryException();
        }

        internalList.setAll(requirementCategories);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<RequirementCategory> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<RequirementCategory> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRequirementCategoryList // instanceof handles nulls
                && internalList.equals(((UniqueRequirementCategoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code requirementCategory} contains only unique requirementCategory modules.
     */
    private boolean requirementCategoriesAreUnique(List<RequirementCategory> requirementCategories) {
        for (int i = 0; i < requirementCategories.size() - 1; i++) {
            for (int j = i + 1; j < requirementCategories.size(); j++) {
                if (requirementCategories.get(i).isSameRequirementCategory(requirementCategories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
