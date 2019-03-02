package seedu.address.model.requirement;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ModuleList in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleName(String)}
 */
public class ModuleList {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String modName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param moduleName A valid module name.
     */
    public ModuleList(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.modName = moduleName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.requirement.ModuleList // instanceof handles nulls
                && modName.equals(((seedu.address.model.requirement.ModuleList) other).modName)); // state check
    }

    @Override
    public int hashCode() {
        return modName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + modName + ']';
    }

}
