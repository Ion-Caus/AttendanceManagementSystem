package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.SchoolStrategy.SchoolStrategyContext;
import view.SchoolStrategy.StudentStrategy;
import viewModel.ClassStudentListViewModel;
import viewModel.StudentViewModel;

import java.util.Optional;

public class ClassStudentListViewController extends ViewController {
    @FXML private Label classLabel;
    @FXML private TextField nameField;
    @FXML private TextField idField;
    @FXML private TableView<StudentViewModel> classStudentTable;
    @FXML private TableColumn<StudentViewModel, String> studentNameColumn;
    @FXML private TableColumn<StudentViewModel, String> studentIDColumn;
    @FXML private Label errorLabel;

    private ClassStudentListViewModel viewModel;

    public ClassStudentListViewController(){
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
        errorLabel.textProperty().bind(viewModel.errorProperty());

    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML private void addButtonPressed()
    {
        viewModel.addStudent();
    }

    @FXML private void backButtonPressed()
    {
        getViewHandler().openView(View.SCHOOL_VIEW);
    }

    @FXML private void removeButtonPressed()
    {
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
