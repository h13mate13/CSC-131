
import java.util.Locale;
import java.util.Locale.Category;

public class Task {
    private String title;
    private String description;

    //make this work only for a specific template
    private String dueDate;
    
    //make this into an enum
    private Priority priority;

    //we need to make this an enum
    private Category category;
    private boolean completed;

    public Task(String title, String description, String dueDate, Priority priority, Category category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }

    //right now we are only returning the title so that the users cannot access or search
    // for a task other than looking it up by category or priority?
    public String getTitle() {
        return title;
    }

    //added category and priotirty getter methods
    public Category getCategory(){
        return category;
    }

    public Priority getPriority(){
        return priority;
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static boolean markComplete(String title, java.util.ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return title + " | " + description + " | " + dueDate + " | " + priority + " | " + category +
                (completed ? " | Completed" : " | Not Completed");
    }
}