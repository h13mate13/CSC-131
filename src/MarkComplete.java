package myPackage;

public class MarkComplete {

	public static String[] markComplete(String[] tasks, String target) {

		// Step 2: Linear search
		int index = -1;
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i].equals(target)) {
				index = i;
				break;
			}
		}

		// If not found, return null
		if (index == -1) {
			return null;
		}

		// Step 3: append "(completed)"
		tasks[index] = tasks[index] + " (completed)";

		// Step 4: Return updated array
		return tasks;
	}

	// Example usage
	public static void main(String[] args) {
		String[] tasks = { "Laundry", "Homework", "Dishes" };

		String[] result = markComplete(tasks, "Homework");

		if (result != null) {
			for (String task : result) {
				System.out.println(task);
			}
		} else {
			System.out.println("Task not found.");
		}
	}
}
