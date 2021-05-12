package model;

import java.util.ArrayList;

public class LessonDataList {


    private final ArrayList<LessonData> lessonDataList;

    public LessonDataList() {
        this.lessonDataList = new ArrayList<>();
    }

    public void addLessonData(LessonData lessonData) {
        lessonDataList.add(lessonData);
    }

    public void removeLessonData(LessonData lessonData) {
        lessonDataList.remove(lessonData);

    }

    public ArrayList<LessonData> getLessonDataList() {
        return lessonDataList;
    }

    public LessonData getByStudent(Student student) {

        return lessonDataList.stream().filter(lessonData -> lessonData.getStudent().equals(student)).findFirst().orElse(null);

    }
}