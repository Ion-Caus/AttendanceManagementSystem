package model.packages;

public class PackageLessonInfo extends Package{
    private String topic;
    private String contents;
    private String homework;
    private String teacherID;

    public PackageLessonInfo(String ID, String topic, String contents, String homework, String teacherID) {
        super(ID);
        this.topic = topic;
        this.contents = contents;
        this.homework = homework;
        this.teacherID = teacherID;
    }

    public String getTopic() {
        return topic;
    }

    public String getContents() {
        return contents;
    }

    public String getHomework() {
        return homework;
    }

    public String getTeacherID() {
        return teacherID;
    }
}
