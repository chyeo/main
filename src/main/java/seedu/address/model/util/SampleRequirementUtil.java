package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyRequirementList;
import seedu.address.model.RequirementList;
import seedu.address.model.module.Credits;
import seedu.address.model.module.Name;
import seedu.address.model.requirement.ModuleList;
import seedu.address.model.requirement.Requirement;

/**
 * Wraps all data at the requirement-list level
 * Duplicates are not allowed (by .isSameRequirement comparison)
 */
public class SampleRequirementUtil {
    public static Requirement[] getSampleRequirements() {
        return new Requirement[] {
            new Requirement(new Name("Computing Foundation"), new Credits("20"),
                getModuleSet("CS2100")),
            new Requirement(new Name("Information Security Requirements"), new Credits("24"),
                getModuleSet("CS4238")),
            new Requirement(new Name("Information Security Electives"), new Credits("12"),
                getModuleSet("IFS4103")),
            new Requirement(new Name("Computing Breadth"), new Credits("12"),
                getModuleSet("CS1231")),
            new Requirement(new Name("IT Professionalism"), new Credits("8"),
                getModuleSet("IS4231")),
            new Requirement(new Name("Mathematics"), new Credits("12"),
                getModuleSet("CS1231"))
        };
    }

    public static ReadOnlyRequirementList getSampleRequirementList() {
        RequirementList sampleRequirementList = new RequirementList();
        for (Requirement sampleRequirement : getSampleRequirements()) {
            sampleRequirementList.addRequirement(sampleRequirement);
        }
        return sampleRequirementList;
    }

    /**
     * Returns a ModuleList set containing the list of strings given.
     */
    public static Set<ModuleList> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleList::new)
                .collect(Collectors.toSet());
    }
}
