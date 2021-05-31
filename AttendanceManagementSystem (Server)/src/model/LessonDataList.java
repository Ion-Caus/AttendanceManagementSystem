package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class serves as a containing element for the LessonData
 */
public class LessonDataList {

    private final ArrayList<LessonData> lessonDataList;

    /**
     * 0 argument constructor will initialize lessonDataList ArrayList as a new ArrayList
     */
    public LessonDataList() {
        this.lessonDataList = new ArrayList<>();
    }

    /** 1 argument constructor that will initialize the lessonDataList using the provided parameter
     * @param lessonDataList
     */
    public LessonDataList(ArrayList<LessonData> lessonDataList) {
        this.lessonDataList = lessonDataList;
    }

    /**This method will add lessonData provided as a parameter to the lessonDataList
     * @param lessonData the lessonData that should be added to the list
     */
    public void addLessonData(LessonData lessonData) {
        lessonDataList.add(lessonData);
    }

    public void removeLessonData(LessonData lessonData) {
        lessonDataList.remove(lessonData);

    }

    /** This method will remove lessonData from the list based on the student associated to it
     * @param studentID a student id argument based on which the method removes the lessonData from the list
     */
    public void removeLessonDataByStudent(String studentID){
        lessonDataList.removeIf(lessonData -> Objects.equals(lessonData.getStudent().getID(),studentID));
    }

    // TODO: 31/5/2021 tomas methods below are not used

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

    /** The purpose of this method is to get lesonData based on student and lesson
     * @param lesson lesson associated with lessonData
     * @param student student associated with lessonData
     * @return LessonData that is associated with both student and lesson arguments
     * @throws IllegalArgumentException will be thrown in case there is no match
     */
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