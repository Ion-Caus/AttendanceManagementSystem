package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentListViewController extends ViewController
{
  @FXML private TableView<viewModel.LessonViewModel> studentListTable;
  @FXML private TableColumn<viewModel.LessonViewModel, String> studentNameColumn;
  @FXML private TableColumn<viewModel.LessonViewModel, String> commentColumn;
  @FXML private TableColumn<viewModel.LessonViewModel, String> gradeColumn;
  @FXML private TableColumn<viewModel.LessonViewModel, String> absenceColumn;
  @FXML private TableColumn<viewModel.LessonViewModel, String> motiveColumn;
  //TODO assign viewModel for columns. Right now - it's a placeholder.

  @FXML private Label StudentsClassLabel;
  @FXML private Label errorLabel;

  @FXML private Button changeStatusButton;
  @FXML private Button gradeCommentButton;
  @FXML private Button viewStudentButton;
  @FXML private Button viewMotiveButton;
  @FXML private Button backButton;

  private viewModel.LessonViewModel viewModel; //TODO assign proper viewModel

  public StudentListViewController()
  {
    // Called by FXMLLoader
  }

  @Override protected void init()
  {
    // viewModel = getViewModelFactory().getScheduleViewModel();  //TODO get relevant viewModel

    studentNameColumn
        .setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
    gradeColumn
        .setCellValueFactory(cellData -> cellData.getValue().topicProperty());
    commentColumn
        .setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
    absenceColumn
        .setCellValueFactory(cellData -> cellData.getValue().timeProperty());
    motiveColumn.setCellValueFactory(
        cellData -> cellData.getValue().classroomProperty());
    //studentListTable.setItems(viewModel.getStudentList);   //TODO assign students to table + Listener
    /*studentListTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) -> viewModel.setSelected(newVal));*/

    // StudentsClassLabel.textProperty().bind(viewModel.ClassProperty()); //TODO assign class name to label
    //errorLabel.textProperty().bind(viewModel.errorProperty());
  }

  @Override public void reset()
  {
   /* viewModel.clear();  //TODO clear method in corresponding viewModel

    changeStatusButton.setVisible(viewModel.canEditProperty().get());
    gradeCommentButton.setVisible(viewModel.canEditProperty().get());
    viewStudentButton.setVisible(viewModel.canEditProperty().get());
    viewMotiveButton.setVisible(viewModel.canEditProperty().get());

    backButton.setVisible(viewModel.canBackProperty().get());
    */
  }

  /*  //TODO  Does it belongs here? P.S. stolen from ScheduleViewController. Haven't done any new methods in viewModels for it to work.
  @FXML
  private void backToSchoolView() {
    viewModel.backToSchoolView();
    getViewHandler().openView(View.SCHOOL_VIEW);
  }
   */
  @FXML private void changeStatus()
  {
  }

  @FXML private void gradeComment()
  {
  }

  @FXML private void viewStudent()
  {
  }

  @FXML private void viewMotive()
  {
  }
}