package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewModel.LoginViewModel;

public class LoginViewController extends ViewController {
    @FXML private TextField userIDField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private LoginViewModel viewModel;

    public LoginViewController() {
        //Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getLoginViewModel();

        userIDField.textProperty().bindBidirectional(viewModel.userIDProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void login() {
        View view = viewModel.login();
        if (view != null) {
            getViewHandler().openView(view);
        }
    }

    @FXML
    private void onEnter(ActionEvent event) {
        if (event.getSource() == userIDField) {
            passwordField.requestFocus();
        }
        else if (event.getSource() == passwordField) {
            login();
        }
    }
}
