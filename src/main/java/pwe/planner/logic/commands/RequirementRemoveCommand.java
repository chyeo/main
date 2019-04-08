package pwe.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static pwe.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static pwe.planner.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import pwe.planner.logic.CommandHistory;
import pwe.planner.logic.commands.exceptions.CommandException;
import pwe.planner.model.Model;
import pwe.planner.model.module.Code;
import pwe.planner.model.requirement.RequirementCategory;

/**
 * Removes a module from a requirement category.
 */
public class RequirementRemoveCommand extends Command {

    public static final String COMMAND_WORD = "requirement_remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a module from a requirement category.\n"
            + "Parameters: "
            + PREFIX_CODE + "CODE "
            + "[" + PREFIX_CODE + "CODE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + "IS4231 ";

    public static final String MESSAGE_SUCCESS = "Module successfully removed!";
    public static final String MESSAGE_NONEXISTENT_CODE =
            "The module to be removed from the requirement category does not exists in the module list!";
    public static final String MESSAGE_CODE_NOT_IN_ANY_REQUIREMENT_CATEGORY =
            "The module to be moved does not exist in any requirement category!";

    private final Set<Code> toRemove = new HashSet<>();
    private ObservableList<RequirementCategory> requirementCategories;

    /**
     * Creates an RequirementRemoveCommand to add the specified {@code codeSet}
     */
    public RequirementRemoveCommand(Set<Code> codeSet) {
        requireNonNull(codeSet);
        this.toRemove.addAll(codeSet);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        requirementCategories = model.getApplication().getRequirementCategoryList();

        if (toRemove.stream().anyMatch(code -> !model.hasModuleCode(code))) {
            throw new CommandException(MESSAGE_NONEXISTENT_CODE);
        }

        boolean codeInAnyReqCat = toRemove.stream().allMatch(code -> requirementCategories.stream()
                .anyMatch(reqCat -> reqCat.getCodeSet().contains(code)));

        if (!codeInAnyReqCat) {
            throw new CommandException(MESSAGE_CODE_NOT_IN_ANY_REQUIREMENT_CATEGORY);
        }

        RequirementCategory singleSourceReqCat = requirementCategories.stream().filter(reqCat -> reqCat.getCodeSet()
                .containsAll(toRemove)).findFirst().orElse(null);

        if (singleSourceReqCat != null) {
            Set<Code> newCodeSet = new HashSet<>(singleSourceReqCat.getCodeSet());
            newCodeSet.removeAll(toRemove);

            RequirementCategory editedRequirementCategory = new RequirementCategory(singleSourceReqCat.getName(),
                    singleSourceReqCat.getCredits(), newCodeSet);

            model.setRequirementCategory(singleSourceReqCat, editedRequirementCategory);
        } else {
            for (Code code : toRemove){
                RequirementCategory sourceReqCat = requirementCategories.stream().filter(reqCat -> reqCat.getCodeSet()
                        .contains(code)).findFirst().orElse(null);

                Set<Code> newCodeSet = new HashSet<>(sourceReqCat.getCodeSet());
                newCodeSet.remove(code);

                RequirementCategory editedRequirementCategory = new RequirementCategory(singleSourceReqCat.getName(),
                        singleSourceReqCat.getCredits(), newCodeSet);

                model.setRequirementCategory(singleSourceReqCat, editedRequirementCategory);
            }

        }

        model.commitApplication();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RequirementRemoveCommand // instanceof handles nulls
                && toRemove.equals(((RequirementRemoveCommand) other).toRemove));
    }
}
