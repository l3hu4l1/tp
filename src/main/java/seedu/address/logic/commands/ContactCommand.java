package seedu.address.logic.commands;

public enum ContactCommand implements CommandWord{
    ADD(AddCommand.COMMAND_WORD),
    ARCHIVE(ArchiveCommand.COMMAND_WORD),
    CLEAR(ClearCommand.COMMAND_WORD),
    DELETE(DeleteCommand.COMMAND_WORD),
    EDIT(EditCommand.COMMAND_WORD),
    FIND(FindCommand.COMMAND_WORD),
    LIST(ListCommand.COMMAND_WORD),
    RESTORE(RestoreCommand.COMMAND_WORD);

    private final String commandWord;
    

    ContactCommand(String commandWord) {
        this.commandWord = commandWord;
    }

    @Override
    public String getCommandWord() {
        return commandWord;
    }

}
