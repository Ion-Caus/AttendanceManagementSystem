package model.packages;

public class PackageLesson extends Package{
    private String topic;
    private String contents;
    private String homework;

    public PackageLesson(String ID, String topic, String contents, String homework) {
        super(ID);
        this.topic = topic;
        this.contents = contents;
        this.homework = homework;
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
}
