package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.ManageViewModel;

public class ManageViewController extends ViewController {


   @FXML private TextField nameField;
   @FXML private TextField idField;
   @FXML private TextField passwordField1;
   @FXML private TextField passwordField2;
   @FXML private Label errorLabel;


    private ManageViewModel viewModel;

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getManageViewModel();
        nameField.textProperty().bind(viewModel.getNameProperty());
        idField.textProperty().bind(viewModel.getIdProperty());
        passwordField1.textProperty().bindBidirectional(viewModel.getPasswordProperty1());
        passwordField2.textProperty().bindBidirectional(viewModel.getPasswordProperty2());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    @Override
    public void reset() {
       viewModel.clear();
       viewModel.loadPersonDetails();
    }

   @FXML public void cancelButtonPressed(ActionEvent actionEvent) {
        getViewHandler().openView(View.SCHEDULE_VIEW);
    }

   @FXML public void saveButtonPressed() {
        if(viewModel.saveDetails())
            getViewHandler().openView(View.SCHEDULE_VIEW);
    }

  @FXML public void onEnter(ActionEvent actionEvent)
  {
    if (actionEvent.getSource() == nameField) {
      idField.requestFocus();
    }
    else if (actionEvent.getSource() == idField) {
      passwordField1.requestFocus();
    }
    else if (actionEvent.getSource() == passwordField1) {
      passwordField2.requestFocus();
    }
    else if (actionEvent.getSource() == passwordField2) {
      saveButtonPressed();
    }
  }
}
