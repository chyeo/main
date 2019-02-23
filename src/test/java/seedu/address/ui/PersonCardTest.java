package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.module.Module;
import seedu.address.testutil.PersonBuilder;

public class PersonCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Module moduleWithNoTags = new PersonBuilder().withTags(new String[0]).build();
        PersonCard personCard = new PersonCard(moduleWithNoTags, 1);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, moduleWithNoTags, 1);

        // with tags
        Module moduleWithTags = new PersonBuilder().build();
        personCard = new PersonCard(moduleWithTags, 2);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, moduleWithTags, 2);
    }

    @Test
    public void equals() {
        Module module = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(module, 0);

        // same module, same index -> returns true
        PersonCard copy = new PersonCard(module, 0);
        assertTrue(personCard.equals(copy));

        // same object -> returns true
        assertTrue(personCard.equals(personCard));

        // null -> returns false
        assertFalse(personCard.equals(null));

        // different types -> returns false
        assertFalse(personCard.equals(0));

        // different module, same index -> returns false
        Module differentModule = new PersonBuilder().withName("differentName").build();
        assertFalse(personCard.equals(new PersonCard(differentModule, 0)));

        // same module, different index -> returns false
        assertFalse(personCard.equals(new PersonCard(module, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedModule} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(PersonCard personCard, Module expectedModule, int expectedId) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(personCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", personCardHandle.getId());

        // verify module details are displayed correctly
        assertCardDisplaysPerson(expectedModule, personCardHandle);
    }
}
