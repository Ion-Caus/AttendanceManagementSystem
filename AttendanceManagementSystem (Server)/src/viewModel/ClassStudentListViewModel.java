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
    private StringProperty errorLabel;
    private ObjectProperty<StudentViewModel> selectedStudent;

    ClassStudentListViewModel(Model model, ViewModelState viewModelState){
        this.model = model;
        this.viewModelState = viewModelState;
        classLabel = new SimpleStringProperty(viewModelState.getID());
        nameField = new SimpleStringProperty();
        labelField = new SimpleStringProperty();
        classStudentTable = FXCollections.observableArrayList();
        errorLabel = new SimpleStringProperty();
        selectedStudent = new SimpleObjectProperty<>();

        //loadFromModel();




    }


    private void loadFromModel(){
        clear();
        model.getClassByName(viewModelState.getID());
        

    }

    public void clear(){
        classLabel.setValue("");
        nameField.setValue("");
        nameField.setValue("");
        labelField.setValue("");
        errorLabel.setValue("");

    }



    public void setSelectedStudent(StudentViewModel selectedStudent) {
        this.selectedStudent.set(selectedStudent);
    }

    public void addStudent() {

        StudentViewModel studentViewModel = selectedStudent.get();
        StudentList studentList = getStudentList();
        studentList.addStudent(new Student(studentViewModel.nameProperty().get(),studentViewModel.idProperty().get()));


    }

    private StudentList getStudentList() {
        return model.getClassByName(viewModelState.getID()).getStudents();
    }


    public void removeStudent(String id) {
         getStudentList().removeStudent(id);

    }

    public StudentViewModel getSelectedStudent() {
        return selectedStudent.get();
    }


}
