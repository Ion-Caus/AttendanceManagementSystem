package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.AddTeacherViewModel;

public class AddTeacherViewController extends ViewController {

    @FXML private Label errorLabel;
    @FXML private TextField teacherName;
    @FXML private TextField teacherID;

    private AddTeacherViewModel viewModel;

    public AddTeacherViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddTeacherViewModel();

        teacherID.textProperty().bindBidirectional(viewModel.teacherIDProperty());
        teacherName.textProperty().bindBidirectional(viewModel.teacherNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }


    @FXML
    public void submitButtonClicked() {
        if (viewModel.addTeacher()) {
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
