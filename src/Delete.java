import java.util.ArrayList;
import java.util.Scanner;

public class Delete {

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
}