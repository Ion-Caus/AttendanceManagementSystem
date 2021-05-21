package dao;

import model.Class;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ScheduleDAO
{
  //void create(Class className) throws SQLException;
  ArrayList<Lesson> readLessons(Class aClass, LocalDate date) throws SQLException;
  ArrayList<Lesson> readLessonsByTeacherName(Teacher teacher) throws SQLException;
  void addLesson(Class aClass, String lessonID) throws SQLException;
  void deleteLesson(String lessonID, Class aClass) throws SQLException;
}
