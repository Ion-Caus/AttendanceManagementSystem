package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.StudentListViewModel;

public class StudentListViewController extends ViewController {
    @FXML
    private TableView<StudentListViewModel> studentListTable;
    @FXML
    private TableColumn<StudentListViewModel, String> studentNameColumn;
    @FXML
    private TableColumn<StudentListViewModel, String> commentColumn;
    @FXML
    private TableColumn<StudentListViewModel, Integer> gradeColumn;
    @FXML
    private TableColumn<StudentListViewModel, String> absenceColumn;
    @FXML
    private TableColumn<StudentListViewModel, String> motiveColumn;
    //TODO assign viewModel for columns. Right now - it's a placeholder.

    @FXML
    private Label lessonTopicLabel;
    @FXML
    private Label classNameLabel;

    @FXML
    private Label errorLabel;

    private StudentListViewModel viewModel; //TODO assign proper viewModel

    public StudentListViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getStudentListViewModel();

        studentNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStudentNameProperty());
        // TODO IntegerProperty conversion
        //gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());

        commentColumn.setCellValueFactory(
                cellData -> cellData.getValue().getCommentProperty());
        absenceColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAbsenceProperty());
        motiveColumn.setCellValueFactory(
                cellData -> cellData.getValue().getMotiveProperty());
        studentListTable.setItems(viewModel.getStudentList());
        studentListTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        lessonTopicLabel.textProperty().bind(viewModel.getLessonTopicProperty());
        classNameLabel.textProperty().bind(viewModel.getClassNameProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();  //TODO check and update clear method in corresponding viewModel
    }


    @FXML
    private void changeAbsence() {
        viewModel.changeAbsence();
    }

    @FXML
    private void gradeComment() {
    }

    @FXML
    private void viewStudent() {
    }

    @FXML
    private void viewMotive() {
    }

    @FXML
    private void backButtonPressed() {
        getViewHandler().openView(View.INFO_VIEW);
    }

    @FXML
    private void submitButtonPressed() {
        if (viewModel.submitDataChange())
            getViewHandler().openView(View.INFO_VIEW);
    }
}