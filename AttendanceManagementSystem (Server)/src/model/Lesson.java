package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a lesson
 */
public class Lesson implements Serializable {
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

    /**
     * This constructor is dependent on the constructor below, it is going to call him providing all the parameters as arguments and after that it is going to initialize id from the argument
     *
     * @param id the lesson id
     * @param teacher the teacher that is teaching the lesson as an object
     * @param lessonDate the date on which lesson is happening
     * @param startTime the lesson's start time
     * @param endTime the lesson's end time
     * @param subject the subject for the lesson, for example Math
     * @param topic the topic for the lesson, for example probabilities
     * @param contents the contents for the lessons
     * @param classroom the name of the classroom
     * @param homework homework that students should complete after lesson
     * @param className name of the class associated with the lesson
     * @throws IllegalArgumentException will be thrown in case some argument is not valid
     */
    public Lesson(String id, Teacher teacher, LocalDate lessonDate, LocalTime startTime, LocalTime endTime, String subject, String topic, String contents, String classroom, String homework, String className) throws IllegalArgumentException {
        this(teacher, lessonDate, startTime, endTime, subject, topic, contents, classroom, homework, className);
        this.id = id;
    }

    /**
     * This constructor is going to initialize all the parameters from arguments provided, except for the id, which is always going to be initialized as "no id"
     *@param teacher the teacher that is teaching the lesson as an object
     *      * @param lessonDate the date on which lesson is happening
     *      * @param startTime the lesson's start time
     *      * @param endTime the lesson's end time
     *      * @param subject the subject for the lesson, for example Math
     *      * @param topic the topic for the lesson, for example probabilities
     *      * @param contents the contents for the lessons
     *      * @param classroom the name of the classroom
     *      * @param homework homework that students should complete after lesson
     *      * @param className name of the class associated with the lesson
     *      * @throws IllegalArgumentException will be thrown in case some argument is not valid
     * @throws IllegalArgumentException this exception is going to be thrown in case the lesson time interval is not valid or if any other argument is not valid or empty
     */
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

    /** This method will check if the time interval is valid, it will return true if it is and false if it is not valid
     * @param startTime the lesson's starting time
     * @param endTime the lessons's ending time
     * @return true or false, based on if the time interval is valid
     */
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

    /**
     * @return will return the lesson's time interval in one string
     */
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
