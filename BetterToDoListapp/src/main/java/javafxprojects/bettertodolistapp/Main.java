package javafxprojects.bettertodolistapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TaskManager.loadTasks();
        Parent root = FXMLLoader.load(getClass().getResource("AddTaskPage.fxml"));
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("AddTaskPage.css").toExternalForm());

        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop() {
        TaskManager.saveTasks();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
