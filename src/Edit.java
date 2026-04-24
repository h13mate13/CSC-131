import java.util.ArrayList;
import java.util.Scanner;

public class Edit {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<String> taskList = new ArrayList<>();

		System.out.println("How many tasks to complete?");
		int taskCount = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < taskCount; i++) {
			System.out.println("Task " + (i + 1) + "?");
			String newTask = scanner.nextLine();
			taskList.add(newTask);
		}

		System.out.println("Tasks before edit: " + taskList);

		System.out.println("What task do you want to edit?");
		String oldTask = scanner.nextLine();

		System.out.println("What is the new task?");
		String newTask = scanner.nextLine();

		boolean edited = editTask(oldTask, newTask, taskList);

		if (edited) {
			System.out.println("Tasks after edit: " + taskList);
		} else {
			System.out.println("Task not found.");
		}

	}

	public static boolean editTask(String oldTask, String newTask, ArrayList<String> taskList) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).equalsIgnoreCase(oldTask)) {
				taskList.set(i, newTask);
				return true;
			}
		}
		return false;
	}
}