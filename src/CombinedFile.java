
import java.util.ArrayList;
import java.util.Scanner;

class Delete {

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("How many tasks to complete?");
        int taskCount = sc1.nextInt();
        sc1.nextLine();

        for (int i = 0; i < taskCount; i++) {
            System.out.println("Task " + (i + 1) + "?");
            String title = sc1.nextLine();
            Task newTask = new Task(title, "", "", "", "");
            taskList.add(newTask);
        }

        System.out.println("Tasks before delete: " + taskList);

        System.out.println("What task do you want to delete?");
        String delTask = sc1.nextLine();

        boolean deleted = deleteTask(delTask, taskList);

        if (deleted) {
            System.out.println("Tasks after delete: " + taskList);
        } else {
            System.out.println("Task not found.");
        }

        sc1.close();
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
}

class Edit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("How many tasks to complete?");
        int taskCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < taskCount; i++) {
            System.out.println("Task " + (i + 1) + "?");
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter due date: ");
            String dueDate = scanner.nextLine();

            System.out.print("Enter priority: ");
            String priority = scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            Task task = new Task(title, description, dueDate, priority, category);
            taskList.add(task);
        }

        System.out.println("Tasks before edit: " + taskList);

        System.out.println("What task do you want to edit?");
        String oldTask = scanner.nextLine();

        System.out.println("What is the new title?");
        String newTitle = scanner.nextLine();

        System.out.println("What is the new description?");
        String newDescription = scanner.nextLine();

        System.out.println("What is the new due date?");
        String newDueDate = scanner.nextLine();

        System.out.println("What is the new priority?");
        String newPriority = scanner.nextLine();

        System.out.println("What is the new category?");
        String newCategory = scanner.nextLine();

        boolean edited = editTask(oldTask, newTitle, newDescription, newDueDate, newPriority, newCategory, taskList);

        if (edited) {
            System.out.println("Tasks after edit: " + taskList);
        } else {
            System.out.println("Task not found.");
        }

        scanner.close();
    }

    public static boolean editTask(String oldTitle, String newTitle, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getTitle().equalsIgnoreCase(oldTitle)) {
                task.setTitle(newTitle);
                return true;
            }
        }
        return false;
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
}

class MarkComplete {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        taskList.add(new Task("Laundry", "Wash clothes", "Friday", "High", "Home"));
        taskList.add(new Task("Homework", "Math exercises", "Tonight", "High", "School"));
        taskList.add(new Task("Dishes", "Clean kitchen", "Today", "Medium", "Home"));

        System.out.println("Tasks before marking complete: " + taskList);
        System.out.println("Which task do you want to mark complete?");
        String target = scanner.nextLine();

        boolean completed = Task.markComplete(target, taskList);

        if (completed) {
            System.out.println("Tasks after marking complete: " + taskList);
        } else {
            System.out.println("Task not found.");
        }

        scanner.close();
    }
}

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
        this.completed = true;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> myTasks = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                createTask(scanner, myTasks);
            } else if (choice.equals("2")) {
                if (myTasks.isEmpty()) {
                    System.out.println("No tasks in the list.");
                } else {
                    System.out.println("\nCurrent Tasks:");
                    for (Task task : myTasks) {
                        System.out.println(task.getTitle());
                    }
                }
            } else if (choice.equals("3")) {
                running = false;
                System.out.println("Goodbye.");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
