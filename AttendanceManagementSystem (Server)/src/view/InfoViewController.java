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
    this.viewModel = getViewModelFactory().getInfoViewModel();

    this.subject.textProperty().bind(viewModel.getSubjectProperty());
    this.topicField.textProperty().bind(viewModel.getTopicProperty());
    this.contentsField.textProperty().bind(viewModel.getContentsProperty());
    this.homeworkField.textProperty().bind(viewModel.getHomeworkProperty());
    this.teacherField.textProperty().bind(viewModel.getTeacherProperty()); // can we change a teacher from here or not? if yes, change to bidirectional.
    this.datePicker.valueProperty().bind(viewModel.getDateProperty()); // can we change the date from here? if yes, change to bidirectional.
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
    this.classID.textProperty().bind(viewModel.getClassIdProperty());
    this.teacherAbsenceField.textProperty().bindBidirectional(viewModel.getAbsentProperty());
  }

  @Override public void reset()
  {
    viewModel.clear();
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
