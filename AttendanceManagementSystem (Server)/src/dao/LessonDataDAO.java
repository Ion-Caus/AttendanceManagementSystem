package dao;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface LessonDataDAO
{
  LessonData readByUserAndLessonID(String userID, String lessonID) throws SQLException;
  List<LessonData> readByStudentID(String studentID) throws SQLException;
  List<LessonData> readByLessonID(String lessonID) throws SQLException;
  void update(LessonData lessonData) throws SQLException;
  void delete(LessonData lessonData) throws SQLException;
}
