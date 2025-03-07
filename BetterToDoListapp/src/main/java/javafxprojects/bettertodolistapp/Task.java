package javafxprojects.bettertodolistapp;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    private SimpleStringProperty name;
    private SimpleBooleanProperty done;

    public Task(String name) {
        this.name = new SimpleStringProperty(name);
        this.done = new SimpleBooleanProperty(false);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        this.name.set(newName);
    }

    public boolean isDone() {
        return done.get();
    }

    public void setDone(boolean value) {
        done.set(value);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleBooleanProperty doneProperty() {
        return done;
    }

    @Override
    public String toString() {
        // For ListView display; shows the task name plus "(Done)" if marked done
        return isDone() ? getName() + " (Done)" : getName();
    }
}
