package dao;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LessonDataDAO
{
  void createLessonData(String lessonID, String studentID) throws SQLException;
  LessonData readByStudentAndLessonID(String studentID, String lessonID) throws SQLException;
  ArrayList<LessonData> readAll() throws SQLException;
  ArrayList<LessonData> readAllByStudentID(String studentID) throws SQLException;
  ArrayList<LessonData> readAllByLessonID(String lessonID) throws SQLException;
  void updateGrade(LessonData lessonData) throws SQLException;   //could be done with comment, gotta see how we implement it in the app
  void updateComment(LessonData lessonData) throws SQLException;
  void updateAbsenceStatus(LessonData lessonData) throws SQLException;
  void updateAbsenceMotive(LessonData lessonData) throws SQLException;
}
