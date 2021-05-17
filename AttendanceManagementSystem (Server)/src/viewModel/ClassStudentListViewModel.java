package viewModel;

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

import java.util.ArrayList;

public class ClassStudentListViewModel {

    private Model model;
    private ViewModelState viewModelState;

    private StringProperty className;
    private StringProperty searchField;
    private StringProperty errorProperty;

    private ObservableList<StudentViewModel> classStudentTable;
    private ObjectProperty<StudentViewModel> selectedStudentProperty;

    ClassStudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;
        className = new SimpleStringProperty(viewModelState.getID());
        searchField = new SimpleStringProperty();
        classStudentTable = FXCollections.observableArrayList();
        errorProperty = new SimpleStringProperty();
        selectedStudentProperty = new SimpleObjectProperty<>();

    }
    
    private void loadFromModel() {
        classStudentTable.clear();
        for(Student student: model.getClassByName(viewModelState.getID()).getStudents().getAllStudents()){
            classStudentTable.add(new StudentViewModel(student));
        }
    }

    public void clear() {
        className.setValue(viewModelState.getID());
        searchField.setValue("");
        errorProperty.setValue("");

        loadFromModel();
    }

    public void addStudent() {
        try {
            Class theClass = getTheClass();
            Student student = model.getStudentBy(searchField.get().split("[()]")[1]);

            student.setClassName(theClass.getClassName());
            theClass.getStudents().addStudent(student);

            // TODO: 5/17/2021 replace with observer
            clear();
        }
        catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
        }
    }

    private Class getTheClass() {
        return model.getClassByName(viewModelState.getID());
    }

    public ArrayList<String> getUnassignedStudents() {
        return model.getUnassignedStudents();
    }
    
    public void removeStudent(String id) {
        getTheClass().getStudents().removeStudent(id);
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
}
