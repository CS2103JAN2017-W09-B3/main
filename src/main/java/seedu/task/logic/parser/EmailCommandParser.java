//@@author A0146789H
package seedu.task.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.EmailCommand;
import seedu.task.logic.commands.IncorrectCommand;

/**
 * @author amon
 *
 */
public class EmailCommandParser extends AbstractParser {
    public static final String PATTERN_EMAIL_ADDRESS = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    public static final Pattern ARGUMENTS_EMAIL = java.util.regex.Pattern.compile(PATTERN_EMAIL_ADDRESS);

    /* (non-Javadoc)
     * @see seedu.task.logic.parser.AbstractParser#parse(java.lang.String)
     */
    @Override
    public Command parse(String args) {
        String toMatch = args.trim();
        Matcher matcher = ARGUMENTS_EMAIL.matcher(toMatch);
        if (matcher.matches()) {
            return new EmailCommand(matcher.group(1));
        }
        return new IncorrectCommand(EmailCommand.MESSAGE_INCORRECT);
    }

    /* (non-Javadoc)
     * @see seedu.task.logic.parser.AbstractParser#isAcceptedCommand(java.lang.String)
     */
    @Override
    public boolean isAcceptedCommand(String command) {
        return EmailCommand.isCommandWord(command);
    }
}
