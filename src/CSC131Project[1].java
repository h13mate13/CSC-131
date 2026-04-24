import java.util.ArrayList;
import java.util.Scanner;

public class CSC131Project {
	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);

		ArrayList<String> taskList = new ArrayList<>();

		System.out.println("How many tasks to complete?");
		int taskCount = sc1.nextInt();
		sc1.nextLine();

		for (int i = 0; i < taskCount; i++) {
			System.out.println("Task " + (i + 1) + "?");
			String newTask = sc1.nextLine();
			taskList.add(newTask);
		}

		System.out.println("Tasks before delete: " + taskList);

		System.out.println("What task do you want to delete?");
		String delTask = sc1.nextLine();

		deleteTask(delTask, taskList);

		System.out.println("Tasks after delete: " + taskList);

		sc1.close();
	}

	public static void deleteTask(String delTask, ArrayList<String> taskList) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).equalsIgnoreCase(delTask)) {
				taskList.remove(i);
				break;
			}
		}
	}
}