package seedu.address.logic.parser;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;

/**
 * Parses input arguments and creates a new AliasCommand Object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    public static final String MESSAGE_ORIGINAL_COMMAND_DOES_NOT_EXISTS =
            "The original command does not exists.\n"
            + "For the list of commands visit the User Guide.";

    public static final String MESSAGE_ALIAS_CONTAINS_SPACE =
            "The alias should not contain any spaces.";

    public static final String MESSAGE_FORMATTED_WRONGLY =
            "Message is formatted wrongly.";

    public static final String MESSAGE_ALIAS_IS_A_PREDEFINED_COMMAND =
            "Alias is a predefined command, please choose another alias.";
    /**
     * Parses the given {@code String} of arguments and transform it to a new Alias object.
     * The new alias object will be parsed to AliasCommand object for execution
     *
     */
    public AliasCommand parse(String args) throws ParseException {
        String argsTrimmed = args.trim();

        String[] tokens = argsTrimmed.split(" ", 2);
        System.out.println(args);
        System.out.println(tokens.length);
        if (tokens.length <= 1) {
            throw new ParseException(MESSAGE_FORMATTED_WRONGLY);
        }

        String originalCommand = tokens[0];
        String newAlias = tokens[1];

        if (!CommandType.isValidCommand(originalCommand)) {
            throw new ParseException(MESSAGE_ORIGINAL_COMMAND_DOES_NOT_EXISTS);
        }

        if (newAlias.contains(" ")) {
            throw new ParseException(MESSAGE_ALIAS_CONTAINS_SPACE);
        }

        if (CommandType.isValidCommand(newAlias)) {
            throw new ParseException(MESSAGE_ALIAS_IS_A_PREDEFINED_COMMAND);
        }

        Alias alias = new Alias(newAlias, originalCommand);
        return new AliasCommand(alias);
    }
}
