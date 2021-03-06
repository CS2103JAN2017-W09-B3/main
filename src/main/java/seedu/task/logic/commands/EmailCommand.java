//@@author A0146789H
package seedu.task.logic.commands;

import java.io.IOException;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.UserPrefs;

/**
 * @author amon
 *
 */
public class EmailCommand extends Command {
    public static final String[] COMMAND_WORDS = new String[] {"email", "setemail", "mailto"};
    public static final String DEFACTO_COMMAND = COMMAND_WORDS[0];

    public static final String MESSAGE_SUCCESS = "The new email has been set successfully!";
    public static final String MESSAGE_INCORRECT = "Please specify a valid email!";
    public static final String MESSAGE_USERPREFS_ERROR = "There was an error retrieving/saving the preferences file!";

    private String email;

    public EmailCommand() {
        super(COMMAND_WORDS);
    }

    public EmailCommand(String email) {
        this();
        this.email = email;
    }

    /* (non-Javadoc)
     * @see seedu.task.logic.commands.Command#execute()
     */
    @Override
    public CommandResult execute() throws CommandException {
        try {
            Optional<UserPrefs> optPrefs = storage.readUserPrefs();
            if (!optPrefs.isPresent()) {
                return new CommandResult(MESSAGE_USERPREFS_ERROR);
            }
            UserPrefs prefs = optPrefs.get();
            prefs.setReminderEmail(email);
            storage.saveUserPrefs(prefs);
        } catch (DataConversionException e) {
            return new CommandResult(MESSAGE_USERPREFS_ERROR);
        } catch (IOException e) {
            return new CommandResult(MESSAGE_USERPREFS_ERROR);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static boolean isCommandWord(String command) {
        return isCommandWord(EmailCommand.COMMAND_WORDS, command);
    }
}
