
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GradeProgress;


/**
 * Parses input arguments and creates a new GradeProgressCommand object
 */
public class GradeProgressCommandParser implements Parser<GradeProgressCommand> {

    public static final String MESSAGE_INVALID_EMPTY_FIELD = "GRADEPROGRESS cannot be empty.";

    /**
     * Parses the given {@code String} of arguments in the context of the GradeProgressCommand
     * and returns a GradeProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeProgressCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_GRADE_PROGRESS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeProgressCommand.MESSAGE_USAGE), ive);
        }

        Optional<String> optGrade = argMultimap.getValue(PREFIX_GRADE_PROGRESS);
        // if no prefix g/ or field after g/ is empty, throw error
        if (optGrade.isEmpty() || optGrade.get().equals("")) {
            throw new ParseException(MESSAGE_INVALID_EMPTY_FIELD);
        }

        String gradeProgress = optGrade.get();

        return new GradeProgressCommand(index, new GradeProgress(gradeProgress));
    }
}
