package seedu.address.model.requirement;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.module.Credits;
import seedu.address.model.module.Name;

/**
 * Represents a Requirement in the requirement list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Requirement {

    // Identity fields
    private final Name name;

    // Data fields
    private final Credits credits;
    private final Set<ModuleList> moduleList = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Requirement(Name name, Credits credits, Set<ModuleList> moduleList) {
        requireAllNonNull(name, credits, moduleList);
        this.name = name;
        this.credits = credits;
        this.moduleList.addAll(moduleList);
    }

    public Name getName() {
        return name;
    }

    public Credits getCredits() {
        return credits;
    }

    /**
     * Returns an immutable moduleList set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleList> getModuleList() {
        return Collections.unmodifiableSet(moduleList);
    }

    /**
     * Returns true if both requirement in PWE have same code.
     * This defines a weaker notion of equality between two requirement.
     */
    public boolean isSameRequirement(Requirement otherRequirement) {
        if (otherRequirement == this) {
            return true;
        }

        return otherRequirement != null
                && otherRequirement.getName().equals(getName());
    }

    /**
     * Returns true if both requirement in PWE have the same identity and data fields.
     * This defines a stronger notion of equality between two requirement.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Requirement)) {
            return false;
        }

        Requirement otherRequirement = (Requirement) other;
        return otherRequirement.getName().equals(getName())
                && otherRequirement.getCredits().equals(getCredits())
                && otherRequirement.getModuleList().equals(getModuleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, credits, moduleList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getName())
                .append(" Credits: ")
                .append(getCredits())
                .append(" Module List: ");
        getModuleList().forEach(builder::append);
        return builder.toString();
    }

}
