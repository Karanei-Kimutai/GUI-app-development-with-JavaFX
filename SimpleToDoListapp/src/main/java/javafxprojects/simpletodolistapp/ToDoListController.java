package javafxprojects.simpletodolistapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoListController {
    @FXML
    private TextField newTaskfield;
    @FXML
    private ListView<String> todoListview;


    //Observable list to store tasks
    private ObservableList<String> tasks;

    @FXML
    public void initialize(){
        tasks= FXCollections.observableArrayList();
        todoListview.setItems(tasks);

        //Show a task in the textfield when it is selected, can be used for editing
        todoListview.getSelectionModel().selectedItemProperty().addListener((obs,oldTask,newTask)->{
            if (newTask !=null){
                newTaskfield.setText(newTask);
            }
        });
    }

    @FXML
    public void handleAddtask(ActionEvent event){
        String task= newTaskfield.getText().trim();
        if(!task.isEmpty()){
            tasks.add(task);
            newTaskfield.clear();
        }
        else{
            Alert alert= new Alert(Alert.AlertType.WARNING,"Task cannot be empty");
            alert.showAndWait();
        }

    }
    @FXML
    public void handleEdittask(ActionEvent event){
        int selectedIndex= todoListview.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            String updatedTask= newTaskfield.getText().trim();
            if(!updatedTask.isEmpty()){
                tasks.set(selectedIndex,updatedTask);
                newTaskfield.clear();
            }
            else{
                Alert alert= new Alert(Alert.AlertType.WARNING,"Task cannot be empty!");
                alert.showAndWait();
            }
        }
        else{
            Alert alert =new Alert(Alert.AlertType.WARNING, "No task selected for editing!");
            alert.showAndWait();
        }

    }
    @FXML
    public void handleDeletetask(ActionEvent event){
        int selectedIndex=todoListview.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            tasks.remove(selectedIndex);
            newTaskfield.clear();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"No task selected for deletion!");
            alert.showAndWait();
        }

    }

}
