package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.AddStudentViewModel;

public class AddStudentViewController extends ViewController {


    @FXML private Label errorLabel;
    @FXML private TextField studentName;
    @FXML private TextField studentID;

    private AddStudentViewModel viewModel;

    public AddStudentViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddStudentViewModel();

        studentID.textProperty().bindBidirectional(viewModel.studentIDProperty());
        studentName.textProperty().bindBidirectional(viewModel.studentNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }


    @FXML
    public void submitButtonClicked() {
        if (viewModel.addStudent()) {
            getViewHandler().openView(View.SCHOOL_VIEW);
            reset();
        }


    }

    @FXML
    public void cancelButtonClicked() {
        getViewHandler().openView(View.SCHOOL_VIEW);
        reset();
    }

}