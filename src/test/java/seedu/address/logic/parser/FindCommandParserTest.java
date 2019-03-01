package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.module.CodeContainsKeywordsPredicate;
import seedu.address.model.module.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "find " + PREFIX_NAME + "Alice Bob", expectedFindNameCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find " + PREFIX_NAME + "  Alice       Bob     ", expectedFindNameCommand);

        FindCommand expectedFindCodeCommand =
                new FindCommand(new CodeContainsKeywordsPredicate(Arrays.asList("CS1231", "GET1004")));
        assertParseSuccess(parser, "find " + PREFIX_CODE + "CS1231 GET1004", expectedFindCodeCommand);
    }

}
