package pwe.planner.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pwe.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static pwe.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static pwe.planner.testutil.TypicalDegreePlanners.getTypicalDegreePlannerList;
import static pwe.planner.testutil.TypicalModules.getTypicalModuleList;
import static pwe.planner.testutil.TypicalRequirementCategories.getTypicalRequirementCategoriesList;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pwe.planner.commons.exceptions.IllegalValueException;
import pwe.planner.logic.CommandHistory;
import pwe.planner.model.Model;
import pwe.planner.model.ModelManager;
import pwe.planner.model.UserPrefs;
import pwe.planner.model.module.Code;
import pwe.planner.model.module.Name;
import pwe.planner.model.requirement.RequirementCategory;
import pwe.planner.storage.JsonSerializableApplication;

public class RequirementRemoveCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();
    private Model model;
    private Set<Code> codeList = new HashSet<>();

    @Before
    public void setUp() throws IllegalValueException {
        model = new ModelManager(
                new JsonSerializableApplication(getTypicalModuleList(), getTypicalDegreePlannerList(),
                        getTypicalRequirementCategoriesList()).toModelType(), new UserPrefs());
    }

    @Test
    public void constructor_nullInputs_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new RequirementRemoveCommand(null);
    }


    @Test
    public void execute_nonExistentCode_throwsCommandException() {
        codeList.clear();
        codeList.add(new Code("CS9999"));
        assertCommandFailure(new RequirementRemoveCommand(codeList), model, commandHistory,
                RequirementRemoveCommand.MESSAGE_NONEXISTENT_CODE);
    }

    @Test
    public void execute_duplicateCode_throwsCommandException() {
        codeList.clear();
        codeList.add(new Code("CS1010"));
        assertCommandFailure(new RequirementRemoveCommand(codeList), model, commandHistory,
                RequirementRemoveCommand.MESSAGE_CODE_NOT_IN_ANY_REQUIREMENT_CATEGORY);
    }

    @Test
    public void execute_removeCodeFromRequirementCategory_success() {
        codeList.clear();
        Name requirementCategoryName = new Name("Computing Foundation");
        RequirementCategory currentRequirementCategory = model.getRequirementCategory(requirementCategoryName);
        RequirementCategory editedRequirementCategory =
                new RequirementCategory(requirementCategoryName, currentRequirementCategory.getCredits(), codeList);

        Model expectedModel = new ModelManager(model.getApplication(), new UserPrefs());
        expectedModel.setRequirementCategory(currentRequirementCategory, editedRequirementCategory);
        expectedModel.commitApplication();

        codeList.add(new Code("CS2100"));
        assertCommandSuccess(new RequirementRemoveCommand(codeList), model, commandHistory,
                RequirementRemoveCommand.MESSAGE_SUCCESS, expectedModel);

        // undo -> reverts application back to previous state
        expectedModel.undoApplication();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> reverts application back to previous state before undo
        expectedModel.redoApplication();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_removeCodesFromRequirementCategories_success() {
        codeList.clear();
        Name requirementCategoryName1 = new Name("Computing Foundation");
        Name requirementCategoryName2 = new Name("Mathematics");
        RequirementCategory currentRequirementCategory1 = model.getRequirementCategory(requirementCategoryName1);
        RequirementCategory currentRequirementCategory2 = model.getRequirementCategory(requirementCategoryName2);
        RequirementCategory editedCurrentReqCat1 =
                new RequirementCategory(requirementCategoryName1, currentRequirementCategory1.getCredits(), codeList);
        RequirementCategory editedCurrentReqCat2 =
                new RequirementCategory(requirementCategoryName2, currentRequirementCategory2.getCredits(), codeList);

        Model expectedModel = new ModelManager(model.getApplication(), new UserPrefs());
        expectedModel.setRequirementCategory(currentRequirementCategory1, editedCurrentReqCat1);
        expectedModel.setRequirementCategory(currentRequirementCategory2, editedCurrentReqCat2);
        expectedModel.commitApplication();

        codeList.add(new Code("CS2100"));
        codeList.add(new Code("CS1231"));
        assertCommandSuccess(new RequirementRemoveCommand(codeList), model, commandHistory,
                RequirementRemoveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_equals() {
        codeList.clear();
        codeList.add(new Code("CS2100"));

        Set<Code> codeListToCompare = new HashSet<>();
        codeListToCompare.add(new Code("CS1010"));

        RequirementRemoveCommand commandBaseline = new RequirementRemoveCommand(codeList);
        RequirementRemoveCommand commandToCompare = new RequirementRemoveCommand(codeListToCompare);

        //same object -> returns true
        assertTrue(commandBaseline.equals(commandBaseline));

        //different objects, same values -> returns true
        RequirementRemoveCommand commandBaselineCopy = new RequirementRemoveCommand(codeList);
        assertTrue(commandBaseline.equals(commandBaselineCopy));

        //different objects -> returns false
        assertFalse(commandBaseline.equals(commandToCompare));

        //different objects -> returns false
        assertFalse(commandBaseline.equals(1));
    }
}
