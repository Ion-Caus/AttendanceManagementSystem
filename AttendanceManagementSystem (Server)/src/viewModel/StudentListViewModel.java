package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Class;
import model.LessonData;
import model.Model;
import model.Student;

public class StudentListViewModel {
    private ObservableList<StudentLessonDataViewModel> lessonDataList;
    private ObjectProperty<StudentLessonDataViewModel> selectedStudentProperty;

    private StringProperty lessonTopicProperty;
    private StringProperty classNameProperty;
    private StringProperty errorProperty;

    private Model model;
    private ViewModelState viewState;

    //TODO 19/5 by Ion do a different ViewModel for the Table and one for the Controller
    public StudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewState = viewModelState;

        lessonDataList = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

        this.lessonTopicProperty = new SimpleStringProperty();
        this.classNameProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void loadFromModel() {
        lessonDataList.clear();
        Class aClass = model.getClassByName(viewState.getID());
        var lesson = model.getLesson(viewState.getLessonID(), aClass);
        System.out.println(aClass.getClassName());
        for (Student student: model.getStudentsByClass(aClass.getClassName())){
            lessonDataList.add(new StudentLessonDataViewModel(model.getLessonData(lesson,student)));
        }
    }

    public void clear() {
        errorProperty.set("");
        selectedStudentProperty.set(null);
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
//        selectedStudentProperty.get().setAbsence(model.changeAbsence(selectedStudentProperty.get()));

        //TODO 19/5 by Ion change the student lessonDATA and send a broadcast
    }

    public boolean submitDataChange() {
        //TODO 19/5 by Ion Use observer to send the changes
        return false;
    }
}

