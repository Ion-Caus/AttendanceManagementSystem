package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import view.View;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;


import java.util.Optional;

public class ClassStrategy extends SchoolStrategy {

    public ClassStrategy(SchoolViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public View add() {
        return View.CLASS_VIEW;
    }

    @Override
    public void remove() {

        ClassViewModel classViewModel = viewModel.getSelectedClass();
        alert.setTitle("Delete class");
        alert.setHeaderText("Delete class " + classViewModel.classNameProperty().get() + " ?");
        result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            viewModel.removeClass(classViewModel.classNameProperty().get());
        }
    }
}