package seedu.task.model.util;

import java.io.IOException;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyTaskManager;
import seedu.task.model.task.Task;
import seedu.task.storage.XmlTaskManagerStorage;

//@@author A0139938L
public class SampleDataUtil {

    private static final String SAMPLE_DATA_FILE_PATH = "./data/sample-data/taskmanager.xml";

    public static Task[] getSampleTasks() throws DataConversionException, IOException {
        ReadOnlyTaskManager sampleTaskManager = getSampleTaskManager();
        Task[] tasks = sampleTaskManager.getTaskArray();
        return tasks;
    }

    public static ReadOnlyTaskManager getSampleTaskManager() throws DataConversionException, IOException {
        XmlTaskManagerStorage sampleStorage = new XmlTaskManagerStorage(SAMPLE_DATA_FILE_PATH);
        ReadOnlyTaskManager taskManager = sampleStorage.getReadOnlyTaskManager(SAMPLE_DATA_FILE_PATH);
        return taskManager;
    }

//    public static ReadOnlyTaskManager getSampleTaskManager() {
//        try {
//            TaskManager sampleTM = new TaskManager();
//            for (Task sampleTask : getSampleTasks()) {
//                sampleTM.addTask(sampleTask);
//            }
//            return sampleTM;
//        } catch (DuplicateTaskException e) {
//            throw new AssertionError("sample data cannot contain duplicate tasks", e);
//        }
//    }
}
//@@author

