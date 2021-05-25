package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.StudentLessonDataViewModel;
import viewModel.StudentListViewModel;

public class StudentListViewController extends ViewController {
    @FXML
    private TableView<StudentLessonDataViewModel> studentListTable;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> idStudentColumn;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> studentNameColumn;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> commentColumn;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> gradeColumn;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> absenceColumn;
    @FXML
    private TableColumn<StudentLessonDataViewModel, String> motiveColumn;
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

        idStudentColumn.setCellValueFactory(
                cellData -> cellData.getValue().getIDStudentProperty());

        studentNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStudentNameProperty());

        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());

        commentColumn.setCellValueFactory(
                cellData -> cellData.getValue().getCommentProperty());
        absenceColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAbsenceProperty());
        motiveColumn.setCellValueFactory(
                cellData -> cellData.getValue().getMotiveProperty());
        studentListTable.setItems(viewModel.getLessonDataList());
        studentListTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        lessonTopicLabel.textProperty().bind(viewModel.getLessonTopicProperty());
        classNameLabel.textProperty().bind(viewModel.getClassNameProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    @Override
    public void reset() {
        viewModel.loadFromModel();
        viewModel.clear();  //TODO check and update clear method in corresponding viewModel
    }


    @FXML
    private void changeAbsence() {
        viewModel.changeAbsence();
        studentListTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void gradeComment() {
        if(viewModel.openGrade())
            getViewHandler().openView(View.ADD_GRADE_VIEW);
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
        getViewHandler().openView(View.INFO_VIEW);
    }
}