package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetThresholdCommand;
import seedu.address.model.product.RestockThreshold;

public class SetThresholdCommandParserTest {

    private final SetThresholdCommandParser parser = new SetThresholdCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetThresholdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleTokens_throwsParseException() {
        assertParseFailure(parser, "5 6", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetThresholdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidThreshold_throwsParseException() {
        assertParseFailure(parser, "-1", RestockThreshold.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "a", RestockThreshold.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validThreshold_returnsCommand() {
        assertParseSuccess(parser, "5", new SetThresholdCommand(new RestockThreshold("5")));
        assertParseSuccess(parser, " 0 ", new SetThresholdCommand(new RestockThreshold("0")));
    }
}
