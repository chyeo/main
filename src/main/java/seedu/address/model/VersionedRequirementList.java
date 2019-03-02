package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code RequirementList} that keeps track of its own history.
 */
public class VersionedRequirementList extends RequirementList {

    private final List<ReadOnlyRequirementList> requirementStateList;
    private int currentStatePointer;

    public VersionedRequirementList(ReadOnlyRequirementList initialState) {
        super(initialState);

        requirementStateList = new ArrayList<>();
        requirementStateList.add(new RequirementList(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code RequirementList} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        requirementStateList.add(new RequirementList(this));
        currentStatePointer++;
        indicateModified();
    }

    private void removeStatesAfterCurrentPointer() {
        requirementStateList.subList(currentStatePointer + 1, requirementStateList.size()).clear();
    }

    /**
     * Restores the requirement list to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new VersionedRequirementList.NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(requirementStateList.get(currentStatePointer));
    }

    /**
     * Restores the requirement list to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new VersionedRequirementList.NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(requirementStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has requirement states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has requirement states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < requirementStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedRequirementList)) {
            return false;
        }

        VersionedRequirementList otherVersionedPlannerList = (VersionedRequirementList) other;

        // state check
        return super.equals(otherVersionedPlannerList)
                && requirementStateList.equals(otherVersionedPlannerList.requirementStateList)
                && currentStatePointer == otherVersionedPlannerList.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}