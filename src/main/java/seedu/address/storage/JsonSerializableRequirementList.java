package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRequirementList;
import seedu.address.model.RequirementList;
import seedu.address.model.requirement.Requirement;

/**
 * An Immutable RequirementList that is serializable to JSON format.
 */
@JsonRootName(value = "requirementList")
public class JsonSerializableRequirementList {

    public static final String MESSAGE_DUPLICATE_REQUIREMENT =
            "Requirement list contains duplicate requirements(s).";

    private final List<JsonAdaptedRequirementList> requirements = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRequirementList} with the given requirements.
     */
    @JsonCreator
    public JsonSerializableRequirementList(
            @JsonProperty("requirementList") List<JsonAdaptedRequirementList> requirements) {
        this.requirements.addAll(requirements);
    }

    /**
     * Converts a given {@code ReadOnlyRequirementList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRequirementList}.
     */
    public JsonSerializableRequirementList(ReadOnlyRequirementList source) {
        requirements.addAll(source.getRequirementList().stream().map(JsonAdaptedRequirementList::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this requirement list into the model's {@code RequirementList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RequirementList toModelType() throws IllegalValueException {
        RequirementList requirementList = new RequirementList();
        for (JsonAdaptedRequirementList jsonAdaptedRequirementList : requirements) {
            Requirement requirement = jsonAdaptedRequirementList.toModelType();
            if (requirementList.hasRequirement(requirement)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REQUIREMENT);
            }
            requirementList.addRequirement(requirement);
        }
        return requirementList;
    }
}
