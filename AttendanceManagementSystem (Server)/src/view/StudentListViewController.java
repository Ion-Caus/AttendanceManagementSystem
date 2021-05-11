package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentListViewController extends ViewController
{
  @FXML private TableView<viewModel.StudentListViewModel> studentListTable;
  @FXML private TableColumn<viewModel.StudentListViewModel, String> studentNameColumn;
  @FXML private TableColumn<viewModel.StudentListViewModel, String> commentColumn;
  @FXML private TableColumn<viewModel.StudentListViewModel, Integer> gradeColumn;
  @FXML private TableColumn<viewModel.StudentListViewModel, String> absenceColumn;
  @FXML private TableColumn<viewModel.StudentListViewModel, String> motiveColumn;
  //TODO assign viewModel for columns. Right now - it's a placeholder.

  @FXML private Label StudentsClassLabel;
  @FXML private Label errorLabel;

  @FXML private Button changeStatusButton;
  @FXML private Button gradeCommentButton;
  @FXML private Button viewStudentButton;
  @FXML private Button viewMotiveButton;
  @FXML private Button backButton;

  private viewModel.StudentListViewModel viewModel; //TODO assign proper viewModel

  public StudentListViewController()
  {
    // Called by FXMLLoader
  }

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getStudentListViewModel();

    studentNameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getStudentNameProperty());
//    gradeColumn.setCellValueFactory(cellData -> cellData.getValue()
//        .getGradeProperty());  //TODO IntegerProperty conversion
    commentColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCommentProperty());
    absenceColumn.setCellValueFactory(
        cellData -> cellData.getValue().getAbsenceProperty());
    motiveColumn.setCellValueFactory(
        cellData -> cellData.getValue().getMotiveProperty());
    studentListTable.setItems(viewModel
        .getStudentList());   //TODO check if students are assigned to the table + Listener
    studentListTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) -> viewModel.setSelected(newVal));

    StudentsClassLabel.textProperty().bind(viewModel.getClassProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  @Override public void reset()
  {
    viewModel.clear();  //TODO check and update clear method in corresponding viewModel
  }

  @FXML private void back()
  {
    //TODO  change the "back" to view lesson instead of back to SCHOOL_VIEW
    getViewHandler().openView(View.INFOVIEW_VIEW);
  }

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