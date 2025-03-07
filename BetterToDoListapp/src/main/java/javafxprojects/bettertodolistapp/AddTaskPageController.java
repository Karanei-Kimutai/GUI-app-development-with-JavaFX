package javafxprojects.bettertodolistapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AddTaskPageController {

    @FXML
    private TextField taskField;

    @FXML
    private void handleAddTask() {
        String enteredtask = taskField.getText().trim();
        if (!enteredtask.isEmpty()) {
            // Add a new Task to the shared list
            TaskManager.tasks.add(new Task(enteredtask));
            taskField.clear();
        }
    }

    @FXML
    private void handleViewTasks() throws IOException {
        // Load the "ViewTasksPage.fxml"
        Parent viewTasksRoot = FXMLLoader.load(getClass().getResource("ViewTasksPage.fxml"));
        Scene scene = new Scene(viewTasksRoot, 600, 400);

        // Load the CSS for the View Tasks page
        scene.getStylesheets().add(getClass().getResource("ViewTasksPage.css").toExternalForm());

        // Get the current stage and switch scenes
        Stage currentStage = (Stage) taskField.getScene().getWindow();
        currentStage.setScene(scene);
    }
}