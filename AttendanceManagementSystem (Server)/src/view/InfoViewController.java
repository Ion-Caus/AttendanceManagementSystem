package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewModel.InfoViewModel;

public class InfoViewController extends ViewController
{
  @FXML private Label subject;
  @FXML private TextField topicField;
  @FXML private TextArea contentsField;
  @FXML private TextArea homeworkField;
  @FXML private TextField teacherField;
  @FXML private DatePicker datePicker;
  @FXML private Label errorLabel;
  @FXML private Label classID;
  @FXML private TextField teacherAbsenceField;

  private InfoViewModel viewModel;

  public InfoViewController()
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
