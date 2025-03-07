package javafxprojects.bettertodolistapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTasksPageController {

    @FXML
    private ListView<Task> tasksListView;

    @FXML
    private TextField editField;

    @FXML
    public void initialize() {
        // Show the tasks from TaskManager in the ListView
        tasksListView.setItems(TaskManager.tasks);
    }

    @FXML
    private void handleEditTask() {
        Task selected = tasksListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No task selected", "Please select a task to edit.");
            return;
        }
        String newName = editField.getText().trim();
        if (newName.isEmpty()) {
            showAlert("Empty name", "Task name cannot be empty.");
            return;
        }
        selected.setName(newName);
        tasksListView.refresh();  // refresh the list display
        editField.clear();
    }

    @FXML
    private void handleDeleteTask() {
        Task selected = tasksListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No task selected", "Please select a task to delete.");
            return;
        }
        TaskManager.tasks.remove(selected);
        editField.clear();
    }

    @FXML
    private void handleMarkDone() {
        Task selected = tasksListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No task selected", "Please select a task to mark done.");
            return;
        }
        selected.setDone(true);
        tasksListView.refresh();
    }

    @FXML
    private void handleGoBack() throws IOException {
        Parent addTaskRoot = FXMLLoader.load(getClass().getResource("AddTaskPage.fxml"));
        Scene scene = new Scene(addTaskRoot, 600, 400);
        // Load the Add Task page CSS
        scene.getStylesheets().add(getClass().getResource("AddTaskPage.css").toExternalForm());

        Stage currentStage = (Stage) tasksListView.getScene().getWindow();
        currentStage.setScene(scene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}