//@@author A0138664W
package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.IncorrectCommand;
import seedu.task.logic.commands.UncheckCommand;
import seedu.task.model.UndoManager;

public class UncheckCommandParser extends AbstractParser {
    @Override
    public Command parse(String args) {

        Optional<Integer> index = ParserUtil.parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UncheckCommand.MESSAGE_USAGE));
        }

        // Add the undo entry after the UncheckCommand is successfully parsed.
        UndoManager.pushUndoCommand(UncheckCommand.DEFACTO_COMMAND);

        return new UncheckCommand(index.get());
    }

    //@@author A0146789H
    @Override
    public boolean isAcceptedCommand(String command) {
        return UncheckCommand.isCommandWord(command);
    }
}
