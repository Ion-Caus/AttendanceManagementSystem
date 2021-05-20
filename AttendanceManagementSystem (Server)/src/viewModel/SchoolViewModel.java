package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.*;

import model.Class;
import model.packages.Package;
import model.packages.PackageName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class SchoolViewModel implements LocalListener<String, Package> {
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
        this.model.addListener(this,  "ADD_TO_CLASS Student","REMOVE_FROM_CLASS Student","ADD Class", "REMOVE Class", "ADD Student", "REMOVE Student", "ADD Teacher", "REMOVE Teacher");
        this.viewModelState = viewModelState;

        classList = FXCollections.observableArrayList();
        selectedClassProperty = new SimpleObjectProperty<>();

        studentList = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

        teacherList = FXCollections.observableArrayList();
        selectedTeacherProperty = new SimpleObjectProperty<>();

        schoolName = new SimpleStringProperty(model.getSchoolName());
        error = new SimpleStringProperty();

        tabSelectedProperty = new SimpleStringProperty("Classes");


        //TODO set in login
        viewModelState.setAccessLevel("Administrator");


        //TODO 18/05 by Ion
        // if setAccessLevel("Teacher"); or Student
        // viewModelState.setSection("Teacher"); or Student
        loadFromModel();
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
        error.setValue("");

    }

    // class list
    public ObservableList<ClassViewModel> getAllClasses() {
        return classList;
    }

    public void setSelected(ClassViewModel selectedLesson) {
        selectedClassProperty.set(selectedLesson);
    }

    public ClassViewModel getSelectedClass() {
        return selectedClassProperty.get();
    }

    // student list
    public ObservableList<StudentViewModel> getAllStudents() {
        return studentList;
    }

    public void setSelected(StudentViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
    }

    public StudentViewModel getSelectedStudent() {
        return selectedStudentProperty.get();
    }


    // teacher list
    public ObservableList<TeacherViewModel> getAllTeachers() {
        return teacherList;
    }

    public void setSelected(TeacherViewModel selectedTeacher) {
        selectedTeacherProperty.set(selectedTeacher);
    }

    public TeacherViewModel getSelectedTeacher() {
        return selectedTeacherProperty.get();
    }

    // getters for properties
    public StringProperty schoolNameProperty() {
        return schoolName;
    }

    public StringProperty errorProperty() {
        return error;
    }


    // tab property
    public void setTabSelectedProperty(String tabSelectedProperty) {
        this.tabSelectedProperty.set(tabSelectedProperty);
    }

    public String getTabSelectedProperty() {
        return this.tabSelectedProperty.get();
    }

    public void setSchoolName(String schoolName) {
        this.schoolName.set(schoolName);
        model.setSchoolName(schoolName);
    }

    public void removeClass(String className) {
        try {
            clear();
            model.removeClass(className);
        }
        catch (IllegalAccessException e) {
            error.set(e.getLocalizedMessage());
        }
    }

    public void removeStudent(String studentID) {
        clear();
        model.removeStudent(studentID);
    }

    public void removeTeacher(String teacherID) {
        clear();
        model.removeTeacher(teacherID);
    }

    public boolean viewSchedule() {
        switch (tabSelectedProperty.get()) {
            case "Classes":
                try {
                    viewModelState.setSection("Class");
                    viewModelState.setClassName(selectedClassProperty.get().classNameProperty().get());
                    return true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    error.setValue("Please select a class.");
                    return false;
                }

            case "Students":
                try {
                    if (selectedStudentProperty.get().classNameProperty().get() == null) {
                        error.set("Student is not in a class.");
                        return false;
                    }
                    viewModelState.setSection("Student");
                    viewModelState.setStudentID(selectedStudentProperty.get().idProperty().get());
                    return true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    error.setValue("Please select a student.");
                    return false;
                }

            case "Teachers":
                try {
                    viewModelState.setSection("Teacher");
                    viewModelState.setTeacherID(selectedTeacherProperty.get().idProperty().get());
                    return true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    error.setValue("Please select a teacher.");
                    return false;
                }
        }
        return false;
    }

    public boolean viewStudentList() {
        try {
            viewModelState.setClassName(selectedClassProperty.get().classNameProperty().get());
            return true;
        }catch (NullPointerException e){
            error.setValue("Please select a class.");
            return false;
        }
    }

    private void add(String who, String name, String id) {
        switch (who) {
            case "Student":
                studentList.add(new StudentViewModel(new Student(name, id)));
                break;
            case "Class":
                classList.add(new ClassViewModel(new Class(id)));
                break;
            case "Teacher":
                teacherList.add(new TeacherViewModel(new Teacher(name,id)));
                break;
        }
    }

    private void remove(String who, String id) {
        switch (who) {
            case "Student":
                studentList.removeIf(student -> student.idProperty().get().equals(id));
                break;
            case "Class":
                classList.removeIf(aClass -> aClass.classNameProperty().get().equals(id));
                break;
            case "Teacher":
                teacherList.removeIf(teacher -> teacher.idProperty().get().equals(id));
                break;
        }
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        //TODO 17/05 by Ion Clean up this part of code and the Observer names for remove and add Student to/from class
        Platform.runLater(() -> {
            String[] commands = event.getPropertyName().split(" ");

            switch (commands[0]) {
                case "ADD_TO_CLASS":
                case "REMOVE_FROM_CLASS":
                    Student student = model.getStudentBy( event.getValue2().getID() );
                    studentList.removeIf(studentVM -> studentVM.idProperty().get().equals(student.getID()));
                    studentList.add(0, new StudentViewModel(student));
                    break;
                case "ADD":
                    PackageName pm = (PackageName)event.getValue2();
                    add(commands[1], pm.getName(), pm.getID());
                    break;
                case "REMOVE":
                    remove(commands[1],  event.getValue2().getID());
                    break;
            }
        });
    }

    public boolean hasSelectionClass() {
        if (selectedClassProperty.get() == null) {
            error.set("Please select a class.");
            return false;
        }
        return true;
    }

    public boolean hasSelectionStudent() {
        if (selectedStudentProperty.get() == null) {
            error.set("Please select a student.");
            return false;
        }
        return true;
    }

    public boolean hasSelectionTeacher() {
        if (selectedTeacherProperty.get() == null) {
            error.set("Please select a teacher.");
            return false;
        }
        return true;
    }




}
