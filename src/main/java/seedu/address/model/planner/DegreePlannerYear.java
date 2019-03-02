package seedu.address.model.planner;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a DegreePlanner's Year in the degreePlanner list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DegreePlannerYear {

    public static final String MESSAGE_YEAR_CONSTRAINTS =
            "Year in an university should only be given in Y (e.g. 1) format and in valid form.";

    /*
     * The date should be valid and in the correct Year format.
     */
    public static final String YEAR_VALIDATION_REGEX =
            "[1-4]{1}";

    public final String plannerYear;

    /**
     * Constructs a {@code DegreePlannerYear}.
     *
     * @param year A valid year.
     */
    public DegreePlannerYear(String Year) {
        requireNonNull(Year);
        checkArgument(isValidYear(Year), MESSAGE_YEAR_CONSTRAINTS);
        plannerYear = Year;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(YEAR_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return plannerYear;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DegreePlannerYear // instanceof handles nulls
                && plannerYear.equals(((DegreePlannerYear) other).plannerYear)); // state check
    }

    @Override
    public int hashCode() {
        return plannerYear.hashCode();
    }
}
