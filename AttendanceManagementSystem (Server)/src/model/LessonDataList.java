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

    public ArrayList<LessonData> getByStudent(Student student) {
        ArrayList<LessonData> lessonDataByStudent = new ArrayList<>();
        for (LessonData data: lessonDataList) {
            if (data.getStudent().equals(student)) {
                lessonDataByStudent.add(data);
            }
        }
        return lessonDataByStudent;
    }

    public LessonData getByStudentAndLesson(Lesson lesson, Student student) throws IllegalArgumentException {
        for (LessonData data: lessonDataList) {
            if (data.getStudent().equals(student) &&
                data.getLesson().getId().equals(lesson.getId())) {
                return data;
            }
        }
        throw new IllegalArgumentException(String.format("No data for student with id(%s) at the lesson with id(%s)",student.getID(), lesson.getId()));
    }
}