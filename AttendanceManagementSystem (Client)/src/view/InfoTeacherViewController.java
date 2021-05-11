package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewmodel.InfoTeacherViewModel;

public class InfoTeacherViewController extends ViewController
{
  @FXML private Label subject;
  @FXML private TextField topicField;
  @FXML private TextArea contentsField;
  @FXML private TextArea homeworkField;
  @FXML private TextField teacherField;
  @FXML private DatePicker datePicker;
  @FXML private Label errorLabel;
  @FXML private Label classID;

  private InfoTeacherViewModel viewModel;

  public InfoTeacherViewController()
  {
    // called by the FXML loader
  }

  @Override protected void init()
  {

  }

  @Override public void reset()
  {

  }

  @FXML private void studentsButtonPressed()
  {
  }

  @FXML private void cancelButtonPressed()
  {
  }

  @FXML private void saveButtonPressed()
  {
  }
}
