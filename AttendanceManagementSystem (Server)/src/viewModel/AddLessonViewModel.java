package viewModel;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;
import model.Class;

import javax.print.DocFlavor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddLessonViewModel {

    private Model model;
    private ViewModelState viewModelState;

    private StringProperty title;
    private StringProperty topic;
    private StringProperty contents;
    private StringProperty homework;
    private StringProperty teacher;
    private StringProperty error;
    private StringProperty subject;
    private StringProperty startTime;
    private StringProperty endTime;
    private ObjectProperty<LocalDate> date;
    private StringProperty classroom;

    public AddLessonViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;

        //Lesson properties
        this.title = new SimpleStringProperty();
        this.subject = new SimpleStringProperty();
        this.topic = new SimpleStringProperty();
        this.contents = new SimpleStringProperty();
        this.homework = new SimpleStringProperty();
        this.teacher = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>(LocalDate.now());
        this.classroom = new SimpleStringProperty();
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();




        this.error = new SimpleStringProperty();
    }

    public void clear(){
        title.set("");
        topic.set("");
        contents.set("");
        homework.set("");
        teacher.set("");
        error.set("");
        subject.set("");
        startTime.set("");
        endTime.set("");
        date.set(null);
        classroom.set("");
    }

    public boolean createLesson(){
       try {
           String id = (teacher.get().contains("(")) ? teacher.get().split("[()]")[1] : "no id";
           Teacher teacher1 = model.getTeacherBy(id);
           Class aClass = model.getClassByName(viewModelState.getClassName());
           Date dateFromModel = new Date(date.get().getYear(), date.get().getMonthValue(), date.get().getDayOfMonth());

           Time startingTime = new Time(LocalTime.parse(startTime.get()));
           Time endingTime = new Time(LocalTime.parse(endTime.get()));

           aClass.getSchedule().addLesson(new Lesson("bohatam", teacher1, dateFromModel, startingTime, endingTime, subject.get(), topic.get(), contents.get(), classroom.get(), homework.get(), classroom.get()));
           System.out.println("we are here");

           return true;
       }

       catch (NullPointerException | IllegalArgumentException e){
           error.set("please make sure to fill out all the fields");
           return false;
       }
       catch (DateTimeParseException parseException){
           error.set("please fill out the lesson time in this format hh:mm for example 12:20");
           startTime.set("");
           endTime.set("");
           return false;
       }



    }




    public StringProperty titleProperty() {
        return title;
    }



    public StringProperty topicProperty() {
        return topic;
    }



    public StringProperty contentsProperty() {
        return contents;
    }



    public StringProperty homeworkProperty() {
        return homework;
    }



    public StringProperty teacherProperty() {
        return teacher;
    }



    public StringProperty errorProperty() {
        return error;
    }



    public StringProperty subjectProperty() {
        return subject;
    }



    public StringProperty startTimeProperty() {
        return startTime;
    }



    public StringProperty endTimeProperty() {
        return endTime;
    }



    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }



    public StringProperty classroomProperty() {
        return classroom;
    }

    public ArrayList<String> getTeacherList() {
        ArrayList<String> teachers = new ArrayList<>();
        for (Teacher teacher: model.getAllTeachers()){
            teachers.add((String.format("%s (%S)",teacher.getName(), teacher.getID())));

        }
        return teachers;
    }
}
