import java.util.ArrayList;
import java.util.Scanner;

public class MarkComplete {
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
}