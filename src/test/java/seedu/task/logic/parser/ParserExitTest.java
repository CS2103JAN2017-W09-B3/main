//@@author A0146789H
package seedu.task.logic.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.ExitCommand;
import seedu.task.model.UndoManager;

public class ParserExitTest {
    private Parser parser;
    @SuppressWarnings("unused")
    private UndoManager undomanager;

    // Set up the fixtures
    @Before
    public void setUp() {
        this.parser = new Parser();
        // Initialize the UndoManager as well to create a previous command stack
        this.undomanager = new UndoManager();
    }

    /* Invalid Tests
     *
     */

    /* Valid Tests
     *
     */

    @Test
    public void parser_exitNoArgs_successful() {
        String commandString = "exit";
        Command result = this.parser.parseCommand(commandString);

        assertTrue(result instanceof ExitCommand);
    }

}
