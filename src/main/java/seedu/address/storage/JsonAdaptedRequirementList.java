package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Credits;
import seedu.address.model.module.Name;
import seedu.address.model.requirement.ModuleList;
import seedu.address.model.requirement.Requirement;

/**
 * Jackson-friendly version of {@link Requirement}.
 */
public class JsonAdaptedRequirementList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "requirement's %s field is missing!";

    private final String name;
    private final String credits;
    private final List<JsonAdaptedModuleList> moduleList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given requirement details.
     */
    @JsonCreator
    public JsonAdaptedRequirementList(@JsonProperty("name") String name, @JsonProperty("credits") String credits,
            @JsonProperty("moduleList") List<JsonAdaptedModuleList> moduleList) {
        this.name = name;
        this.credits = credits;
        if (moduleList != null) {
            this.moduleList.addAll(moduleList);
        }
    }

    /**
     * Converts a given {@code Requirement} into this class for Jackson use.
     */
    public JsonAdaptedRequirementList(Requirement source) {
        name = source.getName().fullName;
        credits = source.getCredits().value;
        moduleList.addAll(source.getModuleList().stream()
                .map(JsonAdaptedModuleList::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted requirement object into the model's {@code Requirement} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted requirement.
     */
    public Requirement toModelType() throws IllegalValueException {
        final List<ModuleList> moduleTags = new ArrayList<>();
        for (JsonAdaptedModuleList tag : moduleList) {
            moduleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (credits == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Credits.class.getSimpleName()));
        }
        if (!Credits.isValidCredits(credits)) {
            throw new IllegalValueException(Credits.MESSAGE_CONSTRAINTS);
        }
        final Credits modelCredits = new Credits(credits);

        final Set<ModuleList> modelTags = new HashSet<>(moduleTags);
        return new Requirement(modelName, modelCredits, modelTags);
    }
}
