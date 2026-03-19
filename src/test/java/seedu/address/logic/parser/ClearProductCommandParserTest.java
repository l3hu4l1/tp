package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearProductCommand;

public class ClearProductCommandParserTest {

    private final ClearProductCommandParser parser = new ClearProductCommandParser();

    @Test
    public void parse_validArgs_returnsCommand() throws Exception {
        ClearProductCommand command = parser.parse("");
        assertNotNull(command);
    }

}
