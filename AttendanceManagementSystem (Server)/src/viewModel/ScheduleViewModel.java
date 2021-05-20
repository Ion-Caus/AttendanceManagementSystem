package viewModel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Lesson;
import model.Model;
import model.Student;
import model.Teacher;
import model.packages.Package;
import model.packages.PackageLesson;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;


import java.time.LocalDate;


public class ScheduleViewModel implements LocalListener<String, Package> {
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
        model.addListener(this, "ChangeLesson");
        this.viewState = viewModelState;

        schedule = FXCollections.observableArrayList();
        selectedLessonProperty = new SimpleObjectProperty<>();

        userProperty = new SimpleStringProperty();
        schoolClassProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();

        dateProperty = new SimpleObjectProperty<>(LocalDate.now());

        forAdminProperty = new SimpleBooleanProperty();

    }

    private void loadScheduleForDay() {
        schedule.clear();

        switch (viewState.getSection()) {
            case "Student":
                for (Lesson lesson : model.getScheduleFor(model.getStudentBy(viewState.getStudentID()), dateProperty.getValue())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Class":
                for (Lesson lesson : model.getScheduleFor(model.getClassByName(viewState.getClassName()), dateProperty.getValue())) {
                    schedule.add(new LessonViewModel(lesson));
                }
                break;
            case "Teacher":
                for (Lesson lesson : model.getScheduleFor(model.getTeacherBy(viewState.getTeacherID()), dateProperty.getValue())) {
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
                Student student = model.getStudentBy(viewState.getStudentID());
                userProperty.set(student.getName());
                schoolClassProperty.set(model.getClassAndSchool(student));
                break;

            case "Teacher":
                Teacher teacher = model.getTeacherBy(viewState.getTeacherID());
                userProperty.set(teacher.getName());
                schoolClassProperty.set(model.getSchoolName());
                break;

            case "Class":
                userProperty.set("");
                schoolClassProperty.set(model.getClassByName(viewState.getClassName()).getClassName() + ", " + model.getSchoolName());
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

    public ObjectProperty<LessonViewModel> getSelected() {
       return selectedLessonProperty;
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


    public boolean loadInfoLesson(){
        try {
            viewState.setLessonID(selectedLessonProperty.get().idProperty().get());
            return true;
        } catch (NullPointerException e) {
            errorProperty.set("Please select a lesson.");
            return false;
        }
    }



    public  boolean hasSelectionProperty(){
        if (selectedLessonProperty.get() == null) {
            errorProperty.set("Please select a class.");
            return false;
        }
        return true;

    }

    public boolean deleteLesson(){
        try {

            // TODO: 20/5/2021 by tomas use observer and move this code to model manager as a method and call that method from here 
            Lesson lesson = model.getClassByName(viewState.getID()).getSchedule().getLessonBy(selectedLessonProperty.get().idProperty().get());
            model.getClassByName(viewState.getID()).getSchedule().removeLesson(lesson);
            loadScheduleForDay();
            errorProperty.set("");
            return true;
        } catch (NullPointerException | IllegalArgumentException e) {
           errorProperty.set("Please select a lesson");
           return false;
        }


    }



    public boolean addLesson(){
        return false;
    }


    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "ChangeLesson":
                    PackageLesson pl = (PackageLesson)event.getValue2();
                    Lesson lesson = model.getLesson(pl.getID());
                    int index = 0;
                    for (int i = 0; i < schedule.size(); i++) {
                        if (schedule.get(i).idProperty().get().equals(pl.getID())) {
                            schedule.remove(schedule.get(i));
                            index = i;
                            break; // break for-loop
                        }
                    }
                    schedule.add(index, new LessonViewModel(lesson));
                    break;
                case "ADD Lesson":
            }
        });
    }

}

