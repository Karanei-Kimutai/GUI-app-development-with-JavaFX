package javafxprojects.bettertodolistapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskManager {
    public static ObservableList<Task> tasks = FXCollections.observableArrayList();

    private static final String FILE_NAME = "tasks.txt";

    /**
     * Load tasks from a text file into the static 'tasks' list.
     * Each line is assumed to have the format:
     *    TaskName|DoneStatus
     * Example:
     *    Buy groceries|false
     *    Finish project|true
     */
    public static void loadTasks() {
        Path filePath = Paths.get(FILE_NAME);
        if (!Files.exists(filePath)) {
            // If the file doesn't exist yet, there's nothing to load
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    boolean done = Boolean.parseBoolean(parts[1].trim());
                    Task task = new Task(name);
                    task.setDone(done);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the current tasks to a text file.
     * Each line is written as:
     *    TaskName|DoneStatus
     */
    public static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.getName() + "|" + task.isDone());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}