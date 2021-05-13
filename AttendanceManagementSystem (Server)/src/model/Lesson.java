package model;


import java.time.format.DateTimeFormatter;

public class Lesson {
    private Teacher teacher;
    private Date lessonDate;
    private Time startTime, endTime;
    private String subject;
    private String topic;
    private String classroom;
    private String homework;

    //TODO set and get the class
    private String className;

    //TODO 11/05 by Ion Move the homework
    public Lesson(Teacher teacher, Date lessonDate, Time startTime, Time endTime,
                  String subject, String topic, String classroom, String homework) throws IllegalArgumentException {
        if (!hasValidTime(startTime, endTime))
            throw new IllegalArgumentException("Illegal lesson time");
        this.teacher = teacher;
        this.lessonDate = lessonDate;
        this.startTime = startTime.copy();
        this.endTime = endTime.copy();
        this.subject = subject;
        this.topic = topic;
        this.classroom = classroom;
        this.homework = homework;
    }

    public static boolean hasValidTime(Time startTime, Time endTime) {
        return !(endTime.isBefore(startTime));
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getHomework() {
        return homework;
    }

    //TODO 14/5 by Ion find a way to set the className for this lesson
    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getTimeInterval() {
        return String.format("%s - %s", startTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm")), endTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

}
