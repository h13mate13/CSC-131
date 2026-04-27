import java.util.ArrayList;
import java.util.Scanner;

public class Edit {

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
}