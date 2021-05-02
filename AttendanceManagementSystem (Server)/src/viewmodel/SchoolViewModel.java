package viewModel;

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

        schoolName = new SimpleStringProperty("School Name");
        error = new SimpleStringProperty();

        tabSelectedProperty = new SimpleStringProperty("Classes");

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
//        for (Teacher teacher : model.getAllTeachers()) {
//            teacherList.add(new TeacherViewModel(teacher));
//        }
    }

    public void clear() {
        loadFromModel();
        //TODO the clear
    }

    // class list
    public ObservableList<ClassViewModel> getAllClasses() {
        return classList;
    }

    public void setSelected(ClassViewModel selectedLesson) {
        selectedClassProperty.set(selectedLesson);
    }

    // student list
    public ObservableList<StudentViewModel> getAllStudents() {
        return studentList;
    }

    public void setSelected(StudentViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
    }

    // teacher list
    public ObservableList<TeacherViewModel> getAllTeachers() {
        return teacherList;
    }

    public void setSelected(TeacherViewModel selectedTeacher) {
        selectedTeacherProperty.set(selectedTeacher);
    }


    // getters for properties
    public StringProperty schoolNameProperty() {
        return schoolName;
    }

    public StringProperty errorProperty() {
        return error;
    }

    // setter for tab property
    public void setTabSelectedProperty(String tabSelectedProperty) {
        this.tabSelectedProperty.set(tabSelectedProperty);
    }

    public void setSchoolName(String schoolName) {
        this.schoolName.set(schoolName);
    }

    // button methods
    public void add() {
        switch (tabSelectedProperty.get()) {
            case "Classes" :

                break;
            case "Students" :

                break;
            case "Teachers" :

                break;
            case "Admins" :
                break;
        }
    }

    public void remove() {
        switch (tabSelectedProperty.get()) {
            case "Classes" :


                break;
            case "Students" :

                break;
            case "Teachers" :

                break;
            case "Admins" :
                break;
        }
    }

    public void viewSchedule() {
        switch (tabSelectedProperty.get()) {
            case "Classes" :
            case "Students" :
            case "Teachers" :
                viewModelState.setId(selectedStudentProperty.get().idProperty().get());
                System.out.println(viewModelState.getId());
                break;
        }
    }

}