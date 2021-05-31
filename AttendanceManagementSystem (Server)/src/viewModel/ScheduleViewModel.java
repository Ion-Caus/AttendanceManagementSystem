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
import model.packages.PackageLessonInfo;
import model.packages.PackageName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
        model.addListener(this, "ChangeLesson", "ADD Lesson", "REMOVE Lesson");
        this.viewState = viewModelState;

        schedule = FXCollections.observableArrayList();
        selectedLessonProperty = new SimpleObjectProperty<>();

        userProperty = new SimpleStringProperty();
        schoolClassProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();

        dateProperty = new SimpleObjectProperty<>(LocalDate.now());

        forAdminProperty = new SimpleBooleanProperty();

    }

    public String getSection(){
        return viewState.getSection();
    }

    public void loadScheduleForDay() {
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
        sortSchedule();
    }

    private void sortSchedule() {
        // sorting the schedule by time
        List<LessonViewModel> list =  schedule.stream()
                .sorted(Comparator.comparing(i -> i.timeProperty().get()))
                .collect(Collectors.toList());
        schedule.clear();
        schedule.addAll(list);
    }

    public void clear() {
        errorProperty.set("");
        dateProperty.setValue(LocalDate.now());

        switch (viewState.getSection()) {
            case "Student":
                Student student = model.getStudentBy(viewState.getStudentID());
                userProperty.set(student.getName());
                schoolClassProperty.set("Class: " + student.getClassName());
                viewState.setClassName(student.getClassName());
                break;

            case "Teacher":
                Teacher teacher = model.getTeacherBy(viewState.getTeacherID());
                userProperty.set(teacher.getName());
                schoolClassProperty.set("");
                break;

            case "Class":
                userProperty.set("");
                schoolClassProperty.set("Class: " + viewState.getClassName());
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

    public boolean hasSelectionProperty(){
        if (selectedLessonProperty.get() == null) {
            errorProperty.set("Please select a class.");
            return false;
        }
        return true;
    }

    public void deleteLesson(){
        try {
            model.removeLesson(viewState.getClassName(), selectedLessonProperty.get().idProperty().get());
            errorProperty.set("");
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
           errorProperty.set("Please select a lesson");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addLesson(Lesson lesson) {
        if (   ( Objects.equals(viewState.getSection(), "Teacher")
                && Objects.equals(lesson.getTeacher().getID(), viewState.getTeacherID())
                && lesson.getLessonDate().equals(dateProperty.get())
        ) ||
                (!Objects.equals(viewState.getSection(), "Teacher")
                        && Objects.equals(lesson.getClassName(), viewState.getClassName()) )
                        && lesson.getLessonDate().equals(dateProperty.get() )
        ) {

            schedule.add(new LessonViewModel(lesson));
        }
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "ChangeLesson":
                    PackageLessonInfo pli = (PackageLessonInfo)event.getValue2();
                    Lesson lesson = model.getLesson(pli.getID());
                    schedule.removeIf(lessonViewModel -> lessonViewModel.idProperty().get().equals(lesson.getId()));
                    addLesson(lesson);
                    break;
                case "ADD Lesson":
                    PackageLesson packageLesson = (PackageLesson) event.getValue2();
                    if (schedule.stream().noneMatch(
                            lessonViewModel -> lessonViewModel.idProperty().get().equals(packageLesson.getID()))
                    ) {
                        addLesson(packageLesson.getLesson());
                    }
                    break;
                case "REMOVE Lesson":
                    schedule.removeIf(lessonViewModel -> lessonViewModel.idProperty().get().equals(event.getValue2().getID()));
                    break;
            }
        });
    }

}

