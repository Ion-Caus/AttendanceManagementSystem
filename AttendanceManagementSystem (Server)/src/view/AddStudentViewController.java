package view;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import viewModel.AddStudentViewModel;
import viewModel.SchoolViewModel;

public class AddStudentViewController extends ViewController {


    @FXML private Label errorLabel;
    @FXML private TextField studentName;

    private AddStudentViewModel viewModel;

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddStudentViewModel();

        errorLabel.textProperty().bind(viewModel.addStudentErrorProperty());
    }


    @FXML
    public void submitButtonClicked() {
        if (viewModel.addStudent(studentName.getText())) {
            getViewHandler().openView(View.SCHOOL_VIEW);
            reset();
        }


    }

    @FXML
    public void cancelButtonClicked() {
        getViewHandler().openView(View.SCHOOL_VIEW);
        reset();
    }

    @Override
    public void reset() {
        studentName.clear();
    }
}