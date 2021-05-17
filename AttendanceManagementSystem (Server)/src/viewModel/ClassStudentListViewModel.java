package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Class;
import model.Model;
import model.Student;
import model.StudentList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.util.ArrayList;

public class ClassStudentListViewModel implements LocalListener<String, String> {

    private Model model;
    private ViewModelState viewModelState;

    private StringProperty className;
    private StringProperty searchField;
    private StringProperty errorProperty;

    private ObservableList<StudentViewModel> classStudentTable;
    private ObjectProperty<StudentViewModel> selectedStudentProperty;

    ClassStudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.model.addListener(this,  "ADD Student Class", "REMOVE Student Class");
        this.viewModelState = viewModelState;

        className = new SimpleStringProperty(viewModelState.getID());
        searchField = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();

        classStudentTable = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

    }
    
    public void loadFromModel() {
        classStudentTable.clear();
        for(Student student: model.getClassByName(viewModelState.getID()).getStudents().getAllStudents()){
            classStudentTable.add(new StudentViewModel(student));
        }
    }

    public void clear() {
        className.setValue("Class " + viewModelState.getID());
        searchField.setValue("");
        errorProperty.setValue("");
    }

    public void addStudent() {
        try {

            //TODO 17/5 by Ion clean up
            String id = (searchField.get().contains("("))? searchField.get().split("[()]")[1] : "no id";
            model.addStudentToClass(id, viewModelState.getID() );
            clear();
        }
        catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
        }
    }

    public void removeStudent(String ID) {
        try {
            model.removeStudentFromClass(ID, viewModelState.getID() );
            clear();
        }
        catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
        }

    }



    public ArrayList<String> getUnassignedStudents() {
        return model.getUnassignedStudents();
    }


    public StudentViewModel getSelectedStudent() {
        return selectedStudentProperty.get();
    }
    
    public void setSelected(StudentViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
    }

    public ObservableList<StudentViewModel> getClassStudentTable() {
        return classStudentTable;
    }

    public StringProperty classProperty() {
        return className;
    }

    public StringProperty searchFieldProperty() {
        return searchField;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    @Override
    public void propertyChange(ObserverEvent<String, String> event) {
        Platform.runLater(() -> {
            if (!viewModelState.getID().equals(event.getValue1())) {
                return;
            }

            String[] commands = event.getPropertyName().split(" ");
            switch (commands[0]) {
                case "ADD":
                    classStudentTable.add(new StudentViewModel(
                            model.getStudentBy(event.getValue2())
                            )
                    );
                    break;
                case "REMOVE":
                    classStudentTable.removeIf(
                            student -> student.idProperty().get().equals(event.getValue2())
                    );
                    break;
            }
        });
    }
}
