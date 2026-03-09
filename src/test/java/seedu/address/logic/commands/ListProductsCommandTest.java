package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ListProductsCommandTest {

    @Test
    public void execute_listProducts_success() {
        Model model = new ModelManager();

        ListProductsCommand command = new ListProductsCommand();

        CommandResult result = command.execute(model);

        assertEquals("Listed all products", result.getFeedbackToUser());
    }
}
