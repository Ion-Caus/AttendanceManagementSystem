package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClassStudentListViewController extends ViewController {
    @FXML private Label classLabel;
    @FXML private TextField nameField;
    @FXML private TextField labelField;
    @FXML private TableView classStudentTable;
    @FXML private TableColumn studentNameColumn;
    @FXML private TableColumn studentIDColumn;
    @FXML private Label errorLabel;

    @Override
    protected void init() {

    }

    @Override
    public void reset() {

    }

    @FXML private void addButtonPressed()
    {
    }

    @FXML private void backButtonPressed()
    {
    }

    @FXML private void removeButtonPressed()
    {
    }
}
