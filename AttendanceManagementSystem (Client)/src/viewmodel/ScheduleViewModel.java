package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.time.LocalDate;


public class ScheduleViewModel {
    private ObservableList<LessonViewModel> schedule;
    private ObjectProperty<LessonViewModel> selectedLessonProperty;

    private StringProperty userProperty;
    private StringProperty schoolClassProperty;
    private StringProperty errorProperty;

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

        loadFromModel();
    }


    private void loadFromModel() {
        schedule.clear();
        for (Lesson lesson : model.getScheduleFor(dateProperty.get())) {
            schedule.add(new LessonViewModel(lesson));
        }
    }

    public void clear() {
        selectedLessonProperty.set(null);

        userProperty.set(model.getUserLegalName());
        schoolClassProperty.set(model.getSchoolAndClass());
        errorProperty.set("");

        dateProperty.setValue(LocalDate.now());
    }

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

    public ObjectProperty<LocalDate> dateProperty() {
        return dateProperty;
    }
}

