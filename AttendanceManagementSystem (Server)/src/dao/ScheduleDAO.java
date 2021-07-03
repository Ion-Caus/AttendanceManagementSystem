package dao;

import model.StudentClass;
import model.Lesson;
import model.Teacher;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ScheduleDAO
{
  //void create(StudentClass className) throws SQLException;
  ArrayList<Lesson> readAll() throws SQLException;
  ArrayList<Lesson> readLessons(StudentClass aClass, LocalDate date) throws SQLException;
  ArrayList<Lesson> readLessonsByTeacherName(Teacher teacher) throws SQLException;
  void addLesson(StudentClass aClass, String lessonID) throws SQLException;
  void deleteLesson(String lessonID, StudentClass aClass) throws SQLException;
}
