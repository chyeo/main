package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Code;
import seedu.address.model.module.CodeContainsKeywordsPredicate;
import seedu.address.model.module.Credits;
import seedu.address.model.module.CreditsContainsKeywordsPredicate;
import seedu.address.model.module.KeywordsPredicate;
import seedu.address.model.module.Name;
import seedu.address.model.module.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        KeywordsPredicate predicate = getKeywordsPredicate(args);
        return new FindCommand(predicate);
    }

    private KeywordsPredicate getKeywordsPredicate(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CODE, PREFIX_CREDITS);
        KeywordsPredicate predicate = null;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameArgs = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            List<String> nameKeywords = ParserUtil.parseMultiNames(nameArgs).stream().map(Name::toString)
                    .collect(Collectors.toList());
            predicate = new NameContainsKeywordsPredicate(nameKeywords);
        } else if (argMultimap.getValue(PREFIX_CODE).isPresent()) {
            String[] codeArgs = argMultimap.getValue(PREFIX_CODE).get().split("\\s+");
            List<String> codeKeywords = ParserUtil.parseMultiCodes(codeArgs).stream().map(Code::toString)
                    .collect(Collectors.toList());
            predicate = new CodeContainsKeywordsPredicate(codeKeywords);
        } else if (argMultimap.getValue(PREFIX_CREDITS).isPresent()) {
            String[] creditsArgs = argMultimap.getValue(PREFIX_CREDITS).get().split("\\s+");
            List<String> creditKeywords = ParserUtil.parseMultiCredits(creditsArgs).stream()
                    .map(Credits::toString).collect(Collectors.toList());
            predicate = new CreditsContainsKeywordsPredicate(creditKeywords);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return predicate;
    }
}
