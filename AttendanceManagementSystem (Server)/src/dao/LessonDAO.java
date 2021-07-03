package dao;

import model.Lesson;
import model.Teacher;

import java.sql.SQLException;

public interface LessonDAO
{
  void createLesson(String className, Lesson lesson) throws SQLException;  //inserts newly created lesson into exact class schedule
  void updateTeacher(Lesson lesson, Teacher teacher) throws SQLException;
  void updateTopic(Lesson lesson, String topic) throws SQLException;
  void updateHomework(Lesson lesson, String homework) throws SQLException;
  void updateContent(Lesson lesson, String content) throws SQLException;
  void delete(String lessonID) throws SQLException;
  void updateLesson(Lesson lesson, Teacher teacher, String topic, String contents, String homework) throws SQLException;
}
