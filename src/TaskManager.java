import java.util.ArrayList;

public class TaskManager {
    private static TaskManager instance;
    private final ArrayList<Task> taskList;

    private TaskManager() {
        taskList = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }

        return instance;
    }

    public synchronized void addTask(Task task) {
        taskList.add(task);
    }

    public synchronized ArrayList<Task> getTasks() {
        return new ArrayList<>(taskList);
    }

    public synchronized boolean deleteTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return false;
        }

        taskList.remove(index);
        return true;
    }

    public synchronized boolean editTask(
            int index,
            String title,
            String description,
            String dueDate,
            String priority,
            String category) {

        if (index < 0 || index >= taskList.size()) {
            return false;
        }

        Task task = taskList.get(index);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);
        task.setCategory(category);

        return true;
    }

    public synchronized boolean markTaskComplete(int index) {
        if (index < 0 || index >= taskList.size()) {
            return false;
        }

        taskList.get(index).markCompleted();
        return true;
    }
}