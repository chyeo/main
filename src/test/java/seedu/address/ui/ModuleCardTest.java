package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.module.Module;
import seedu.address.testutil.PersonBuilder;

public class ModuleCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Module moduleWithNoTags = new PersonBuilder().withTags(new String[0]).build();
        ModuleCard moduleCard = new ModuleCard(moduleWithNoTags, 1);
        uiPartRule.setUiPart(moduleCard);
        assertCardDisplay(moduleCard, moduleWithNoTags, 1);

        // with tags
        Module moduleWithTags = new PersonBuilder().build();
        moduleCard = new ModuleCard(moduleWithTags, 2);
        uiPartRule.setUiPart(moduleCard);
        assertCardDisplay(moduleCard, moduleWithTags, 2);
    }

    @Test
    public void equals() {
        Module module = new PersonBuilder().build();
        ModuleCard moduleCard = new ModuleCard(module, 0);

        // same module, same index -> returns true
        ModuleCard copy = new ModuleCard(module, 0);
        assertTrue(moduleCard.equals(copy));

        // same object -> returns true
        assertTrue(moduleCard.equals(moduleCard));

        // null -> returns false
        assertFalse(moduleCard.equals(null));

        // different types -> returns false
        assertFalse(moduleCard.equals(0));

        // different module, same index -> returns false
        Module differentModule = new PersonBuilder().withName("differentName").build();
        assertFalse(moduleCard.equals(new ModuleCard(differentModule, 0)));

        // same module, different index -> returns false
        assertFalse(moduleCard.equals(new ModuleCard(module, 1)));
    }

    /**
     * Asserts that {@code moduleCard} displays the details of {@code expectedModule} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ModuleCard moduleCard, Module expectedModule, int expectedId) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(moduleCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", personCardHandle.getId());

        // verify module details are displayed correctly
        assertCardDisplaysPerson(expectedModule, personCardHandle);
    }
}
