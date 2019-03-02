package seedu.address.model.util;

import seedu.address.model.DegreePlannerList;
import seedu.address.model.ReadOnlyDegreePlannerList;
import seedu.address.model.module.Code;
import seedu.address.model.planner.DegreePlanner;
import seedu.address.model.planner.DegreePlannerYear;
import seedu.address.model.planner.DegreePlannerSemester;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDegreePlannerUtil {
    public static DegreePlanner[] getSampleDegreePlanners() {
        return new DegreePlanner[] {
                new DegreePlanner(new Code("CS1010"), new DegreePlannerYear("1"),
                        new DegreePlannerSemester("1")),
                new DegreePlanner(new Code("CS1231"), new DegreePlannerYear("1"),
                        new DegreePlannerSemester("1")),
                new DegreePlanner(new Code("MA1101R"), new DegreePlannerYear("1"),
                        new DegreePlannerSemester("1")),
                new DegreePlanner(new Code("GER1000"), new DegreePlannerYear("1"),
                        new DegreePlannerSemester("1")),
        };
    }

    public static ReadOnlyDegreePlannerList getSampleDegreePlannerList() {
        DegreePlannerList sampleDegreePlannerList = new DegreePlannerList();
        for (DegreePlanner sampleDegreePlanner : getSampleDegreePlanners()) {
            sampleDegreePlannerList.addDegreePlanner(sampleDegreePlanner);
        }
        return sampleDegreePlannerList;
    }
}
