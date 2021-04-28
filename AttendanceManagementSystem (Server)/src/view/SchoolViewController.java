package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.ClassViewModel;
import viewmodel.SchoolViewModel;
import viewmodel.StudentViewModel;
import viewmodel.TeacherViewModel;

public class SchoolViewController extends ViewController {
    @FXML private TabPane tabPane;

    @FXML private Label schoolName;
    @FXML private Label errorLabel;

    @FXML private Button scheduleButton;
    @FXML private Button studentListButton;

    @FXML private TableView<ClassViewModel> classesTable;
    @FXML private TableColumn<ClassViewModel, String> classNameColumn;

    @FXML private TableView<StudentViewModel> allStudentsTable;
    @FXML private TableColumn<StudentViewModel, String> studentColumn;

    @FXML private TableView<TeacherViewModel> allTeachersTable;
    @FXML private TableColumn<TeacherViewModel, String> teacherColumn;

    private SchoolViewModel viewModel;

    public SchoolViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getSchoolViewModel();

        //TODO check if its working   or change it to .addListener
        tabPane.getSelectionModel().getSelectedItem().textProperty().bind(viewModel.tabSelectedProperty());

        schoolName.textProperty().bind(viewModel.schoolNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());

        //TODO check if its working   or change it to .addListener
        scheduleButton.textProperty().bindBidirectional(viewModel.scheduleButtonProperty());
        studentListButton.textProperty().bindBidirectional(viewModel.studentListButtonProperty());

        // Classes Table
        classNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().classNameProperty()
        );
        classesTable.setItems(viewModel.getAllClasses());

        // Students Table
        studentColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        allStudentsTable.setItems(viewModel.getAllStudents());

        // Teachers Table
        teacherColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        allTeachersTable.setItems(viewModel.getAllTeachers());



    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void editSchoolName() {
    }

    @FXML
    private void add() {
    }

    @FXML
    private void remove() {
    }

    @FXML
    private void viewSchedule() {
    }

    @FXML
    private void viewStudentList() {
    }
}
