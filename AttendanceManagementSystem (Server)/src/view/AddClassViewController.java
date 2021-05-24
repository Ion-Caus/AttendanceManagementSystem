package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.AddClassViewModel;

public class AddClassViewController extends ViewController {
    @FXML private TextField className;
    @FXML private Label errorLabel;

    private AddClassViewModel viewModel;

    public AddClassViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddClassViewModel();
        className.textProperty().bindBidirectional(viewModel.classNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    public void submitButtonClicked() {
        if (viewModel.addClass()) {
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
