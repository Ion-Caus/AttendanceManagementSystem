package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Student;

public class StudentListViewModel {
    private ObservableList<StudentListViewModel> studentList;
    private ObjectProperty<StudentListViewModel> selectedStudentProperty;

    private StringProperty lessonTopicProperty;
    private StringProperty studentNameProperty;
    private StringProperty commentProperty;
    private StringProperty absenceProperty;
    private StringProperty motiveProperty;
    private StringProperty classNameProperty;
    private StringProperty errorProperty;

    private IntegerProperty gradeProperty;

    private Model model;
    private ViewModelState viewModelState;

    //TODO 19/5 by Ion do a different ViewModel for the Table and one for the Controller
    public StudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;

        studentList = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

        this.studentNameProperty = new SimpleStringProperty();
        this.gradeProperty = new SimpleIntegerProperty();
        this.commentProperty = new SimpleStringProperty();
        this.absenceProperty = new SimpleStringProperty();
        this.motiveProperty = new SimpleStringProperty();

        this.lessonTopicProperty = new SimpleStringProperty();
        this.classNameProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }


    public void loadFromModel() {
        studentList.clear();
        //for (Student student: model.getStudentsByClass())
    }

    public void clear() {
        errorProperty.set("");
        selectedStudentProperty.set(null);
    }

    public ObservableList<StudentListViewModel> getStudentList() {
        return studentList;
    }

    public void setSelected(StudentListViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
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

    public StringProperty getLessonTopicProperty() {
        return lessonTopicProperty;
    }

    public StringProperty getClassNameProperty() {
        return classNameProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public void changeAbsence() {
        if (selectedStudentProperty.get() == null) {
            errorProperty.set("Please first select a student.");
            return;
        }
        switch (absenceProperty.get()) {
            case "YES":
                absenceProperty.set("---");
                break;
            case "---":
                absenceProperty.set("YES");
                break;
        }
        //model.changeAbsence(selectedStudentProperty.get());

        //TODO 19/5 by Ion change the student lessonDATA and send a broadcast
    }

    public boolean submitDataChange() {
        //TODO 19/5 by Ion Use observer to send the changes
        return false;
    }
}

