package view.SchoolStrategy;

import javafx.scene.control.ButtonType;
import view.SchoolViewController;
import viewModel.SchoolViewModel;
import viewModel.TeacherViewModel;

public class SchoolTeacherStrategy extends SchoolStrategy{
    public SchoolTeacherStrategy(SchoolViewController controller, SchoolViewModel viewModel) {
        super(controller, viewModel);
    }

    @Override
    void add() {

    }

    @Override
    void remove() {

        TeacherViewModel teacherViewModel =controller.getAllTeachersTable().getSelectionModel().getSelectedItem();
        alert.setTitle("Delete teacher");
        alert.setHeaderText("Delete teacher "+teacherViewModel.nameProperty()+" ?");
        result = alert.showAndWait();

        if (result.isPresent() && result.get()== ButtonType.OK) {

            controller.getAllTeachersTable().getSelectionModel().clearSelection();
          //  viewModel.removeTeacher(teacherViewModel);
        }

    }
}
