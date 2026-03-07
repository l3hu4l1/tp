package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.model.Model;

/**
 * Confirms the previous question.
 */
public class CancelCommand extends Command {

    public static final String COMMAND_WORD = "y";

    public static final String MESSAGE_SUCCESS = "failure";

    private final Supplier<Optional<CommandResult>> onCancel;

    public CancelCommand(Supplier<Optional<CommandResult>> onCancel) {
        this.onCancel = onCancel;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return onCancel.get().orElse(new CommandResult(MESSAGE_SUCCESS));
    }

    @Override
    public PendingConfirmation getPendingConfirmation() {
        return new PendingConfirmation();
    }
}
