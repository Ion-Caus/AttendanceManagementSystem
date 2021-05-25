package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Lesson {
    private String id;
    private Teacher teacher;
    private LocalDate lessonDate;
    private LocalTime startTime, endTime;
    private String subject;
    private String topic;
    private String contents;
    private String classroom;
    private String homework;
    private String className;

    public Lesson(String id, Teacher teacher, LocalDate lessonDate, LocalTime startTime, LocalTime endTime, String subject, String topic, String contents, String classroom, String homework, String className) throws IllegalArgumentException {
        this(teacher, lessonDate, startTime, endTime, subject, topic, contents, classroom, homework, className);
        this.id = id;
    }

    public Lesson(Teacher teacher, LocalDate lessonDate, LocalTime startTime, LocalTime endTime, String subject, String topic, String contents, String classroom, String homework, String className) throws IllegalArgumentException {
        if (!hasValidTime(startTime, endTime))
            throw new IllegalArgumentException("Illegal lesson time");
        if(subject.isEmpty()||topic.isEmpty())
            throw new IllegalArgumentException("Please fill out the subject and topic");
        this.id = "no id";
        this.teacher = teacher;
        this.lessonDate = lessonDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.topic = topic;
        this.contents = contents;
        this.classroom = classroom;
        this.homework = homework;
        this.className = className;
    }

    public static boolean hasValidTime(LocalTime startTime, LocalTime endTime) {
        return !(endTime.isBefore(startTime));
    }

    public String getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public String getContents() {
        return contents;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getHomework() {
        return homework;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getTimeInterval() {
        return String.format("%s - %s", startTime.format(DateTimeFormatter.ofPattern("HH:mm")), endTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", teacher=" + teacher +
                ", lessonDate=" + lessonDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", subject='" + subject + '\'' +
                ", topic='" + topic + '\'' +
                ", contents='" + contents + '\'' +
                ", classroom='" + classroom + '\'' +
                ", homework='" + homework + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
