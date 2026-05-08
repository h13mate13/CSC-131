import java.util.ArrayList;

public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String category;
    private boolean completed;

    public Task(String title, String description, String dueDate, String priority, String category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public static boolean markComplete(String title, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markCompleted();
                return true;
            }
        }

        return false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Title: " + title
                + ", Description: " + description
                + ", Due Date: " + dueDate
                + ", Priority: " + priority
                + ", Category: " + category
                + ", Completed: " + completed;
    }
}