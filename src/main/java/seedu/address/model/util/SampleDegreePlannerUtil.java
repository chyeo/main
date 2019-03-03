package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.DegreePlannerList;
import seedu.address.model.ReadOnlyDegreePlannerList;
import seedu.address.model.module.Code;
import seedu.address.model.planner.DegreePlanner;
import seedu.address.model.planner.Semester;
import seedu.address.model.planner.Year;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDegreePlannerUtil {
    public static DegreePlanner[] getSampleDegreePlanners() {
        return new DegreePlanner[] {
            new DegreePlanner(new Year("1"), new Semester("1"),
                    getCodeSet("CS1010", "CS1231", "MA1101R", "GER1000", "EC1301")),
            new DegreePlanner(new Year("1"), new Semester("2"),
                    getCodeSet("CS2100", "CS2102", "CS2040C", "CS2107", "GEQ1000")),
            new DegreePlanner(new Year("2"), new Semester("1"),
                    getCodeSet("CS2105", "GEH1023", "CS2106", "IS1103", "GET1023")),
            new DegreePlanner(new Year("2"), new Semester("2"),
                    getCodeSet("CS2113T", "CS2101", "ST2334", "MA1521", "CS3235")),
            new DegreePlanner(new Year("3"), new Semester("1"),
                    getCodeSet("CS3233", "IS3103", "CS4236", "CS4238", "CS3210")),
            new DegreePlanner(new Year("3"), new Semester("2"),
                    getCodeSet("CS3243", "CS3244", "GEH1068", "LAK1201", "CS5231")),
            new DegreePlanner(new Year("4"), new Semester("1"),
                    getCodeSet("CS5321", "CS5331", "EC3303", "IFS4205")),
            new DegreePlanner(new Year("4"), new Semester("2"),
                    getCodeSet("CS5332", "IFS4102", "IS4302", "IS4234", "EC3304"))
        };
    }

    public static ReadOnlyDegreePlannerList getSampleDegreePlannerList() {
        DegreePlannerList sampleDegreePlannerList = new DegreePlannerList();
        for (DegreePlanner sampleDegreePlanner : getSampleDegreePlanners()) {
            sampleDegreePlannerList.addDegreePlanner(sampleDegreePlanner);
        }
        return sampleDegreePlannerList;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Code> getCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Code::new)
                .collect(Collectors.toSet());
    }

}
