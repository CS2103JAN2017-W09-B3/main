//@@author A0138664W
package seedu.task.logic.commands;

import java.util.List;
import java.util.Set;

import seedu.task.commons.core.Messages;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.tag.Tag;
import seedu.task.model.tag.UniqueTagList;
import seedu.task.model.tag.UniqueTagList.DuplicateTagException;
import seedu.task.model.task.CompletionStatus;
import seedu.task.model.task.EndTime;
import seedu.task.model.task.Name;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.StartTime;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;

public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to existing task.\n"
            + "Parameters: INDEX [#tag]\n"
            + "Example: " + COMMAND_WORD
            + " 1 #CS2103 #uni";
    public static final String ADD_TAG_SUCCESS = "Added new tags into task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager.";
    public static final String MESSAGE_DUPLICATE_TAGS = "This tag already exists in the task.";


    private int filteredTaskListIndex;
    private Set<String> tags;

    public AddTagCommand () {}

    public AddTagCommand(int filteredTaskListIndex, Set<String> tags) throws IllegalValueException {
        assert tags != null;
        assert filteredTaskListIndex > 0;
        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);

        Task editedTask = null;

        try {
            editedTask = createTaskAfterAddedTags(taskToEdit, tags);
        } catch (DuplicateTagException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TAGS);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        try {
            model.updateTask(filteredTaskListIndex, editedTask);
        } catch (UniqueTaskList.DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(ADD_TAG_SUCCESS, editedTask));
    }

    public CommandResult executeUndo(Task previousTask, Task editedTask, Model model) throws CommandException {
        int taskId = model.getTaskID(editedTask);
        try {
            model.updateTaskUndo(taskId, previousTask);
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(UndoCommand.MESSAGE_UNDO_SUCCESS_EDIT, previousTask));
    }

    public CommandResult executeRedo(Task previousTask, Task editedTask, Model model) throws CommandException {
        int taskID = model.getTaskID(editedTask);
        try {
            model.updateTaskUndo(taskID, previousTask);
        } catch (UniqueTaskList.DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(RedoCommand.MESSAGE_REDO_SUCCESS_EDIT, previousTask));
    }

    private static Task createTaskAfterAddedTags(ReadOnlyTask taskToEdit, Set<String> tags)
           throws DuplicateTagException, IllegalValueException {
        assert taskToEdit != null;

        Name name = taskToEdit.getName();
        StartTime startTime = taskToEdit.getStartTime();
        EndTime endTime = taskToEdit.getEndTime();
        CompletionStatus updatedCompletionStatus = taskToEdit.getCompletionStatus();

        UniqueTagList tagList = taskToEdit.getTags();

        for (String tagName: tags) {
            tagList.add(new Tag(tagName));
        }

        return new Task(name, startTime, endTime, updatedCompletionStatus, tagList);
    }

}
