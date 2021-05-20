package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewModel.AddGradeViewModel;

public class AddGradeViewController extends ViewController {
    @FXML private TextField gradeField;
    @FXML private TextArea commentArea;

    @FXML private Label errorLabel;

    private AddGradeViewModel viewModel;

    public AddGradeViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddGradeViewModel();
        gradeField.textProperty().bindBidirectional(viewModel.getGradeProperty());
        commentArea.textProperty().bindBidirectional(viewModel.getCommentProperty());

        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void cancelButtonClicked() {
        getViewHandler().openView(View.STUDENT_LIST_VIEW);
    }

    @FXML
    private void submitButtonClicked() {
        if (viewModel.submitGradeComment()) {
            getViewHandler().openView(View.STUDENT_LIST_VIEW);
        }
    }
}
