package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Class;
import model.Lesson;
import model.Model;
import model.Teacher;

import javax.print.DocFlavor;
import java.time.LocalDate;
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
    }

    public void createLesson(){
      /*  Teacher teacher = model.getTeacherBy(viewModelState.getTeacherI)


        Lesson lesson= new Lesson(teacher,date.get(), startTime.get(), endTime.get(), subject.get(), topic.get(), classroom.get(), homework.get(), contents.get()  )
        model.getClassByName(viewModelState.getID()).getSchedule().addLesson(lesson);*/

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

    public ArrayList<Teacher> getTeacherList() {
        return model.getAllTeachers();
    }
}
