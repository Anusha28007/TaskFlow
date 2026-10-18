
import java.io.*;
import java.util.ArrayList;

public class TaskStorage {

    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(
                    task.getName().replace("|", "/") + "|" +
                    task.getPriority() + "|" +
                    task.getCategory() + "|" +
                    task.isCompleted()
                );
            }
        } catch (IOException e) {
            System.out.println("Could not save tasks.");
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length == 4) {
                    Task task = new Task(
                        parts[0],
                        parts[1],
                        parts[2]
                    );

                    task.setCompleted(Boolean.parseBoolean(parts[3]));
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load tasks.");
        }

        return tasks;
    }
}

