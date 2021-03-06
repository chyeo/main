package pwe.planner.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pwe.planner.logic.commands.CommandTestUtil.DESC_AMY;
import static pwe.planner.logic.commands.CommandTestUtil.DESC_BOB;
import static pwe.planner.logic.commands.CommandTestUtil.VALID_CODE_BOB;
import static pwe.planner.logic.commands.CommandTestUtil.VALID_CREDITS_BOB;
import static pwe.planner.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static pwe.planner.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.Test;

import pwe.planner.logic.commands.EditCommand.EditModuleDescriptor;
import pwe.planner.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditCommand.EditModuleDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditModuleDescriptor editedAmy = new EditModuleDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different credits -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_AMY).withCredits(VALID_CREDITS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different code -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_AMY).withCode(VALID_CODE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
