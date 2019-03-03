package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyRequirementCategoryList;
import seedu.address.model.RequirementCategoryList;
import seedu.address.model.module.Code;
import seedu.address.model.module.Credits;
import seedu.address.model.module.Name;
import seedu.address.model.requirementCategory.RequirementCategory;

/**
 * Wraps all data at the requirementCategory-list level
 * Duplicates are not allowed (by .isSameRequirementCategory comparison)
 */
public class SampleRequirementCategoryUtil {
    public static RequirementCategory[] getSampleRequirementCategories() {
        return new RequirementCategory[] {
            new RequirementCategory(new Name("Computing Foundation"), new Credits("220"),
                    getCodeSet()),
            new RequirementCategory(new Name("Information Security Requirements"), new Credits("224"),
                    getCodeSet()),
            new RequirementCategory(new Name("Information Security Electives"), new Credits("122"),
                    getCodeSet()),
            new RequirementCategory(new Name("Computing Breadth"), new Credits("122"),
                    getCodeSet()),
            new RequirementCategory(new Name("IT Professionalism"), new Credits("228"),
                    getCodeSet()),
            new RequirementCategory(new Name("Mathematics"), new Credits("122"),
                    getCodeSet())
        };
    }

    public static ReadOnlyRequirementCategoryList getSampleRequirementCategoryList() {
        RequirementCategoryList sampleRequirementCategoryList = new RequirementCategoryList();
        for (RequirementCategory sampleRequirementCategory : getSampleRequirementCategories()) {
            sampleRequirementCategoryList.addRequirementCategory(sampleRequirementCategory);
        }
        return sampleRequirementCategoryList;
    }

    /**
     * Returns a Code set containing the list of strings given.
     */
    public static Set<Code> getCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Code::new)
                .collect(Collectors.toSet());
    }
}
