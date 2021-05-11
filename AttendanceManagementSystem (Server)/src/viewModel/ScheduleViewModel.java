package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Lesson;
import model.Model;
import model.Student;

import java.time.LocalDate;


public class ScheduleViewModel {
    private ObservableList<LessonViewModel> schedule;
    private ObjectProperty<LessonViewModel> selectedLessonProperty;

    private StringProperty userProperty;
    private StringProperty schoolClassProperty;
    private StringProperty errorProperty;

    private BooleanProperty canEditProperty;
    private BooleanProperty canBackProperty;

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

        canEditProperty = new SimpleBooleanProperty();
        canBackProperty = new SimpleBooleanProperty();

    }


    private void loadFromModel() {
        schedule.clear();

        switch (viewState.getSection()) {
            case "Student":
                for (Lesson lesson : model.getScheduleFor(model.getStudentBy(viewState.getId()), dateProperty.get())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Class":
                for (Lesson lesson : model.getScheduleFor(model.getClassByName(viewState.getId()), dateProperty.get())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Teacher":
                break;
        }

    }

    public void clear() {
        loadFromModel();

        selectedLessonProperty.set(null);

        System.out.println(viewState.getSection());
        switch (viewState.getSection()) {
            case "Student":
                Student student = model.getStudentBy(viewState.getId());
                userProperty.set(student.getName());
                schoolClassProperty.set(model.getClassAndSchool(student));
                break;

            case "Teacher":
                //userProperty.set(model.getTeacherBy(viewState.getId(););
                schoolClassProperty.set(model.getSchoolName());
                break;

            case "Class":
                userProperty.set("");
                schoolClassProperty.set(model.getClassByName(viewState.getId()).getClassName() + ", " + model.getSchoolName());
                break;
        }

        switch (viewState.getAccessLevel()) {
            case "Student":
                canEditProperty.set(false);
                canBackProperty.set(false);
                break;
            case "Teacher":
                canEditProperty.set(true);
                canBackProperty.set(false);
                break;
            case "Administrator":
                canEditProperty.set(true);
                canBackProperty.set(true);
                break;
        }


        errorProperty.set("");

        dateProperty.setValue(LocalDate.now());
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

    public BooleanProperty canEditProperty() {
        return canEditProperty;
    }

    public BooleanProperty canBackProperty() {
        return canBackProperty;
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
        loadFromModel();
    }

    public void previousDay() {
        dateProperty.setValue(dateProperty.get().minusDays(1));
    }

    public void nextDay() {
        dateProperty.setValue(dateProperty.get().plusDays(1));
    }

}

