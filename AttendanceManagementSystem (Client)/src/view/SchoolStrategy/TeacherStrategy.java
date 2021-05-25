package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.View;
import viewModel.SchoolViewModel;
import viewModel.TeacherViewModel;

import java.util.Optional;

public class TeacherStrategy extends SchoolStrategy {
    
    public TeacherStrategy(SchoolViewModel viewModel) {
        super(viewModel);
    }

    @Override
    View add() {
        return View.ADD_TEACHER_VIEW;
    }

    @Override
    void remove() {
        if (viewModel.hasSelectionTeacher()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result;

            TeacherViewModel teacherViewModel = viewModel.getSelectedTeacher();
            alert.setTitle("Delete teacher");
            alert.setHeaderText("Delete teacher " + teacherViewModel.nameProperty().get() + " ?");
            result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                viewModel.removeTeacher(teacherViewModel.idProperty().get());
            }
        }
    }
}
