package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Lesson;
import model.Model;
import model.Student;
import model.Teacher;

import java.time.LocalDate;


public class ScheduleViewModel {
    private ObservableList<LessonViewModel> schedule;
    private ObjectProperty<LessonViewModel> selectedLessonProperty;

    private StringProperty userProperty;
    private StringProperty schoolClassProperty;
    private StringProperty errorProperty;

    private BooleanProperty forAdminProperty;

    private ObjectProperty<LocalDate> dateProperty;

    private Model model;
    private ViewModelState viewState;

    public ScheduleViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewState = viewModelState;

        schedule = FXCollections.observableArrayList();
        selectedLessonProperty = new SimpleObjectProperty<>();

        userProperty = new SimpleStringProperty();
        schoolClassProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();

        dateProperty = new SimpleObjectProperty<>(LocalDate.now());

        forAdminProperty = new SimpleBooleanProperty();

    }

    //TODO How should we load the data, and Database?
    private void loadScheduleForDay() {
        schedule.clear();

        switch (viewState.getSection()) {
            case "Student":
                for (Lesson lesson : model.getScheduleFor(model.getStudentBy(viewState.getID()), dateProperty.getValue())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Class":
                for (Lesson lesson : model.getScheduleFor(model.getClassByName(viewState.getID()), dateProperty.getValue())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Teacher":
                for (Lesson lesson : model.getScheduleFor(model.getTeacherBy(viewState.getID()), dateProperty.getValue())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
        }

    }

    public void clear() {
        errorProperty.set("");
        dateProperty.setValue(LocalDate.now());

        loadScheduleForDay();

        selectedLessonProperty.set(null);

        switch (viewState.getSection()) {
            case "Student":
                Student student = model.getStudentBy(viewState.getID());
                userProperty.set(student.getName());
                schoolClassProperty.set(model.getClassAndSchool(student));
                break;

            case "Teacher":
                Teacher teacher = model.getTeacherBy(viewState.getID());
                userProperty.set(teacher.getName());
                schoolClassProperty.set(model.getSchoolName());
                break;

            case "Class":
                userProperty.set("");
                //TODO 13/5 by Ion Student has the String className so just return it
                schoolClassProperty.set(model.getClassByName(viewState.getID()).getClassName() + ", " + model.getSchoolName());
                break;
        }

        switch (viewState.getAccessLevel()) {
            case "Student":
            case "Teacher":
                forAdminProperty.set(false);
                break;
            case "Administrator":
                forAdminProperty.set(true);
                break;
        }
    }

    // properties
    public ObservableList<LessonViewModel> getSchedule() {
        return schedule;
    }

    public void setSelected(LessonViewModel selectedLesson) {
        selectedLessonProperty.set(selectedLesson);
    }

    public StringProperty userProperty() {
        return userProperty;
    }

    public StringProperty schoolClassProperty() {
        return schoolClassProperty;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    public BooleanProperty forAdminProperty() {
        return forAdminProperty;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return dateProperty;
    }
    //--

    public void backToSchoolView() {
        viewState.clear();
    }

    // datepicker methods
    public void changeDate() {
        loadScheduleForDay();
    }

    public void previousDay() {
        dateProperty.setValue(dateProperty.get().minusDays(1));
    }

    public void nextDay() {
        dateProperty.setValue(dateProperty.get().plusDays(1));
    }


    public void loadInfoLesson(){
        viewState.setLessonID(selectedLessonProperty.get().idProperty().get());
        System.out.println("View State");
        System.out.println("AccessLevel: " + viewState.getAccessLevel());
        System.out.println("ID: " + viewState.getID());
        System.out.println("Selection: " + viewState.getSection());
        System.out.println("LessonID: " + viewState.getLessonID());
    }

}

