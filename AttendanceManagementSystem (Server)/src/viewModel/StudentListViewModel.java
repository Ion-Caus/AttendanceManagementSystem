package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class StudentListViewModel {
    private ObservableList<StudentListViewModel> studentList;
    private ObjectProperty<StudentListViewModel> selectedStudentListProperty;

    private StringProperty studentNameProperty;
    private StringProperty commentProperty;
    private StringProperty absenceProperty;
    private StringProperty motiveProperty;
    private StringProperty classProperty;
    private StringProperty errorProperty;

    private IntegerProperty gradeProperty;

    private Model model;

    public StudentListViewModel(Model model) {
        this.model = model;

        studentList = FXCollections.observableArrayList();
        selectedStudentListProperty = new SimpleObjectProperty<>();

        this.studentNameProperty = new SimpleStringProperty();
        this.commentProperty = new SimpleStringProperty();
        this.absenceProperty = new SimpleStringProperty();
        this.motiveProperty = new SimpleStringProperty();
        this.classProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();

        this.gradeProperty = new SimpleIntegerProperty();
    }

    public ObservableList<StudentListViewModel> getStudentList() {
        return studentList;
    }

    public void setSelected(StudentListViewModel selectedClass) {
        selectedStudentListProperty.set(selectedClass);
    }

    public StringProperty getStudentNameProperty() {
        return studentNameProperty;
    }

    public StringProperty getCommentProperty() {
        return commentProperty;
    }

    public StringProperty getAbsenceProperty() {
        return absenceProperty;
    }

    public StringProperty getMotiveProperty() {
        return motiveProperty;
    }

    public IntegerProperty getGradeProperty() {
        return gradeProperty;
    }

    public StringProperty getClassProperty() {
        return classProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public void back() {
    }

    public void clear() {
        errorProperty.set("");
        selectedStudentListProperty.set(null);
    }

}

