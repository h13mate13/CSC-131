import java.util.ArrayList;
import java.util.Scanner;

public class CombinedFile {

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
                CreateTask.createTask(scanner, myTasks);
            } else if (choice.equals("2")) {
                viewTasks(myTasks);
            } else if (choice.equals("3")) {
                Edit.editTaskMenu(scanner, myTasks);
            } else if (choice.equals("4")) {
                Delete.deleteTaskMenu(scanner, myTasks);
            } else if (choice.equals("5")) {
                MarkComplete.markTaskCompleteMenu(scanner, myTasks);
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