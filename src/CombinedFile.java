import java.util.ArrayList;
import java.util.Scanner;

public class Delete {
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

import java.util.ArrayList;
import java.util.Scanner;



//the tasks need to be incorporated to not just arrays but text files maybe? lets work on that later
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

public class Task {
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
}

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
