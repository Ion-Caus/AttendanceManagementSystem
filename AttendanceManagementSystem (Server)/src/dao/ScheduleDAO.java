package dao;

import model.Class;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ScheduleDAO
{
  //void create(Class className) throws SQLException;
  List<Lesson> readLessons(Class aClass, Date date) throws SQLException;
  List<Lesson> readLessonsByTeacherName(Teacher teacher) throws SQLException;
  void update(String scheduleID) throws SQLException; //not needed, check Impl class
  void addLesson(Class aClass, String lessonID) throws SQLException;
  void deleteLesson(String lessonID, Class aClass) throws SQLException;
}
