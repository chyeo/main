package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.module.Code;
import seedu.address.model.planner.DegreePlanner;
import seedu.address.model.planner.DegreePlannerSemester;
import seedu.address.model.planner.DegreePlannerYear;

/**
 * Jackson-friendly version of {@link DegreePlanner}.
 */
public class JsonAdaptedDegreePlannerList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "degreePlanner's %s field is missing!";

    private final String code;
    private final String year;
    private final String semester;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedDegreePlannerList(@JsonProperty("code") String code, @JsonProperty("year") String year,
            @JsonProperty("semester") String semester) {
        this.code = code;
        this.year = year;
        this.semester = semester;
    }

    /**
     * Converts a given {@code DegreePlanner} into this class for Jackson use.
     */
    public JsonAdaptedDegreePlannerList(DegreePlanner source) {
        code = source.getCode().value;
        year = source.getYear().plannerYear;
        semester = source.getSemester().plannerSemester;
    }

    /**
     * Converts this Jackson-friendly adapted degreePlanner object into the model's {@code DegreePlanner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted degreePlanner.
     */
    public DegreePlanner toModelType() throws IllegalValueException {
        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (year == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DegreePlannerYear.class.getSimpleName()));
        }
        if (!DegreePlannerYear.isValidYear(year)) {
            throw new IllegalValueException(DegreePlannerYear.MESSAGE_YEAR_CONSTRAINTS);
        }
        final DegreePlannerYear modelYear = new DegreePlannerYear(year);

        if (semester == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DegreePlannerSemester.class.getSimpleName()));
        }
        if (!DegreePlannerSemester.isValidSemester(semester)) {
            throw new IllegalValueException(DegreePlannerSemester.MESSAGE_SEMESTER_CONSTRAINTS);
        }
        final DegreePlannerSemester modelSemester = new DegreePlannerSemester(semester);

        return new DegreePlanner(modelCode, modelYear, modelSemester);
    }
}
