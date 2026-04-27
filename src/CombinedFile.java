import java.util.ArrayList;
import java.util.Scanner;

class Task {
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

public class CombinedFile {

    public static void createTask(Scanner scanner, ArrayList<Task> taskList) {
        System.out.println("\nCreate New Task");

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Due Date: ");
        String dueDate = scanner.nextLine();

        System.out.print("Enter Priority: ");
        String priority = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        Task newTask = new Task(title, description, dueDate, priority, category);
        taskList.add(newTask);

        System.out.println("Success, Task '" + title + "' has been created.");
    }

    public static void viewTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("\nCurrent Tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    public static boolean deleteTask(String title, ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTitle().equalsIgnoreCase(title)) {
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void deleteTaskMenu(Scanner scanner, ArrayList<Task> taskList) {
        System.out.print("What task do you want to delete? ");
        String delTask = scanner.nextLine();

        boolean deleted = deleteTask(delTask, taskList);

        if (deleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public static boolean editTask(
            String oldTitle,
            String newTitle,
            String newDescription,
            String newDueDate,
            String newPriority,
            String newCategory,
            ArrayList<Task> taskList) {

        for (Task task : taskList) {
            if (task.getTitle().equalsIgnoreCase(oldTitle)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setDueDate(newDueDate);
                task.setPriority(newPriority);
                task.setCategory(newCategory);
                return true;
            }
        }
        return false;
    }

    public static void editTaskMenu(Scanner scanner, ArrayList<Task> taskList) {
        System.out.print("What task do you want to edit? ");
        String oldTask = scanner.nextLine();

        System.out.print("What is the new title? ");
        String newTitle = scanner.nextLine();

        System.out.print("What is the new description? ");
        String newDescription = scanner.nextLine();

        System.out.print("What is the new due date? ");
        String newDueDate = scanner.nextLine();

        System.out.print("What is the new priority? ");
        String newPriority = scanner.nextLine();

        System.out.print("What is the new category? ");
        String newCategory = scanner.nextLine();

        boolean edited = editTask(oldTask, newTitle, newDescription, newDueDate, newPriority, newCategory, taskList);

        if (edited) {
            System.out.println("Task edited successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public static void markTaskCompleteMenu(Scanner scanner, ArrayList<Task> taskList) {
        System.out.print("Which task do you want to mark complete? ");
        String target = scanner.nextLine();

        boolean completed = Task.markComplete(target, taskList);

        if (completed) {
            System.out.println("Task marked complete.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> myTasks = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Mark Task Complete");
            System.out.println("6. Quit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                createTask(scanner, myTasks);
            } else if (choice.equals("2")) {
                viewTasks(myTasks);
            } else if (choice.equals("3")) {
                editTaskMenu(scanner, myTasks);
            } else if (choice.equals("4")) {
                deleteTaskMenu(scanner, myTasks);
            } else if (choice.equals("5")) {
                markTaskCompleteMenu(scanner, myTasks);
            } else if (choice.equals("6")) {
                running = false;
                System.out.println("Goodbye.");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}