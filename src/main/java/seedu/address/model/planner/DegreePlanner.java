package seedu.address.model.planner;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.Code;

/**
 * Represents a DegreePlanner in the degreePlanner list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DegreePlanner {

    // Identity fields
    private final Code code;

    // Data fields
    private final DegreePlannerYear year;
    private final DegreePlannerSemester semester;

    /**
     * Every field must be present and not null.
     */
    public DegreePlanner(Code code, DegreePlannerYear year, DegreePlannerSemester semester) {
        requireAllNonNull(code, year, semester);
        this.code = code;
        this.year = year;
        this.semester = semester;
    }

    public Code getCode() {
        return code;
    }

    public DegreePlannerYear getYear() {
        return year;
    }

    public DegreePlannerSemester getSemester() {
        return semester;
    }

    /**
     * Returns true if both modules in planner have same code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameDegreePlanner(DegreePlanner otherDegreePlanner) {
        if (otherDegreePlanner == this) {
            return true;
        }

        return otherDegreePlanner != null
                && otherDegreePlanner.getCode().equals(getCode());
    }

    /**
     * Returns true if both modules in planner have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DegreePlanner)) {
            return false;
        }

        DegreePlanner otherDegreePlanner = (DegreePlanner) other;
        return otherDegreePlanner.getCode().equals(getCode())
                && otherDegreePlanner.getYear().equals(getYear())
                && otherDegreePlanner.getSemester().equals(getSemester());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, year, semester);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCode())
                .append(" Year: ")
                .append(getYear())
                .append(" Semester: ")
                .append(getSemester());
        return builder.toString();
    }

}
