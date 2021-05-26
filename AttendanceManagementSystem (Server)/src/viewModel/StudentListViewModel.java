package viewModel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.Class;
import model.packages.Package;
import model.packages.PackageAbsence;
import model.packages.PackageGrade;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;

import java.sql.SQLException;

public class StudentListViewModel implements GeneralListener<String, Package>  {
    private ObservableList<StudentLessonDataViewModel> lessonDataList;
    private ObjectProperty<StudentLessonDataViewModel> selectedStudentProperty;

    private StringProperty lessonTopicProperty;
    private StringProperty classNameProperty;
    private StringProperty errorProperty;

    private Model model;
    private ViewModelState viewState;

    public StudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        model.addListener(this, "ChangeMotive", "ChangeAbsence");
        this.viewState = viewModelState;

        lessonDataList = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

        this.lessonTopicProperty = new SimpleStringProperty();
        this.classNameProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void loadFromModel() {
        lessonDataList.clear();
        Class aClass = null;
        switch (viewState.getSection()) {
            case "Teacher":
                Lesson lesson = model.getLesson(viewState.getLessonID());
                aClass = model.getClassByName(lesson.getClassName());
                break;
            case "Class":
                aClass = model.getClassByName(viewState.getClassName());
                break;
            case "Student":
                Student student = model.getStudentBy(viewState.getStudentID());
                aClass = model.getClassByName(student.getClassName());
                break;
        }
        assert aClass != null;
        Lesson lesson = model.getLesson(viewState.getLessonID(), aClass);
        for (Student student: model.getStudentsByClass(aClass.getClassName())){
            lessonDataList.add(new StudentLessonDataViewModel(model.getLessonData(lesson,student)));
        }

    }

    public void clear() {
        errorProperty.set("");
    }

    public ObservableList<StudentLessonDataViewModel> getLessonDataList() {
        return lessonDataList;
    }

    public void setSelected(StudentLessonDataViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
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

        try {
            model.changeAbsence(selectedStudentProperty.get().getIDStudentProperty().get(),
                    viewState.getLessonID(),
                    selectedStudentProperty.get().getAbsenceProperty().get().equals("YES")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        clear();
    }

    public boolean openGrade() {
        if(selectedStudentProperty.get() == null){
            errorProperty.set("Please select a student.");
            return false;
        }
        viewState.setStudentID(selectedStudentProperty.get().getIDStudentProperty().get());
        return true;
    }


    private void updateLessonData(String studentID, String lessonID) {
        LessonData lessonData = model.getLessonData(
                model.getLesson(lessonID),
                model.getStudentBy(studentID)
        );
        int index = 0;
        for (int i = 0; i < lessonDataList.size(); i++) {
            if (lessonDataList.get(i).getIDStudentProperty().get().equals(studentID)) {
                lessonDataList.remove(lessonDataList.get(i));
                index = i;
                break;
            }
        }
        lessonDataList.add(index, new StudentLessonDataViewModel(lessonData));
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "ChangeMotive":
                case "ChangeAbsence":
                    PackageAbsence pa = (PackageAbsence)event.getValue2();
                    updateLessonData(pa.getID(), pa.getLessonID());
                    break;
                case "ChangeGradeComment":
                    PackageGrade pg = (PackageGrade)event.getValue2();
                    updateLessonData(pg.getID(), pg.getLessonID());
            }
        });
    }
}

