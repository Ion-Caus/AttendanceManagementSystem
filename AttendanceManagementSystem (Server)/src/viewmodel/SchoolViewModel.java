package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Class;
import model.Student;
import model.Teacher;

public class SchoolViewModel {
    private ObservableList<ClassViewModel> classList;
    private ObjectProperty<ClassViewModel> selectedClassProperty;

    private ObservableList<StudentViewModel> studentList;
    private ObjectProperty<StudentViewModel> selectedStudentProperty;

    private ObservableList<TeacherViewModel> teacherList;
    private ObjectProperty<TeacherViewModel> selectedTeacherProperty;

    private StringProperty schoolName;
    private StringProperty error;

    private StringProperty scheduleButton;
    private StringProperty studentListButton;

    private StringProperty tabSelectedProperty;

    private Model model;
    private ViewModelState viewModelState;

    public SchoolViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;

        classList = FXCollections.observableArrayList();
        selectedClassProperty = new SimpleObjectProperty<>();

        studentList = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

        teacherList = FXCollections.observableArrayList();
        selectedTeacherProperty = new SimpleObjectProperty<>();

        schoolName = new SimpleStringProperty();
        error = new SimpleStringProperty();

        scheduleButton = new SimpleStringProperty();
        studentListButton = new SimpleStringProperty();

        tabSelectedProperty = new SimpleStringProperty();
    }

    private void loadFromModel() {
        classList.clear();
        for (Class theClass : model.getAllClasses()) {
            classList.add(new ClassViewModel(theClass));
        }

        studentList.clear();
        for (Student student : model.getAllStudents()) {
            studentList.add(new StudentViewModel(student));
        }

        teacherList.clear();
        for (Teacher teacher : model.getAllTeachers()) {
            teacherList.add(new TeacherViewModel(teacher));
        }
    }

    public void clear() {
        //TODO the clear
    }

    public ObservableList<ClassViewModel> getAllClasses() {
        return classList;
    }

    public void setSelected(ClassViewModel selectedLesson) {
        selectedClassProperty.set(selectedLesson);
    }

    public ObservableList<StudentViewModel> getAllStudents() {
        return studentList;
    }

    public void setSelected(StudentViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
    }
    public ObservableList<TeacherViewModel> getAllTeachers() {
        return teacherList;
    }

    public void setSelected(TeacherViewModel selectedTeacher) {
        selectedTeacherProperty.set(selectedTeacher);
    }

    public StringProperty schoolNameProperty() {
        return schoolName;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty scheduleButtonProperty() {
        return scheduleButton;
    }

    public StringProperty studentListButtonProperty() {
        return studentListButton;
    }

    public StringProperty tabSelectedProperty() {
        return tabSelectedProperty;
    }
}
