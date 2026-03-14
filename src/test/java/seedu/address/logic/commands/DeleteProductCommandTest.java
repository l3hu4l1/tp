package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.VendorVault;
import seedu.address.model.product.Product;
import seedu.address.testutil.ProductBuilder;

/**
 * Tests for {@link DeleteProductCommand}.
 */
public class DeleteProductCommandTest {

    /**
     * Tests that deleting a non-existent product throws an exception.
     */
    @Test
    public void execute_invalidProduct_throwsCommandException() {
        ModelManager model = new ModelManager(new VendorVault(), new UserPrefs());

        DeleteProductCommand command = new DeleteProductCommand("INVALID", true);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    /**
     * Tests deleting a valid product.
     */
    @Test
    public void execute_validProduct_success() throws Exception {
        ModelManager model = new ModelManager(new VendorVault(), new UserPrefs());

        Product product = new ProductBuilder().build();
        model.addProduct(product);

        DeleteProductCommand command =
                new DeleteProductCommand(product.getIdentifier().toString(), false);

        command.execute(model);

        assertEquals(0, model.getFilteredProductList().size());
    }

    /**
     * Tests equality logic.
     */
    @Test
    public void equals() {
        DeleteProductCommand firstCommand = new DeleteProductCommand("P001", true);
        DeleteProductCommand secondCommand = new DeleteProductCommand("P001", true);

        assertEquals(firstCommand, firstCommand);
        assertEquals(firstCommand, secondCommand);
    }
}
