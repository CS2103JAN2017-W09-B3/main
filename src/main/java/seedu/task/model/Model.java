package seedu.task.model;

import java.util.Set;

import seedu.task.commons.core.UnmodifiableObservableList;
import seedu.task.model.chat.ChatList;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given task. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws UniqueTaskList.DuplicateTaskException;

    /**
     * Updates the task located at {@code filteredTaskListIndex} with {@code editedTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code filteredTaskListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException;

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();

    //@@author A0139410N
    /** Updates the filter of the filtered task list to show all unchecked tasks */
    void updateFilteredListToShowUnchecked();

    /** Updates the filter of the filtered task list to show all checked tasks */
    void updateFilteredListToShowChecked();

    /** Updates the filter of the filtered task list to show all floating tasks */
    void updateFilteredListToShowFloating();

    /** Updates the filter of the filtered task list to show all deadline tasks */
    void updateFilteredListToShowDeadline();

    /** Updates the filter of the filtered task list to show all event tasks */
    void updateFilteredListToShowEvent();

    /** Updates the filter of the filtered task list to show all upcoming tasks */
    void updateFilteredListToShowUpcoming();

    /** Updates the filter of the filtered task list to show all overdue tasks */
    void updateFilteredListToShowOverdue();

    /** Updates the filter of the filtered task list to filter by the given keywords*/
    void updateFilteredTaskList(Set<String> keywords);

    /** Updates the filter of the filtered task list to filter by the given keywords and tags*/
    void updateFilteredTaskList(Set<String> keywords, Set<Tag> tagKeywords);

    //@@author A0139938L
    /** Returns the chat list as an {@code FilteredList<Chat>} */
    ChatList getChatList();
    //@@author
    //@@author A0138664W
    void deleteTaskUndo(ReadOnlyTask target) throws TaskNotFoundException;

    void updateTaskUndo(int filteredTaskListIndex, ReadOnlyTask editedTask) throws DuplicateTaskException;

    void addTaskUndo(Task task) throws DuplicateTaskException;

    UndoManager getUndoManager();

    //@@author
    int getTaskID(Task task);

    //@@author A0139938L
    void changeSaveToLocation(ReadOnlyTaskManager taskManager, String filepath);

    void changeLoadFromLocation(String filepath);
    //@@author

}
