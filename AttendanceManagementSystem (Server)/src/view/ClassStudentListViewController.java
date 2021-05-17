package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;
import viewModel.ClassStudentListViewModel;
import viewModel.StudentViewModel;

import java.util.Optional;

public class ClassStudentListViewController extends ViewController {
    @FXML
    private Label classLabel;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<StudentViewModel> classStudentTable;
    @FXML
    private TableColumn<StudentViewModel, String> studentNameColumn;
    @FXML
    private TableColumn<StudentViewModel, String> studentIDColumn;
    @FXML
    private Label errorLabel;

    private ClassStudentListViewModel viewModel;

    public ClassStudentListViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getClassStudentListViewModel();

        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        studentIDColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        classStudentTable.setItems(viewModel.getClassStudentTable());
        classStudentTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        classLabel.textProperty().bind(viewModel.classProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());

        searchField.textProperty().bindBidirectional(viewModel.searchFieldProperty());
        TextFields.bindAutoCompletion(searchField, viewModel.getUnassignedStudents());

    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void addButtonPressed() {
        viewModel.addStudent();
    }

    @FXML
    private void backButtonPressed() {
        getViewHandler().openView(View.SCHOOL_VIEW);
    }

    @FXML
    private void removeButtonPressed() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result;

        StudentViewModel studentViewModel = viewModel.getSelectedStudent();
        alert.setTitle("Delete student");
        alert.setHeaderText("Delete student " + studentViewModel.nameProperty().get() + " ?");
        result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            viewModel.removeStudent(studentViewModel.idProperty().get());
        }
    }
}
