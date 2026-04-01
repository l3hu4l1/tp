package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProducts.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Aliases;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.VendorVault;
import seedu.address.model.person.Person;
import seedu.address.model.product.Product;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditProductDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProductBuilder;

public class UndoCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new VendorVault(getTypicalAddressBook(), getTypicalInventory()),
                new UserPrefs(), new Aliases());
    }

    @Test
    public void execute_noUndoAvailable_failure() {
        assertCommandFailure(new UndoCommand(), model,
                UndoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_afterAdd_undoSuccess() throws CommandException {
        Person person = new PersonBuilder().build();

        new AddCommand(person).execute(model);

        Model expectedModel =
                new ModelManager(new VendorVault(getTypicalAddressBook(), getTypicalInventory()),
                        new UserPrefs(), new Aliases());

        String addActionSummary = String.format(AddCommand.MESSAGE_ACTION_SUMMARY, Messages.format(person));
        String expectedUndoMessage = UndoCommand.MESSAGE_SUCCESS + addActionSummary;

        assertCommandSuccess(
                new UndoCommand(),
                model,
                expectedUndoMessage,
                expectedModel
        );
    }

    @Test
    public void execute_afterAddProduct_undoSuccess() throws CommandException {
        Product product = new ProductBuilder().build();

        new AddProductCommand(product).execute(model);

        Model expectedModel =
                new ModelManager(new VendorVault(getTypicalAddressBook(), getTypicalInventory()),
                        new UserPrefs(), new Aliases());

        String addProductActionSummary = String.format(AddProductCommand.MESSAGE_ACTION_SUMMARY,
                Messages.formatProduct(product));
        String expectedUndoMessage = UndoCommand.MESSAGE_SUCCESS + addProductActionSummary;

        assertCommandSuccess(
                new UndoCommand(),
                model,
                expectedUndoMessage,
                expectedModel
        );
    }

    @Test
    public void execute_afterEdit_undoSuccess() throws CommandException {
        Person personToEdit = model.getFilteredPersonList().get(0);

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone("998")
                .build();

        Person editedPerson = new PersonBuilder(personToEdit)
                .withPhone("998")
                .build();

        new EditCommand(personToEdit.getEmail(), descriptor).execute(model);

        Model expectedModel =
                new ModelManager(new VendorVault(getTypicalAddressBook(), getTypicalInventory()),
                        new UserPrefs(), new Aliases());

        String editActionSummary = String.format(EditCommand.MESSAGE_ACTION_SUMMARY, Messages.format(editedPerson));
        String expectedUndoMessage = UndoCommand.MESSAGE_SUCCESS + editActionSummary;

        assertCommandSuccess(
                new UndoCommand(),
                model,
                expectedUndoMessage,
                expectedModel
        );
    }

    @Test
    public void execute_afterEditProduct_undoSuccess() throws CommandException {
        Product productToEdit = model.getFilteredProductList().get(0);

        EditProductCommand.EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withQuantity("999")
                .build();

        Product editedProduct = new ProductBuilder(productToEdit).withQuantity("999").build();

        new EditProductCommand(productToEdit.getIdentifier().value, descriptor).execute(model);

        Model expectedModel =
                new ModelManager(new VendorVault(getTypicalAddressBook(), getTypicalInventory()),
                        new UserPrefs(), new Aliases());

        String editProductActionSummary = String.format(EditProductCommand.MESSAGE_ACTION_SUMMARY,
                Messages.formatProduct(editedProduct));
        String expectedUndoMessage = UndoCommand.MESSAGE_SUCCESS + editProductActionSummary;

        assertCommandSuccess(
                new UndoCommand(),
                model,
                expectedUndoMessage,
                expectedModel
        );
    }

    @Test
    public void getPendingConfirmation_returnsInactivePendingConfirmation() {
        UndoCommand undoCommand = new UndoCommand();
        PendingConfirmation pendingConfirmation = undoCommand.getPendingConfirmation();
        assertFalse(pendingConfirmation.getNeedConfirmation());
    }
}
