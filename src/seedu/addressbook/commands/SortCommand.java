package seedu.addressbook.commands;

/**
 * Clears the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = "Sort address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    public SortCommand() {}


    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
