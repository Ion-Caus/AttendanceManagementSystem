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

public class ClassStudentListViewModel {

    private Model model;
    private ViewModelState viewModelState;
    private StringProperty classLabel;
    private StringProperty nameField;
    private StringProperty labelField;
    private ObservableList<StudentViewModel> classStudentTable;
    private StringProperty errorProperty;
    private ObjectProperty<StudentViewModel> selectedStudentProperty;

    ClassStudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;
        classLabel = new SimpleStringProperty(viewModelState.getID());
        nameField = new SimpleStringProperty();
        labelField = new SimpleStringProperty();
        classStudentTable = FXCollections.observableArrayList();
        errorProperty = new SimpleStringProperty();
        selectedStudentProperty = new SimpleObjectProperty<>();

        loadFromModel();
        
    }
    
    private void loadFromModel() {
        clear();
        for(Student student: model.getClassByName("12 C").getStudents().getAllStudents()){
            classStudentTable.add(new StudentViewModel(student));
        }
//        model.getClassByName(viewModelState.getID());
        
    }

    public void clear() {
        classLabel.setValue("");
        nameField.setValue("");
        nameField.setValue("");
        labelField.setValue("");
        errorProperty.setValue("");

    }
    
    public void setSelectedStudent(StudentViewModel selectedStudent) {
        this.selectedStudentProperty.set(selectedStudent);
    }

    public boolean addStudent() {
        try {
            // TODO: 5/17/2021 DENISS fix the add method with the search library
            StudentViewModel studentViewModel = null;
            getStudentList().addStudent(new Student(studentViewModel.nameProperty().get(), studentViewModel.idProperty().get()));
            // TODO: 5/17/2021 replace with observer
            loadFromModel();
            return true;
        }
        catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    private StudentList getStudentList() {
        return model.getClassByName(viewModelState.getID()).getStudents();
    }
    
    public void removeStudent(String id) {
        getStudentList().removeStudent(id);
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


    public StringProperty errorProperty() {
        return errorProperty;
    }
}
