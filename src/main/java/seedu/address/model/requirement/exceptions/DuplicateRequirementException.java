package seedu.address.model.requirement.exceptions;

/**
 * Signals that the operation will result in duplicate Requirements (Requirements are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRequirementException extends RuntimeException {
    public DuplicateRequirementException() {
        super("Operation would result in duplicate requirement");
    }
}
