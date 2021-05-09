package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SchoolViewController;
import view.View;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;
import viewModel.StudentViewModel;

import java.util.Optional;

public class SchoolStudentsStrategy extends SchoolStrategy {


    public SchoolStudentsStrategy(SchoolViewController controller, SchoolViewModel viewModel) {
        super(controller, viewModel);
    }

    @Override
    void add() {
        controller.getViewHandler().openView(View.STUDENT_VIEW);

    }

    @Override
    void remove() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result;

        StudentViewModel studentViewModel =controller.getAllStudentsTable().getSelectionModel().getSelectedItem();
        alert.setTitle("Delete student");
        alert.setHeaderText("Delete student "+studentViewModel.nameProperty()+" ?");
        result = alert.showAndWait();

        if (result.isPresent() && result.get()== ButtonType.OK) {

            controller.getClassesTable().getSelectionModel().clearSelection();
            viewModel.removeStudent(studentViewModel);
        }

    }
}
