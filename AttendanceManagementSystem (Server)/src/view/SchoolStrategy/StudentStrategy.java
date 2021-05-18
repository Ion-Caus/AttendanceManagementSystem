package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import view.View;

import viewModel.SchoolViewModel;
import viewModel.StudentViewModel;

import java.util.Optional;

public class StudentStrategy extends SchoolStrategy {


    public StudentStrategy(SchoolViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public View add() {
        return View.STUDENT_VIEW;
    }

    @Override
    public void remove() {
        if (viewModel.hasSelectionStudent()) {
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
}