import java.util.Scanner;
import java.util.ArrayList;

public class CreateTask{
    public static void createTask(Scanner scanner, ArrayList<Task> taskList){
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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> myTasks = new ArrayList<>();
        createTask(scanner, myTasks);
        if(!myTasks.isEmpty()){
            System.out.println("\nCurrent Tasks in List: " + myTasks.size());
            System.out.println("First Task Title: " + myTasks.get(0).getTitle());
        }
        scanner.close();
    }
}